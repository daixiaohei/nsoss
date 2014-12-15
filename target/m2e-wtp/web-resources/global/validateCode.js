/**
 * Author: zk Date: 2014-07-10
 */
define('global/validateCode', function(require, exports, module) {
	var $ = require('lib/jquery');
	var J = require('util/j_jquery');
	var zBox = require('util/zbox');
	var baseUrl = J.BaseUrl();
	var indexUrl = baseUrl + '/index.html';

	var initCodeNoUrl = baseUrl + '/coupon/findCodeNo.do';
	var initCodeUrl = baseUrl + '/coupon/updateCodeNoStatus.do';

	var initCode = function() {
		var code = $("#codeNo").val();
		$.post(initCodeNoUrl, {codeNo : code}, function(ret) {
			var options = '';
			//alert(JSON.stringify(ret));
			if($.isEmptyObject(ret)){
				options=2;
				
			}else{		
			for ( var d in ret) {
				$("#id").val(ret[d].id);
				//alert("status:" + ret[d].status);
				//alert("id:" + ret[d].id);
				options += ret[d].status;
				}
			}
			if (options == "1") {
				$("#code").html("状态为：已送");
				$("#code").append("<br>"+'修改为：'+'<a href="javascript:void(0);" " class="button button-info ns-d-button" id="use" name="use" >已使用</a>');
			}
			if (options == "0") {
				$("#code").html("状态为：不存在");
				
			}
			if (options == "-1") {
				$("#code").html("状态为：已消费");
			}
			if (options == "2") {
				$("#code").html("状态为：不存在");
			}

		});
	};

	var initStatus = function() {
		var id=$("#id").val();
		//alert(id);
		$.post(initCodeUrl, {id : id}, function(ret) {
			$("#code").html("状态改为：已消费使用");

		});
	};

	// 事件映射
	var eventMap = {
		"#submitBtn click" : initCode,
		"#use click" : initStatus,

	};

	J.FireEvent(eventMap);
});
