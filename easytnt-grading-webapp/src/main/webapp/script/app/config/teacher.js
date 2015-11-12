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
				var teacher = {name:$('#teacherName').val(),leader:leader,subject:{id:$('#subject :selected').attr('data-rr-value'),subjectCode:$('#subject').val()}};
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
					
					var teacher = {teacherName:$('#teacherName').val(),leader:leader,subject:{id:$('#subject').val(),name:$('#subject :selected').attr('data-rr-sname'),subjectCode:$('#subject :selected').attr('data-rr-code')}};
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
					var teacher = {teacherName:$('#teacherName').val(),leader:leader,subject:{id:$('#subject').val(),name:$('#subject :selected').attr('data-rr-sname'),subjectCode:$('#subject :selected').attr('data-rr-code')}};
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
			var myForm = new editorForm($form);
			ui.pretty($form);
			var currentTeacher = {
				isNew:false,
				row:undefined,
				o:undefined,
				show:function(){
					this.o = {
						name:this.row.find(''),
						teacherId:this.row.find(''),
						subject:{
							id:this.row.find()
						}
					};
					myForm.show(this);
				}
			};
			
			//点击科目搜索按钮
			$("#search").click(function(e){
				var subject_id = $('#subject').val();
				var subject_name = $('#subject :selected').attr('data-rr-sname');
				var teacher ={subject:{id:subject_id,name:subject_name}}
				ajaxWrapper.get("/teacher/getSubjectName",teacher,"Json",{beforeMsg:{tipText:"没有该科目信息",show:false},successMsg:{tipText:"查询返回结果集",show:true}},function(data){
					if(data.status.success){
						setTimeout(function(){
							location.reload();
						},1000);
					}
				});
			});
			
			myTable.on('click','tbody tr td a[data-rr-name=teacher]',function(e){
				currentTeacher.row = $(this).parent().parent();
				currentTeacher.show();
			}).on('click','tbody tr td a[data-rr-name="updatePass"]',function(e){
				var sd = $(this).parent().parent().find('td:first a[data-rr-name="teacher"]');
				var btns = [{text:'确定',clazz :'btn-primary',callback:function(){
					var tId = sd.attr('data-rr-tid');
					var teacherPass = sd.attr('data-rr-tpass');
					var tPass = $("#inputPassord").val();
					var nPass = $("#newPassword").val();
					var qPass = $("#readPassword").val();
					if(tPass!=teacherPass){
						dialog.fadedialog(getOpts("原密码有误"));
					}else if(nPass!=qPass){
						dialog.fadedialog(getOpts("密码不一致"));
					}else{
				    var teacher = {teacherId:tId,teacherPassord:qPass};	
					ajaxWrapper.putJson("teacher/updatePass",teacher,{beforeMsg:{tipText:"",show:false},successMsg:{tipText:"修改密码成功",show:true}},function(data){
						if(data.status.success){
							dialog.update-success(getOpts("修改密码成功"));
							//$(this).trigger('close');
						}
					});
				}
				}},{text:'放弃',callback:function(){
					$(this).trigger('close');
				}}];
				var message=
					'<form id="updatePass"  method="POST" action="">'+
			    	   '<div class="input-group update-pass">'+
				    	'<div class="form-group">'+
				    		'<label for="inputPassord" class="col-sm-4 control-label">原密码</label>'+
				    			'<div class="col-sm-8">'+
				    				'<input type="text" class="form-control" id="inputPassord"  placeholder="原密码">'+
				    			'</div>'+
				    	'</div> '+

				    	'<div class="form-group">'+
			    		'<label for="newPassword" class="col-sm-4 control-label">新密码</label>'+
			    			'<div class="col-sm-8">'+
			    				'<input type="password" class="form-control" id="newPassword"  placeholder="新密码">'+
			    			'</div>'+
			    		'</div> '+
			    		
			    		'<div class="form-group">'+
			    		'<label for="readPassword" class="col-sm-4 control-label">确认密码</label>'+
			    			'<div class="col-sm-8">'+
			    				'<input type="password" class="form-control" id="readPassword"  placeholder="确认密码">'+
			    			'</div>'+
			    	    '</div> '+
				       '</div>'+
				  '</form>';
				var modal = ui.modal("修改密码",message,5,btns);
				
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