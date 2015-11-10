(function(){
	"use strict";
	define(['jquery','ajaxwrapper','ui'],function($,ajaxWrapper,ui){
		function o(){
			ui.fileUpload($('div.subject-container'));
			$('#uploadForm').submit(function(){
				return false;
			});
			$('#upload').click(function(){
				if($('div.file-preview-filename').val() == 0){
					return;
				}
				ajaxWrapper.upload('examinee/upload','fileName',{beforeMsg:{tipText:"",show:true},successMsg:{tipText:"上传成功",show:true}});
			});
		};

		return {
			render:function(){
				
				return new o();
			}
		};
	});
})();