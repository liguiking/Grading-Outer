(function(){
	"use strict";
	define(['jquery','ajaxwrapper'],function($,ajaxWrapper){
		var Teacher = function(){
			this.teacherName = '';
			this.teacherAccount = '';
			this.teacherPassord ='';
			this.subject = {};
		};
		var subject = function(){
			this.id = '';
			this.name = '';
		};
		
		var editorForm = function(editorForm){
			this.Teacher = undefined,
			this.isNew =  true,
			//获取表单的各个参数
			this.show = function(Teacher,subject,isNew){
				for(var o in Teacher){
					editorForm.find('#'+o).val(Teacher[o]);
				}
				for(var o in subject){
					editorForm.find('#'+o).val(subject[o]);
				}
				
				$('#subject').val(Teacher.subject.id);
				//editorForm.find(':text:first').focus();
				this.isNew=isNew;
				this.Teacher = Teacher;
				this.subject = subject;
				
			};
			
			//验证表单
			this.validate = function(){
				var b = true;
				var sName = $("#subject").find("option:selected").text()
				var tName = $("#teacherName").val();
				if(sName!="" &&sName !=null && tName!=""&&!tName !=null){
					return true;
				}else{
					b=false;
					return false;
				}
				/*var score = [0,0,0];
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
				}*/
				return b;
			};
			
			//保存响应
			this.save = function(){
				if(this.validate()){
					ajaxWrapper.postJson("/teacher",myData.dataValue(),
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
			
			//修改响应
			this.update = function(){
				if(this.validate()){
					//调用putJson
					ajaxWrapper.putJson("/teacher",myData.dataValue(),
					  {
						beforeMsg:{tipText:"",show:false},
					    successMsg:{tipText:"更新成功",show:true}
					   },
					function(m){
				    	if(m.status.success){
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
		
		//赋值
		var myData = {
			dataValue:function(){
			var myForm = $('div.subject-container>.subject-editor>form');
			var t = new Teacher();
			var s = new subject();
			//s.id = myForm.find("#subject").val();  --自动生成id可以删掉
			s.name =  $("#subject").find("option:selected").text()
			
			t.teacherName = myForm.find("#teacherName").val();
			t.teacherAccount = myForm.find("#teacherAccount").val();
			t.teacherPassord = t.teacherAccount;
			//获取主键id
			t.teacherId = $("#teacherId").val();
			t.subject = s;
			return t;
			}
		};
		
		var o = function(){
			var myTable = $('div.subject-container>table');
			var myForm = new editorForm($('div.subject-container>.subject-editor>form'));
			var currentTeacher = {
				isNew:false,
				row:undefined,
				o:undefined,
				show:function(){
					var t = new Teacher();
					var s = new subject();
					if(!this.isNew){
						var sd = this.row.find('td:first a[data-rr-name="subjectName"]');
						//获取文本值
						t.subject.name = sd.text();
						s.name = sd.text();
						//获取id
						t.subject.id = sd.attr('data-rr-value');
						s.id = sd.attr('data-rr-value');
						//获取显示科目id和科目
						//$('#subject option:selected').text("Id："+sd.attr('data-rr-value')+" "+"值："+t.subject.name);  
						t.teacherName = this.row.find('td:eq(1)').text();
						t.teacherAccount = this.row.find('td:eq(2)').text();
						t.subject = s;
					}
					myForm.show(t,s,this.isNew);
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