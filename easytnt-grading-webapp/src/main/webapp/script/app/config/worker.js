(function(){
	"use strict";
	define(['jquery','ajaxwrapper','Json'],function($,ajaxWrapper){
		var Teacher = function(){
			this.teacherName = '';
			this.teacherAccount = '';
			//科目属性值
			this.subject = {
				id:'',
				name:''
			}
		};
		
		var editorForm = function(editorForm){
			this.subject = undefined,
			//获取表单的各个参数
			this.show = function(Teacher){
				for(var o in Teacher){
					editorForm.find('#'+o).val(Teacher[o]);
				}
				editorForm.find(':text:first').focus();
				this.Teacher = Teacher;
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
			
			
			//保存响应
			this.save = function(){
				if(this.validate()){
					//响应执行ajax 		 //得到上面的teacher实体参数
					ajaxWrapper.postJson("/teacher",this.Teacher,
							{
						    beforeMsg:{tipText:"",show:false},
						    successMsg:{tipText:"创建成功",show:true}
						    },
							function(m){
						    	if(m.status.success){
						}
					});
				}
			};
			
			this.update = function(){
				if(this.validate()){
					//调用putJson
				}
			}
			
			this.remove = function(){
				if(this.validate()){
					//调用removeJson
				}
			}
			
			//给表单赋值 
			this.setData = function(){
				this.Teacher.teacherName = editorForm.find("#teacherName").val();
				this.Teacher.subject.name = editorForm.find("#subjectId").val();
				
			}
			
			
			var self = this;
			editorForm.submit(function(){
				self.setData();
				self.save();
				return false;
			});
		};
		
		var o = function(){
			var myTable = $('div.subject-container>table');
			var myForm = new editorForm($('div.subject-container>.subject-editor>form'));
			var currentSubject = {
				isNew:false,
				row:undefined,
				show:function(){
					var t = new Teacher();
					if(!this.isNew){
						var sd = this.row.find('td:first a[data-rr-name="subjectName"]');
						//获取文本值
						t.subject.name = sd.text();	
						//获取id
						t.subject.id = sd.attr('data-rr-value');
						//获取显示科目id和科目
						$('#subjectId option:selected').text("Id："+sd.attr('data-rr-value')+" "+"值："+t.subject.name);  
						
						t.teacherName = this.row.find('td:eq(1)').text();
						t.teacherAccount = this.row.find('td:eq(2)').text();
					}
					myForm.show(t);
				}
			};
			
			var initSubject = function(isNew){
				currentSubject.isNew = isNew;
				currentSubject.row = $(this).parent().parent();
				currentSubject.show();
			}

			//默认新增
			initSubject(true);
			
			//点击+号新增
			myTable.on('click','tbody #newTeacher',function(e){
				initSubject(true);
			}).on('click','tbody a[data-rr-name="subjectName"]',function(e){
				initSubject(false);
			});
		};

		return {
			render:function(){
				
				return new o();
			}
		};
	});
})();