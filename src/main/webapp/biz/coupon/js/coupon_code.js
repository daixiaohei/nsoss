/**
 * Title:  优惠券代码管理入口
 * Author: ZK
 * Date: 2014-09-24
 */
define('biz/coupon/js/coupon_code',function(require,exports,module){
	var $ = require('lib/jquery');
	var zBox = require('util/zbox');
	var J = require('util/j_jquery');
	var baseUrl = J.BaseUrl();
	
	var couponId = J.Request('couponId');
	
	var searchUrl = baseUrl+'/coupon/queryCouponCode.do';
	
	//表头
	var columns = [
        {title : '优惠券名',dataIndex :'couponName', width:'20%',elCls:'center'},
		{title : '券代码',dataIndex :'codeNo', width:'20%',elCls:'center'},
        {title : '使用状态',dataIndex :'status', width:'15%',elCls:'center'},
        {title : '打印时间',dataIndex :'printDate', width:'15%',elCls:'center'},
        {title : '使用时间',dataIndex :'useDate', width:'15%',elCls:'center'},
        {title : '金额',dataIndex :'couponMoney', width:'15%',elCls:'right'},
    ];
    
    //数据源
	var storeCFG = {
		url: searchUrl,
		params:{
			couponId:couponId
		}
	};
	
	var dataGrid = zBox.grid(columns,storeCFG);
	
	//用户操作 (CRUD) 
    var Operation = {
    	Condition: {couponId:couponId,codeNo:'',status:''},
	    find: function(){//查
	    	dataGrid.store.load(Operation.Condition);
	    }
    };
    
    //事件映射
    var eventMap = {
		"#searchBtn click" : Operation.find,
		"#srh_code change" : function(){Operation.Condition.codeNo = $(this).trim();},
		"#srh_status change" : function(){Operation.Condition.status = $(this).trim();}
	};
	
    J.FireEvent(eventMap);
    
});
	
