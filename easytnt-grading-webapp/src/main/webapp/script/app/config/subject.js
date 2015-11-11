(function(){
	"use strict";
	define(['jquery','app/marking/ImgToolbox','app/marking/PointPanel', 'app/marking/ImgView' ,'ajaxwrapper','ui'],function($,imgToolbox,point,imgViewer,ajaxWrapper,ui){
		var subjectExam = function(){
			this.testId=undefined;
			this.subject = {};
			this.usedPaper=[];
		};
		var examPaper = function(){
			this.name = '';
			this.fullScore = 0;
			this.objectivityScore = 0;
			this.subjectivityScore = 0;
		};
		var subject = function(){
			this.name = '';
			this.subjectCode = '';
		};
		var editorForm = function(editorForm){
			
			this.examPaper = undefined,
			this.isNew =  true,
			this.show = function(examPaper,subject,subjectExam,isNew){
				for(var o in examPaper){
					editorForm.find('#'+o).val(examPaper[o]);
				}
				for(var o in subject){
					editorForm.find('#'+o).val(subject[o]);
				}
				for(var o in subjectExam){
					editorForm.find('#'+o).val(subjectExam[o]);
				}
				editorForm.find(':text:first').focus();
				this.isNew = isNew;
				this.examPaper = examPaper;
				this.subject = subject;
				this.subjectExam = subjectExam;
			};
			
			this.validate = function(){
				var b = true;
				var score = [0,0,0];
				editorForm.find(':text').each(function(i,n){
					if(i == 0){
						if(this.value.length < 1 ){
							b = false;
							return false;
						}	
					}else{
						score[i-1] = this.value;
					}
				});
				if(b && score[0] != (score[1]*1 + score[2]*1)){
					b = false;
				}
				return b;
			};
			
			this.save = function(){
				if(this.validate()){
					ajaxWrapper.postJson("subjectExam",setInfo.setValue(),
							{beforeMsg:{tipText:".",show:false},
							sucessMsg:{tipText:"保存成功",show:true}},
							function(m){
								if(m.status.success){
									location.reload();
								}
							});
				}

			};
			this.update = function(){
				if(this.validate()){
					ajaxWrapper.putJson("subjectExam",setInfo.setValue(),
							{beforeMsg:{tipText:".",show:false},
							sucessMsg:{tipText:"保存成功",show:true}},
							function(m){
								if(m.status.success){
									location.reload();
								}
							});
				}

			};
			var self = this;
			editorForm.submit(function(){
				if(self.isNew){
					self.save();
				}else{
					self.update();
				}
				return false;
			});
			
		};
		var setInfo = {
				setValue:function(){
					var myForm = $('div.subject-container>.subject-editor>form');
					var e = new examPaper();
					var s = new subject();
					var se = new subjectExam();
					e.name = myForm.find('#name').val();
					s.name = myForm.find('#name').val();
					s.subjectCode = myForm.find('#subjectCode').val();
					e.fullScore = myForm.find('#fullScore').val();
					e.objectivityScore = myForm.find('#objectivityScore').val();
					e.subjectivityScore = myForm.find('#subjectivityScore').val();
					se.testId=myForm.find('#testId').val();
					se.subject = s;
					se.usedPaper = [e];
					return se;
				}
			};
		var o = function(){
			var myTable = $('div.subject-container>table');
			var myForm = new editorForm($('div.subject-container>.subject-editor>form'));
			var currentSubject = {
				isNew:false,
				row:undefined,
				show:function(){
					var e = new examPaper();
					var s = new subject();
					var se = new subjectExam();
					if(!this.isNew){
						var sd = this.row.find('td:first a[data-rr-name="subjectName"]');
						e.name = sd.text();
						s.name = sd.text();
						s.subjectCode = sd.attr('data-rr-value');
						se.testId = sd.attr('data-rr-testId');
						e.fullScore = this.row.find('td:eq(5)').text();
						e.objectivityScore = this.row.find('td:eq(6)').text();
						e.subjectivityScore = this.row.find('td:eq(7)').text();
						se.subject = s;
						se.usedPaper = [e];
					}
					myForm.show(e,s,se,this.isNew);
				}
			};

			myTable.on('click','tbody #newSubject',function(e){
				currentSubject.isNew = true;
				currentSubject.row = $(this).parent().parent();
				currentSubject.show();
			}).on('click','tbody #updateSubject',function(e){
				currentSubject.isNew = false;
				currentSubject.row = $(this).parent().parent().parent().parent().parent();
				currentSubject.show();
			}).on('click','tbody #removeSubject',function(e){
				var myTable = $('div.subject-container>table');
				var sd = $(this).parent().parent().parent().parent().parent().find('td:first a[data-rr-name="subjectName"]');
				var testId = sd.attr('data-rr-testId');
				if(testId==""){
					return;
				}
				ajaxWrapper.removeJson("subjectExam",{testId:testId},
						{beforeMsg:{tipText:".",show:false},
						sucessMsg:{tipText:"删除成功",show:true}},
						function(m){
							if(m.status.success){
								location.reload();
							}
						});
			}).on('click','tbody #addImage',function(e){
				var row = $(this).parent().parent();
				var paperId = row.find('td:eq(5)').attr('data-rr-paperId');
				var btns = [{text:'确定',clazz : 'btn-primary',callback:function(){
					if($('div.file-preview-filename').val() == 0){
						return;
					}
					if(paperId==''){
						return;
					}
					ajaxWrapper.upload('examPaper/onAddPaperCard/'+paperId,
							'fileName',
							{beforeMsg:{tipText:"",show:true},successMsg:{tipText:"上传成功",show:true}});
					$(this).trigger('close');
				}},{text:'放弃',callback:function(){
					$(this).trigger('close');
				}}];
				var message=
					'<form id="uploadForm"  method="POST" action="" enctype="multipar/form-data">'+
			    	   '<div class="input-group file-preview">'+
				    	'<input type="text" class="form-control file-preview-filename" disabled="disabled">'+
				    	'<div class="input-group-btn"> '+
						   '<button type="button" class="btn btn-default file-preview-clear" style="display:none;">'+
						      '<span class="glyphicon glyphicon-remove"></span>清除'+
						    '</button>'+
						    '<div class="btn btn-default file-preview-input" >'+
						      '<span class="glyphicon glyphicon-folder-open"></span>'+
						      '<span class="file-preview-input-title">选择文件</span>'+
						      '<input id="fileName" type="file" name="fileName" accept="image/gif, image/jpeg,image/png" style="display:none;">'+
						    '</div>'+
					    '</div>'+
				       '</div>'+
				  '</form>';
				var modal = ui.modal('上传图片',message,'md',btns);
				modal.find(':text').focus();
				ui.fileUpload(modal);
				$(modal.find('#uploadForm')).submit(function(){
					return false;
				});
			});
		};

		return {
			render:function(){
				
				return new o();
			}
		};
	});
})();