(function(){
	"use strict";
	define(['jquery','ajaxwrapper','ui'],function($,ajaxWrapper,ui){
		function o(){
			var self = this;
			var $outer = $('div.subject-container');
			ui.pretty($outer);
			ui.fileUpload($outer);
			
			$('#uploadForm').submit(function(){
				return false;
			});
			$('#upload').click(function(){
				if($('div.file-preview-filename').val() == 0){
					return;
				}
				ajaxWrapper.upload('examinee/upload','fileName',
						{beforeMsg:{tipText:"",show:true},successMsg:{tipText:"上传成功",show:true}},
						function(data){
							var table  = $('.table').find('thead').find('tr');
							table.empty();
							clearOption();
							addOption("全部");
							var length=data.title.length;
							for(var i=0;i<length;i++){
								table.append('<th>'+data.title[i]+'</th>');
								addOption(data.title[i]);
							}
						});
				
			});
			function addOption(value){
				$("select[name='studentNumber']").append('<option value='+value+'>'+value+'</option>');
				$("select[name='studentName']").append('<option value='+value+'>'+value+'</option>');
				$("select[name='gender']").append('<option value='+value+'>'+value+'</option>');
				$("select[name='nation']").append('<option value='+value+'>'+value+'</option>');
				$("select[name='birthday']").append('<option value='+value+'>'+value+'</option>');
				$("select[name='seatingNumber']").append('<option value='+value+'>'+value+'</option>');
				$("select[name='examinneUuid']").append('<option value='+value+'>'+value+'</option>');
				$("select[name='uuidType']").append('<option value='+value+'>'+value+'</option>');
				$("select[name='arts']").append('<option value='+value+'>'+value+'</option>');
				$("select[name='clazzName']").append('<option value='+value+'>'+value+'</option>');
				$("select[name='clazzCode']").append('<option value='+value+'>'+value+'</option>');
				$("select[name='absence']").append('<option value='+value+'>'+value+'</option>');
				$("select[name='totalScore']").append('<option value='+value+'>'+value+'</option>');
				$("select[name='roomNumber']").append('<option value='+value+'>'+value+'</option>');
				$("select[name='schoolName']").append('<option value='+value+'>'+value+'</option>');
				$("select[name='schoolCode']").append('<option value='+value+'>'+value+'</option>');
				$("select[name='districtNumber']").append('<option value='+value+'>'+value+'</option>');
				$("select[name='districtName']").append('<option value='+value+'>'+value+'</option>');
			}
			function clearOption(){
				$("select[name='studentNumber']").empty();
				$("select[name='studentName']").empty();
				$("select[name='gender']").empty();
				$("select[name='nation']").empty();
				$("select[name='birthday']").empty();
				$("select[name='seatingNumber']").empty();
				$("select[name='examinneUuid']").empty();
				$("select[name='uuidType']").empty();
				$("select[name='arts']").empty();
				$("select[name='clazzName']").empty();
				$("select[name='clazzCode']").empty();
				$("select[name='absence']").empty();
				$("select[name='totalScore']").empty();
				$("select[name='roomNumber']").empty();
				$("select[name='schoolName']").empty();
				$("select[name='schoolCode']").empty();
				$("select[name='districtNumber']").empty();
				$("select[name='districtName']").empty();
			}
			this.query = function(pager){
				logger.log('examinee.query');
				if(!pager){
					pager = _pager.concreator.init();
	        	}
				//TODO query
			};
			
			//加入页码块
			var _pager = new ui.pager().create('pager',self.query);
			
			
			$outer.show();
			function setData(){
				var options={
						student_name : $("select[name='studentName']").val(),
						student_number : $("select[name='studentNumber']").val(),
						gender : $("select[name='gender']").val(),
						nation : $("select[name='nation']").val(),
						birthday : $("select[name='birthday']").val(),
						seating_number : $("select[name='seatingNumber']").val(),
						examinne_uuid : $("select[name='examinneUuid']").val(),
						uuid_type : $("select[name='uuidType']").val(),
						arts : $("select[name='arts']").val(),
						clazz_name :  $("select[name='clazzName']").val(),
						clazz_code : $("select[name='clazzCode']").val(),
						absence  :  $("select[name='absence']").val(),
						total_score :  $("select[name='totalScore']").val(),
						room_number  :  $("select[name='roomNumber']").val(),
						school_name  :  $("select[name='schoolName']").val(),
						school_code  :  $("select[name='schoolCode']").val(),
						district_number  :  $("select[name='districtNumber']").val(),
						district_name  :  $("select[name='districtName']").val()
				}
				return options;
				
			}
			$('.import').click(function(){
				ajaxWrapper.postJson("examinee/importExaminee",setData(),
						{beforeMsg:{tipText:".",show:false},
					     successMsg:{tipText:"导入成功",show:true}},
						function(m){
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