(function(){
	"use strict";
	define(['jquery','ajaxwrapper','ui', 'dialog'],function($,ajaxWrapper,ui,dialog){
		var Teacher = function(){
			this.teacherName = '';
			this.teacherAccount = '';
			this.teacherPassord ='';
			this.leader= 0;
			this.subject = {};
		};
		var subject = function(){
			this.id = '';
			this.name = '';
		};
		
		var editorForm = function($form){
			this.Teacher = {},
			this.isNew =  true,
			//获取表单的各个参数
			this.show = function(teacher){
				this.Teacher = teacher;
				this.isNew  = false;
			};
			
			//验证表单
			this.validate = function(){
				var b = true;
				//TODO
				return b;
			};
			
			//单独抽出来的取值拼接json格式
			function createTeacher(){
				var leader = 0;
				if($('#isLeader')[0].checked)
					leader = 1;
				var teacher = {teacherName:$('#teacherName').val(),leader:leader,subject:{id:$('#subject :selected').attr('data-rr-value'),subjectCode:$('#subject').val()}};
				return teacher;
			};
			
			
			//保存响应
			this.save = function(){
				if(this.validate()){
					var amount = $("#amount").val();					
					if(amount * 1 < 1){
						amount = 1;
					}
					var leader = 0;
					if($('#isLeader')[0].checked){
						leader = 1;
					}else{
						leader = 0;
					}
					
					var teacher = createTeacher();
					ajaxWrapper.postJson("/teacher/"+amount ,teacher,
						{beforeMsg:{tipText:"",show:false},successMsg:{tipText:"创建成功",show:true}},
						function(data){
							if(data.status.success){
								setTimeout(function(){
									location.reload();
								},1000);
							}
						}
					);
				}
			};
			
			//修改响应
			this.update = function(){
				if(this.validate()){
					var teacher = createTeacher();
					ajaxWrapper.putJson("/teacher",teacher,{beforeMsg:{tipText:"",show:false},successMsg:{tipText:"更新成功",show:true}},function(data){
						if(data.status.success){
							setTimeout(function(){
								location.reload();
							},1000);
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
			var  $form = $('div.subject-container>.subject-editor>form');
			var $outer = $('div.subject-container');
			var myForm = new editorForm($form);
			ui.pretty($form);
			ui.pretty(myTable.prev());
			var currentTeacher = {
				isNew:false,
				row:undefined,
				o:undefined,
				show:function(){
					this.o = {
						name:this.row.find('td:eq(1)').text(),
						teacherId:this.row.find('td:eq(0) a').attr('data-rr-tid'),
						subject:{
							id:this.row.find('td:eq(0) a').attr('data-rr-sid')
						}
					};
					myForm.show(this);
				}
			};
			
			//加入页码块
			this.query = function(pager){
				logger.log('examinee.query');
				if(!pager){
	        	    pager = ui.pager.init($outer);
	        	}
			};
			
			ui.pager.render({
				"fn":self.query,
				"containerObj" : $outer,
				"pageObj" : $outer.find('#pager')
			});
			
			ui.pretty($outer);
			
			//select 的下拉change响应事件
			$("#subject-query").change(function(){
				var messageObj ="";
				var subjectId = $("#subject-query").val();
				ajaxWrapper.getHtml("/query/1/10",{id:subjectId},messageObj,function(html){
					  var rowSize = myTable.find('tbody tr').size();
					  myTable.find('tbody tr:lg('+rowSize+')').remove();
					  $(html).insertBefoer(myTable.find('tbody tr:last'));
				});
			});
			
			myTable.on('click','tbody tr td a[data-rr-name=teacher]',function(e){
				currentTeacher.row = $(this).parent().parent();
				currentTeacher.show();
			}).on('click','tbody tr td a[data-rr-name="resetPass"]',function(e){
				var sd = $(this).parent().parent().find('td:first a[data-rr-name="teacher"]');
				var account =sd.attr("data-rr-account");
				var tId = sd.attr("data-rr-tid");
				//这种做法无意义,而且修改密码也不是这样修改的，这是管理员用的
				var btns = [{text:'确定',clazz :'btn-primary',callback:function(){
				    var teacher = {teacherId:tId,teacherAccount:account};	
					ajaxWrapper.putJson("/teacher/password/reset/"+tId,teacher,{beforeMsg:{tipText:"",show:false},successMsg:{tipText:""+account+"账号密码重置成功",show:true}},function(data){
						if(data.status.success){
						}
					});
				
				}},{text:'放弃',callback:function(){
					$(this).trigger('close');
				}}];
				var message1="是否确定重置账号"+account+"的密码";
				var modal = ui.modal("重置密码",message1,5,btns);
			});
			$form.show();
		};

		//操作提示
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