(function(){
	"use strict";
	define(['jquery','ajaxwrapper','ui', 'dialog','intense'],function($,ajaxWrapper,ui,dialog,intense){
		var editorForm = function($form){
			this.exam = undefined,
			this.isNew =  true,
			this.show = function(exam){
				this.isNew = exam.isNew;
				this.exam = exam;
				if(!exam.isNew){
					$('#examName').val(exam.o.desc.name);
					$('#beginDate').val(exam.o.desc.from);
					$('#endDate').val(exam.o.desc.to);
				}
				else{
					$('#examName').val('').focus();
					$('#beginDate').val('');
					$('#endDate').val('');
				}
			};
			
			//表单验证
			this.validate = function(){
				var b = true;
				
				return b;
			};
			
			function createExam(){
				var myTable = $('div.subject-container>table');
				var id = myTable.find('td:eq(0) a').attr('data-rr-id');
				var oid = myTable.find('td:eq(0) a').attr('data-rr-oid');
				
				var exam = {id:id,oid:oid,desc:{name:$("#examName").val(),from:$("#beginDate").val(),to:$("#endDate").val()}};
				return exam;
			}
			
			this.save = function(){
				if(this.validate()){
					var eaxm = createExam();
					ajaxWrapper.postJson("/exam",eaxm,
							{beforeMsg:{tipText:".",show:false},
						     successMsg:{tipText:"保存成功",show:true}},
							function(m){
								if(m.status.success){
									setTimeout(function(){location.reload();},1000);
							}
					});
				}

			};
			
			
			this.update = function(){
				if(this.validate()){
					var eaxm = createExam();
					ajaxWrapper.putJson("/exam",eaxm,
						{beforeMsg:{tipText:".",show:false},
						 successMsg:{tipText:"更新成功",show:true}},
						function(m){
							if(m.status.success){
								setTimeout(function(){location.reload();},1000);
						}
					});
				}

			};
			var self = this;
			$form.submit(function(){
				if(self.isNew){
					self.save();
				}else{
					self.update();
				}
				return false;
			});
		};
		
		var o = function(){
			var myTable = $('div.subject-container>table');
			var myForm = new editorForm($('div.subject-container>.subject-editor>form'));
			var self = this;
			var currentEaxm = {
				isNew:false,
				row:undefined,
				o:undefined,
				show:function(){
					if(!this.isNew){
						this.o = {
							id:myTable.find('td:eq(0) a').attr('data-rr-id'),
							oid:myTable.find('td:eq(0) a').attr('data-rr-oid'),
							desc:{
								name:myTable.find('td:eq(0) a').text(),
								from:myTable.find('td:eq(1) a').text(),
								to:myTable.find('td:eq(2) a').text()
							},
						};
					}else{
										
					}
					myForm.show(this);
				}
			};
			
			
			//单击事件
			myTable.on('click',"#newExam",function(e){
				currentEaxm.isNew = true;
				currentEaxm.show();
			}).on('click','tbody tr td a[data-rr-name="examName"]',function(e){
				currentEaxm.row = $(this).parent().parent();
				currentEaxm.isNew = false;
				currentEaxm.show();
				
			}).on('click','tbody tr td a[data-rr-name="deleteExam"]',function(e){
				var myTable = $('div.subject-container>table');
				var id = myTable.find('td:eq(0) a').attr('data-rr-id');
				ajaxWrapper.removeJson("exam",{id:id},
						{beforeMsg:{tipText:".",show:true},
						successMsg:{tipText:"删除成功",show:true}},
						function(m){
							if(m.status.success){
								setTimeout(function(){location.reload();},1000);
						}
				   });
			});
		};
		
		//提示
		function getOpts(message){
			var DialogSize = {SM:'sm',MD:'md',LG:'lg'};
			var opts = {
					size : DialogSize.SM,
					header : {
						show : true,
						text : "操作提示"
					},
					iconInfo:'error',
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