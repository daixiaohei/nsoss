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
	    // 浏览改大小
	    $(window).bind('resize', function () {
	         var url = window.location.href;
	         var pos = url.indexOf('#'); 
	         window.location = pos > -1 ? url.substring(0, pos) : url;
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
