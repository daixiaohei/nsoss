/**
 * @license Copyright (c) 2003-2014, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.html or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	config.skin = 'moono';
	config.extraPlugins = 'uicolor';
	config.uiColor = "";
	config.toolbar = "full";
	config.allowedContent = true;
	config.title="新闻编辑器";
	config.dialog_backgroundCoverOpacity =0.5
	config.toolbar_Basic =
	[
		[ 'Bold', 'Italic', '-', 'NumberedList', 'BulletedList', '-', 'Link', 'Unlink' ],
		[ 'FontSize', 'TextColor', 'BGColor' ],
		[ 'UIColor' ]
	]
	config.toolbarLocation ='top';

    //图片上传到服务器配置
    var pathName = window.document.location.pathname;
    //获取带"/"的项目名，如：/uimcardprj
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);

    config.filebrowserImageUploadUrl = projectName+'/news/upload.do'; //固定路径
};
