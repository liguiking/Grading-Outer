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
							addOption("无");
							var length=data.title.length;
							for(var i=0;i<length;i++){
								table.append('<th>'+data.title[i]+'</th>');
								addOption(data.title[i]);
							}
						});
				
			});
			function addOption(value){
				$("select[name='dataName']").each(function () {
					$(this).append('<option value='+value+'>'+value+'</option>');
		        });
			}
			function clearOption(){
				$("select[name='dataName']").each(function () {
					$(this).empty();
		        });
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
				var options = '({';
				$("select[name='dataName']").each(function () {
					options+=$(this).attr("data-name")+':"'+$(this).val()+'",';
		        });
				options=options.substring(0,options.length-1)
				options+='})';
				options = eval(options);
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