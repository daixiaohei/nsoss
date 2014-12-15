/**
 * Author: zk
 * Date: 2014-07-10
 */
define('global/pager',function(require,exports,module){
	var $ = require('lib/jquery');
	var J = require('util/j_jquery');
	var Index = require('global/index');
	var Pager = {};
	Pager.Data = {};
	
	Pager.Output = function (urlFormat, pageSize, pageIndex, pageCount, recordCount) {
	    pageSize = parseInt(pageSize, 10);
	    pageIndex = parseInt(pageIndex, 10);
	    pageCount = parseInt(pageCount, 10);
	    recordCount = parseInt(recordCount, 10);
	
	    if (pageIndex < 1)
	        pageIndex = 1;
	    if (pageIndex > pageCount)
	        pageIndex = pageCount;
	
	    Pager.Data.urlFormat = urlFormat;
	    Pager.Data.pageCount = pageCount;
	
	    function _getLink(id,text, enabled, urlFormat, index) {
	        if (enabled == false)
	            return J.FormatString(' <a class="button-white" style="filter:Alpha(Opacity=60);opacity:0.6;" href="javascript:void(0);"><span>{0}</span></a>', text);
	        else
	            return J.FormatString(' <a class="button-white" pageIndex="{0}" id="{1}" href="javascript:void(0);"><span>{2}</span></a>', index,id, text);
	    }
	    
	    function _bindEvent(id){
			$("#"+id).click(function(){
				var pageIndex = $(this).attr("pageIndex");
				Index.Open(J.FormatString(Pager.Data.urlFormat, pageIndex));
			});
		}
	
		function _Jump() {
	    	var index = $('#current-index').trim();
	    	if (J.IsIntPositive(index) == false || parseInt(index) < 1 || parseInt(index) > Pager.Data.pageCount) {
	        	$('#current-index').val('').focus();
	        	return;
	    	}
	    	Index.Open(J.FormatString(Pager.Data.urlFormat, index));
		}
		
	    var html = [];
	    html.push(J.FormatString('<div class="msg">共{0}条记录，当前第{1}/{2}，每页{3}条记录</div>', recordCount, pageIndex, pageCount, pageSize));
	    html.push(_getLink('firstBtn','首页', pageIndex > 1, urlFormat, 1));
	    html.push(_getLink('preBtn','上一页', pageIndex > 1, urlFormat, pageIndex - 1));
	    html.push(_getLink('nextBtn','下一页', pageCount > 0 && pageIndex < pageCount, urlFormat, pageIndex + 1));
	    html.push(_getLink('lastBtn','未页', pageCount > 0 && pageIndex < pageCount, urlFormat, pageCount));
	    html.push('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;');
	    html.push('第<input id="current-index" class="input-small" style="text-align:center;" type="text" value="' + (pageIndex > 0 ? pageIndex : '') + '" />页');
	    html.push('&nbsp;&nbsp;&nbsp;&nbsp;');
	    html.push('<a class="button-white"' + (pageCount <= 1 ? ' style="filter:Alpha(Opacity=60);opacity:0.6;" ' : ' id="jumpBtn"') + ' href="javascript:void(0);"><span>跳转</span></a>');
		
		$(".pager-bar").append(html.join(''));
		
		_bindEvent("firstBtn");
		_bindEvent("preBtn");
		_bindEvent("nextBtn");
		_bindEvent("lastBtn");
		
		$("#jumpBtn").click(function(){_Jump();});
	};
	
	return Pager;
	
})
