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
	
	return {
		isLogin:function(){
			if(top.location.href === loginUrl | top.location.href === baseUrl)return;
	 		var username = J.GetCookie('username');
    		if(!username){
    			top.location.href = loginUrl;
    			return false;
    		}
    		return true;
		},
		loaded:function(){
			if(top.location.href === loginUrl | top.location.href === baseUrl)return;
			$('body').hide();
			var loaded = $('<div id="loaded"><img src='+baseUrl+'/global/images/loading8.gif alt="loading" border="0" /></div>');
			$('body').after(loaded);
			$('body').get(0).onload = function(){
				var content = top.document.getElementById('content');
				if(content) $(content).show();
				setTimeout(function(){
					$('body').show();loaded.hide();
				},700);
			};
		}
	};
});