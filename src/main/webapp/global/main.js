/**
 * Author: zk
 * Date: 2014-07-10
 */
define(function(require,exports,module){
	var $ = require('lib/jquery');
	var Admin = require('global/admin');
	var Index = require('global/index');
	
	
    // 初始化
	$(function () {
		
    	Admin.Init();
    	Index.loadMemu();
    	
    	$("#logoutBtn").click(function(){Admin.Logout();});
    	$("#welcomeBtn").click(function(){Index.Open('welcome.html');});
	});
    
});
