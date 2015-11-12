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
				ajaxWrapper.upload('examinee/upload','fileName',{beforeMsg:{tipText:"",show:true},successMsg:{tipText:"上传成功",show:true}});
			});
			
			this.query = function(pager){
				logger.log('examinee.query');
				if(!pager){
	        	    pager = ui.pager.init($outer);
	        	}
				//TODO query
			};
			
			ui.pager.render({
				"fn":self.query,
				"containerObj" : $outer,
				"pageObj" : $outer.find('#pager')
			});
			
			$outer.show();
		};

		return {
			render:function(){
				
				return new o();
			}
		};
	});
})();