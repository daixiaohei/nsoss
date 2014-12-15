/**
 * title：全局过滤器
 * Author: zk
 * Date: 2014-07-10
 */
define('lib/filter',function (require, exports, module) {
	var $ = require('lib/jquery');
	var J = require('util/j_jquery');
	var baseUrl = J.BaseUrl()+'/';
	var loginUrl = baseUrl+'login.html';
	var validateCodeUrl = baseUrl+'validateCode.html';
	
	return {
		isLogin:function(){
			if(top.location.href === loginUrl | top.location.href === baseUrl | top.location.href === validateCodeUrl)return;
	 		var username = J.GetCookie('username');
    		if(!username){
    			top.location.href = loginUrl;
    			return false;
    		}
    		return true;
		},
		isLoad:function(){
			if(top.location.href === loginUrl | top.location.href === baseUrl)return;
			
			var content = top.document.getElementById('content');
			
			var loaded = $('<div id="loaded"><img src='+baseUrl+'/global/images/loading8.gif alt="loading" border="0" /></div>');
			
			var win = $(window),
			height = win.height(),
			pt = (height-50)/2;
			
			if(content) $(content).show();
			
			var loadCss = {
				'text-align':'center',
				'padding-top':pt
			};
			
			loaded.css(loadCss);
			
			$('body').hide().before(loaded);
			
			window.onload = function(){
				$('body').show(),loaded.hide();
			};
		}
	};
});