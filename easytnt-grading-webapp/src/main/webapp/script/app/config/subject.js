(function(){
	"use strict";
	define(['jquery','ajaxwrapper'],function($,ajaxWrapper){
		var subjectExam = function(){
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
			this.show = function(examPaper,subject,subjectExam){
				for(var o in examPaper){
					editorForm.find('#'+o).val(examPaper[o]);
				}
				editorForm.find(':text:first').focus();
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
									alert("保存成功");
								}
							});
				}

			};
			var self = this;
			editorForm.submit(function(){
				self.save();
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
					e.fullScore = myForm.find('#fullScore').val();
					e.objectivityScore = myForm.find('#objectivityScore').val();
					e.subjectivityScore = myForm.find('#subjectivityScore').val();
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
						s.subjectCode = sd.attr('data-rr-value');;
						e.fullScore = this.row.find('td:eq(3)').text();
						e.objectivityScore = this.row.find('td:eq(4)').text();
						e.subjectivityScore = this.row.find('td:eq(5)').text();
						se.subject = s;
						se.usedPaper = [e];
					}
					myForm.show(e,s,se);
				}
			};

			myTable.on('click','tbody #newSubject',function(e){
				currentSubject.isNew = true;
				currentSubject.row = $(this).parent().parent();
				currentSubject.show();
			}).on('click','tbody a[data-rr-name="subjectName"]',function(e){
				currentSubject.isNew = false;
				currentSubject.row = $(this).parent().parent();
				currentSubject.show();
			});
		};

		return {
			render:function(){
				
				return new o();
			}
		};
	});
})();