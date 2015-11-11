(function(){
	"use strict";
	define(['jquery','app/marking/ImgToolbox','app/marking/PointPanel', 'app/marking/ImgView' ,'ajaxwrapper','ui', 'dialog'],function($,imgToolbox,point,imgViewer,ajaxWrapper,ui,dialog){
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
					ajaxWrapper.postJson("subjectExam/onCreateSubjectExam",setInfo.setValue(),
							{beforeMsg:{tipText:".",show:false},
							sucessMsg:{tipText:"计分成功",show:true}},
							function(m){
								if(m.status.success){
									dialog.fadedialog(getOpts("保存成功"));
									location.reload();
								}
							});
				}

			};
			this.update = function(){
				if(this.validate()){
					ajaxWrapper.putJson("subjectExam/onUpdateSubjectExam",setInfo.setValue(),
							{beforeMsg:{tipText:".",show:false},
							sucessMsg:{tipText:"计分成功",show:true}},
							function(m){
								if(m.status.success){
									dialog.fadedialog(getOpts("保存成功"));
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
			}).on('click','tbody tr td a[data-rr-name="updateSubject"]',function(e){
				currentSubject.isNew = false;
				currentSubject.row = $(this).parent().parent().parent().parent().parent();
				currentSubject.show();
			}).on('click','tbody tr td a[data-rr-name="removeSubject"]',function(e){
				var myTable = $('div.subject-container>table');
				var sd = $(this).parent().parent().parent().parent().parent().find('td:first a[data-rr-name="subjectName"]');
				var testId = sd.attr('data-rr-testId');
				ajaxWrapper.removeJson("subjectExam/onDeleteSubjectExam",{testId:testId},
						{beforeMsg:{tipText:".",show:false},
						sucessMsg:{tipText:"删除成功",show:true}},
						function(m){
							if(m.status.success){
								dialog.fadedialog(getOpts("删除成功"));
								location.reload();
							}
						});
			}).on('click','tbody tr td a[data-rr-name="addImage"]',function(e){
				var btns = [{text:'确定',clazz : 'btn-primary',callback:function(){
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
						    '<button type="type" class="btn btn-default " id="upload">'+
						      '<span class="glyphicon glyphicon-upload"></span>上传'+
						    '</button>'+
					    '</div>'+
				       '</div>'+
				  '</form>';
				var modal = ui.show("图片上传",message,'md');
				ui.fileUpload(modal);
				$(modal.find('#uploadForm')).submit(function(){
					return false;
				});
				var row = $(this).parent().parent();
				var paperId = row.find('td:eq(4)').attr('data-rr-paperId');
				$(modal.find('#upload')).click(function(){
					if($('div.file-preview-filename').val() == 0){
						return;
					}
					ajaxWrapper.upload('examPaper/onAddPaperCard/'+paperId,
							'fileName',
							{beforeMsg:{tipText:".",show:false},
								sucessMsg:{tipText:"上传成功",show:true}},
							function(m){
									if(m.status.success){
										dialog.fadedialog(getOpts("上传成功"));
									}
							});
				});
				$(modal.find('.close')).click(function(){
					location.reload();
				});
			}).on('click','tbody tr td a[data-rr-name="image"]',function(e){
				var path = $(this).attr('data-rr-imagePath');
				
				var message ='<img src="static/'+path+'" style="width:550px;height:700px" />'
				ui.show("图片预览",message,'md');
				
//				var cardId = $(this).attr('data-rr-cardId');
//				var row = $(this).parent().parent();
//				var paperId = row.find('td:eq(4)').attr('data-rr-paperId');
//				ajaxWrapper.removeJson("examPaper/onRemovePaperCard/"+paperId,{cardId:cardId,path:path},
//						{beforeMsg:{tipText:".",show:false},
//						sucessMsg:{tipText:"删除成功",show:true}},
//						function(m){
//							if(m.status.success){
//								dialog.fadedialog(getOpts("删除成功"));
//								location.reload();
//							}
//						});
			});
		};
		function getOpts(message){
			var DialogSize = {SM:'sm',MD:'md',LG:'lg'};
			var opts = {
					size : DialogSize.SM,
					header : {
						show : true,
						text : "操作提示"
					},
					tipText :message
				};
			return opts;
		}
		return {
			render:function(){
				
				return new o();
			}
		};
	});
})();