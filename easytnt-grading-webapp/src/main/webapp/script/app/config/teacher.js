(function(){
	"use strict";
	define(['jquery','ajaxwrapper'],function($,ajaxWrapper){
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
			this.Teacher = undefined,
			//获取表单的各个参数
			this.show = function(Teacher){
				editorForm.find('#teacherName').val(Teacher.o.teacherName);
				editorForm.find('#teacherAccount').val(Teacher.o.teacherAccount);
				editorForm.find('#teacherName').focus();
				$('#subject').val(Teacher.o.subject.id);
				this.Teacher = Teacher.o;
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
			
			var self = this;
			editorForm.submit(function(){
				//self.setData();
				self.save();
				return false;
			});
		};
		
		var o = function(){
			var myTable = $('div.subject-container>table');
			var myForm = new editorForm($('div.subject-container>.subject-editor>form'));
			var currentTeacher = {
				isNew:false,
				row:undefined,
				o:undefined,
				show:function(){
					this.o = new Teacher();
					if(!this.isNew){
						var sd = this.row.find('td:first a[data-rr-name="subjectName"]');
						//获取文本值
						this.o.subject.name = sd.text();	
						//获取id
						this.o.subject.id = sd.attr('data-rr-value');
						//获取显示科目id和科目
						//$('#subjectId option:selected').text("Id："+sd.attr('data-rr-value')+" "+"值："+t.subject.name);  
						
						this.o.teacherName = this.row.find('td:eq(1)').text();
						this.o.teacherAccount = this.row.find('td:eq(2)').text();
					}
					myForm.show(this);
				}
			};
			
			
			//点击+号新增
			myTable.on('click','tbody #newTeacher',function(e){
				currentTeacher.isNew = true;
				currentTeacher.row = $(this).parent().parent();
				currentTeacher.show();
			}).on('click','tbody a[data-rr-name="subjectName"]',function(e){
				currentTeacher.isNew = false;
				currentTeacher.row = $(this).parent().parent();
				currentTeacher.show();
			});
		};

		return {
			render:function(){
				
				return new o();
			}
		};
	});
})();