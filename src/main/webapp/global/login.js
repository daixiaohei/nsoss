/**
 * Author: zk
 * Date: 2014-07-10
 */
define('global/login',function(require,exports,module){
	var $ = require('lib/jquery');
	var J = require('util/j_jquery');
	var zBox = require('util/zbox');
	var baseUrl = J.BaseUrl();
	var indexUrl = baseUrl+'/index.html';
	var Login = {};
	
	VerifyImageUrl = '';
	LoginUrl = baseUrl+'/user/login.do';
	
	Login.Init = function () {
	    if ($.browser.msie) {
	        var height = $(document).height() - 338;
	        if (J.IsIE6) {
	            height = height - 20;
	        }
	        $('#login_auto_height').css({ height: height - ($('#login_form tr').length - 2) * 38 - 53 + 30 });
	    }
	    // 验证码
	    $('#refresh_verify_image').click(Login.LoadCaptcha);
	    $('#verify_image').click(Login.LoadCaptcha).load(function () { $(this).show(); });
	    Login.LoadCaptcha();
	    // 提交登录
	    $('#login_btn').click(Login.Submit);
	    $('#password').keydown(function (event) { J.EnterSubmit(event, Login.Submit); });
	    $('#verifycode').keydown(function (event) { J.EnterSubmit(event, Login.Submit); });
	    $('#username').keydown(function (event) { J.EnterSubmit(event, Login.Submit); }).focus();
	};
	
	Login.LoadCaptcha = function () {
	    $('#verify_image').attr('src', VerifyImageUrl + '?t=' + Math.random());
	};
	
	Login.Tip = function (text, icon) {
	    zBox.tip(text, icon, { top: '100px' });
	};
	
	Login.loading = function(){
		$(".login-load").hide();$(".login-loading").show();$("#login_btn").setDisabled(true);
	};
	
	Login.loaded = function(){
		$(".login-load").show();$(".login-loading").hide();$("#login_btn").setDisabled(false);
	};
	
	Login.Submit = function () {
	    var username = $('#username').trim();
	    var password = $('#password').trim();
	    var verifycode = $('#verifycode').trim();
	    var rememberMe = $('#rememberMe').get(0).checked;
	
	    if (username == '') {
	        Login.Tip('请输入用户名！', 'warning');
	        $('#username').focus();
	        return;
	    }
	    if (password == '') {
	        Login.Tip('请输入密码！', 'warning');
	        $('#password').focus();
	        return;
	    }
	    if (verifycode == '') {
	        Login.Tip('请输入验证码！', 'warning');
	        $('#verifycode').focus();
	        return;
	    }
	    var data = { name: username, password: password, rememberMe: rememberMe, verifycode: verifycode };
	
	    //直接到首页
	
	    Login.loading();
	    J.PostJson(LoginUrl, data, function (data) {
	    	Login.loaded();
	        if (data.code === '0') {
	        	if(rememberMe){
	        		J.SetCookie("username",data.user.name,24*7);
	        		J.SetCookie("userid",data.user.id,24*7);
	        	}else{
	        		J.SetCookie("username",data.user.name);
	        		J.SetCookie("userid",data.user.id);
	        	}
	            top.location.href = indexUrl;
	        } else {
	            Login.LoadCaptcha();
	            if (data.code === '1') {
	                Login.Tip('验证码已失效，请重新输入！', 'error');
	                $('#verifycode').val('').focus();
	            } else if (data.code === '2') {
	                Login.Tip('验证码错误，请重新输入！', 'error');
	                $('#verifycode').select();
	            } else {
	                Login.Tip(data.message, 'error');
	                $('#username').select();
	            }
	        }
	    }, function () {
	    	Login.loaded();
	        Login.Tip('登录出错！', 'error');
	    });
	};
	
	Login.Init();
	
});
