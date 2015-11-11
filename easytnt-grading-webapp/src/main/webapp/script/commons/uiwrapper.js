(function() {
	"use strict";
	define([ 'jquery', 'dialog' ], function($, dialog) {
		var DialogSize = {SM:'sm',MD:'md',LG:'lg'};
		var button = {
				type : 'button',
				text :  "确定",
				clazz : 'btn-default'
		};
		var self ;
		var ui = self = {
				modal:function(title, content, size,buttons){
					var opts = {
							size : size||DialogSize.SM,
							header : {
								show : true,
								text : title||""
							},
							body :content||""
						};
					if($.isArray(buttons)){
						opts['footer'] = {
								show : true,
								buttons:[]
						};
						$.each(buttons,function(i){
							var btn = {};
							$.extend(true,btn,button,this);
							opts.footer.buttons[i] =btn;
						});
					}
					return dialog.modal(opts);
				},show:function(title,content, size){
					var opts = {
							size : size||DialogSize.SM,
							header : {
								show : true,
								text : title||""
							},
							footer:{show:false,buttons:[]},
							body :content||""
						};
					return dialog.modal(opts);
				},
				fileUpload:function(html,opts,callback){
					var fileInputCss = {
						position:"absolute",
						top:"0",
						right:"0",
						margin:"0",
						padding:"0",
						cursor:"pointer",
						opacity:"0",
						width:"110px",
						height:"34px",
						display:"inline"
					};
					var fileHtml = $(html);
	
					fileHtml.find('input[type=file]').css(fileInputCss);
					fileHtml.find('.file-preview-filename').css({"height":"36px"});
					fileHtml.find('.file-preview-clear').click(function(e){
						fileHtml.find('.file-preview-filename').val('');
						fileHtml.find('.file-preview-clear').hide();
						fileHtml.find('.file-preview-input input:file').val('');
						fileHtml.find('.file-preview-input-title').text('请选择文件');
						fileHtml.find('button.btn-import').removeAttr('disabled');
						fileHtml.find('#SheetNames')[0].style.display = 'none';
					});
					
					fileHtml.find('.file-preview-input input[type=file]').change(function(e){
						var file = this.files[0];
						
						var reader = new FileReader();
						reader.onload = function(e){
							fileHtml.find('.file-preview-input-title').text('更换文件');
							fileHtml.find('.file-preview-clear').show();
							fileHtml.find('.file-preview-filename').val(file.name);
							fileHtml.find('button.btn-import').removeAttr('disabled');
						};
						reader.readAsDataURL(file);
					});
				}
		};
		return ui;
	});
})();