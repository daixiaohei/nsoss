/**
 * Author: zk
 * Date: 2014-07-10
 */
define('global/admin',function(require,exports,module){
	var $ = require('lib/jquery');
	var J = require('util/j_jquery');
	var zBox = require('util/zbox');
	var baseUrl = J.BaseUrl();
	var loginUrl = baseUrl+'/login.html';
	
	var Admin = {};
	Admin.Loaded = false;
	Admin.Init = function () {
	    // 其它初始化
	    $('a').live('focus', function () { $(this).blur(); });
	    $('input[type=radio]').live('focus', function () { $(this).blur(); });
	    $('input[type=checkbox]').live('focus', function () { $(this).blur(); });
	    $('input[type=checkbox]').css('border', 'none');
	    $('.btn-middle').css({ 'margin-bottom': (J.IsIE6 ? 1 : 3) + 'px' }); //修改按钮水平对齐
	    // 隔行变色
	    $('table.data-table tr:even').addClass('even');
	
	    // 三态按钮
	    $('.btn')
	        .live('mousedown', function () { $(this).addClass('btn-active'); })
	        .live('mouseup', function () { $(this).removeClass('btn-active'); })
	        .live('mouseover', function () { $(this).addClass('btn-hover'); })
	        .live('mouseout', function () { $(this).removeClass('btn-active'); $(this).removeClass('btn-hover'); });
	    $('.btn-lit')
	        .live('mousedown', function () { $(this).addClass('btn-lit-active'); })
	        .live('mouseup', function () { $(this).removeClass('btn-lit-active'); })
	        .live('mouseover', function () { $(this).addClass('btn-lit-hover'); })
	        .live('mouseout', function () { $(this).removeClass('btn-lit-active'); $(this).removeClass('btn-lit-hover'); });
	
	    // 浏览改大小
	    $(window).bind('resize', function () {
	        if ((Admin.Loaded || $.browser.mozilla) && (Admin.IsLoginPage || Admin.IsIndexPage)) {
	            var url = window.location.href;
	            var pos = url.indexOf('#'); // 有#时不起作用
	            window.location = pos > -1 ? url.substring(0, pos) : url;
	        }
	        Admin.Loaded = true;
	    });
	};
	
	Admin.Logout = function () {
	    zBox.confirm('确定要退出系统吗？', '提示', function () {
	    	J.RemoveCookie('username');
	    	J.RemoveCookie('userid');
	        top.location.href = loginUrl;
	        return true;
	    });
	};
	
	Admin.SubmitForm = function (name) {
	    var form = (name == undefined || name == '') ? document.forms[0] : document.forms[name];
	    if (J.IsObject(form)) {
	        $('.btn-lit').setDisabled(true).click(function () { return false; });
	        if (zBox != undefined) {
	            zBox.tip('正在提交...', 'loading');
	        }
	        form.submit();
	    }
	};

	return Admin;
});
