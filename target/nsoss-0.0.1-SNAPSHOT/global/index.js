/**
 * Author: zk
 * Date: 2014-07-10
 */
define('global/index',function(require,exports,module){
	var $ = require('lib/jquery');
	var J = require('util/j_jquery');
	var top = window.top;
	var baseUrl = J.BaseUrl();
	var memuUrl = baseUrl+'/user/loadMemu.do';
	
	var Index = {};
	Index.MenuIndex = 0;
	Index.MenuSpeed = 250;
	Index.Init = function () {
		var username = J.GetCookie('username');
		$("#user").html(username);
	    // 初始化高与宽
	    var win = $(window);
	    var width = win.width() - 182 - 0 - 7;
	    var height = win.height() - 64 - 2 - 33;
	    var height_c = height - 29 - 8 - 7;
	    $('#left').height(height);
	    $('#right').height(height).width(width);
	    $('#menu-container').height(height - 3);
	    $('#content-container').height(height_c);
	    $('#loading').css('padding-top', height_c / 2);
	
	    // 菜单切换效果
	    var tits = $('.menu-tit');
	    var lists = $('.menu-list').hide();
	    $(lists[0]).slideDown(Index.MenuSpeed);
	    $.each(tits, function (i, el) { $(el).attr('index', i); });
	    tits.click(function (e) {
	        var me = $(this);
	        var index = parseInt(me.attr('index'));
	        if (index != Index.MenuIndex) {
	            var last = Index.MenuIndex;
	            Index.MenuIndex = index;
	            $(lists[last]).slideUp(Index.MenuSpeed);
	            $(lists[index]).slideDown(Index.MenuSpeed);
	        }
	    });
	
	    // 菜单点击事件
	    var links = $('a[target=content]');
	    if (links.length > 0) {
	        links.bind('click', function (e) {
	            if (e.preventDefault)
	                e.preventDefault();
	            else
	                e.returnValue = false;
    			
	            Index.Open($(this).attr('href'));
    			Index.SetTitle($(this).html());
	            
	        });
	        //links[0].click();
	    }
	    Index.Open('welcome.html');
	 };
	
	 Index.Open = function (url) {
	 	var loading = top.document.getElementById('loading');
	 	var content = top.document.getElementById('content');
	    if (url != '') {
	        $(loading).show();
	        if (url.indexOf('#') == -1) {
	            url = url + (url.indexOf('?') == -1 ? '?___t=' : '&___t=') + Math.random();
	        } else {
	            var arr = url.split('#');
	            url = arr[0] + (arr[0].indexOf('?') == -1 ? '?___t=' : '&___t=') + Math.random() + '#' + arr[1];
	        }
	        $(content).hide().attr('src', url);
	    }
	 };
	
	 Index.SetTitle = function (title) {
		 $('#index-title').html(title);
	 };
	 
	 Index.loadMemu = function(){
	 	J.PostJson(memuUrl,{},function(data){
	 		var menuTitle = '<div class="menu-tit">{0}</div>';
	 		var menuList = '<div class="menu-list"><div class="top-line"></div><ul class="nav-items">{0}</ul></div>';
	 		var li0 = '',li1 = '';
	 		for(var i in data){
	 			if(data[i].type===0){
	 				li0 += '<li><a href="'+data[i].url+'" target="content">'+data[i].name+'</a></li>';
	 			}else if(data[i].type===1){
	 				li1 += '<li><a href="'+data[i].url+'" target="content">'+data[i].name+'</a></li>';
	 			}
	 		}
	 		var menuTitle0 = J.FormatString(menuTitle,'系统管理');
	 		var menuTitle1 = J.FormatString(menuTitle,'运营管理');
	 		var menuList0 = J.FormatString(menuList,li0);
	 		var menuList1 = J.FormatString(menuList,li1);
	 		var container = menuTitle1+menuList1+menuTitle0+menuList0;
	 		$('#menu-container').append(container);
	 		Index.Init();
	 	},function(){
	 		console.log('加载菜单失败.');
	 	});
	 };
	 
	 return Index;
});
