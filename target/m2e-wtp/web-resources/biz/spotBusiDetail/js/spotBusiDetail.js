/**
 * Title: 业务管理入口
 * Author: qt
 * Date: 2014-07-10
 */
define('biz/spotBusiDetail/js/spotBusiDetail',function(require,exports,module){
	var $ = require('lib/jquery');
	var zBox = require('util/zbox');
	var J = require('util/j_jquery');
	var baseUrl = J.BaseUrl();
	
	var searchUrl = baseUrl+'/analysis/querySpotBusiDetail.do';
	var initBusiNameUrl = baseUrl+'/business/findBusiName.do';
	var initSpotNameUrl = baseUrl+'/analysis/findSpotName.do';
	var initBusiStatusUrl = baseUrl+'/analysis/findBusiStatus.do';
	var initCityNoUrl = baseUrl+'/analysis/findCityNo.do';

	//表头
	var columns = [
        {title : '业务ID',dataIndex :'busiId', width:'10%',elCls:'center'},
	    {title : '网点',dataIndex :'deviceName', width:'10%',elCls:'center'},
        {title : '业务名称',dataIndex :'busiName', width:'10%',elCls:'center'},
        {title : '交易金额',dataIndex :'payMoney', width:'10%',elCls:'center'},
        {title : '支付状态',dataIndex :'payStatus', width:'10%',elCls:'center'},
        {title : '错误代码',dataIndex :'errorCode', width:'10%',elCls:'center'},
        {title : '业务交易状态',dataIndex :'busiStatus', width:'10%',elCls:'center'},
        {title : '逻辑代码',dataIndex :'busiLogicNo', width:'10%',elCls:'center'},
        {title : '支付时间',dataIndex :'payTime', width:'10%',elCls:'center'},
        {title : '银行账号',dataIndex :'bankAcctNo', width:'10%',elCls:'center'},
        {title : '支付终端',dataIndex :'posDeviceNo', width:'10%',elCls:'center'},
        {title : '支付记录',dataIndex :'posRecordNo', width:'10%',elCls:'center'},
        {title : '时间',dataIndex :'createTime', width:'10%',elCls:'center'}
    ];
    
    //数据源
	var storeCFG = {
		url: searchUrl,
		params:{
			startDate:'2014-05-5 16:33:00',
			endDate:'2014-06-08 16:33:00'
		}
	};

	//var dataGrid = zBox.grid(columns,storeCFG);
	
	//不使用分页
	var gridCFG = {
		bbar:{pagingBar:false}
	};
	
	var dataGrid = zBox.grid(columns,storeCFG,gridCFG);
	
	zBox.calendar({trigger:'.calendar1',showTime:true});
	zBox.calendar({trigger:'.calendar2',showTime:true});
	
	$('.calendar1').val('2014-05-5 16:33:00');
	$('.calendar2').val('2014-06-08 16:33:00');
	
	//用户操作 (CRUD) 
    var Operation = {
    	Condition: {cityNo:'',deviceListId:'',busiId:'',statusId:'',startDate:'',endDate:''},
    	data: {busiName:null,deviceName:null,busiId:null,deviceListId:null,statusId:null,payMoney:null,payStatus:null,errorCode:null,busiStatus:null,busiLogicNo:null,payTime:null,bankAcctNo:null,posDeviceNo:null,posRecordNo:null,createTime:null},
    	
	    find: function(){//查
	    	dataGrid.store.load(Operation.Condition);
	    },
	    initName:function(){
    		$.get(initBusiNameUrl,function(ret){
    			$("#busiId").empty();
    			var options = '';
    			options+='<option  value="">全部</option>';
    			for(var d in ret){
    				options += '<option  value="'+ret[d].busiId+'">'+ret[d].busiName+'</option>';
    			}    
    			
        		$("#busiId").append(options);
        		
    		});
    	},
    	initSpotName:function(){
    		$.get(initSpotNameUrl,function(ret){
    			$("#deviceListId").empty();
    			var options = '';
    			options+='<option  value="">全部</option>';
    			for(var d in ret){
    				options += '<option  value="'+ret[d].deviceListId+'">'+ret[d].deviceName+'</option>';
    			}    
    			
        		$("#deviceListId").append(options);
        		
    		});
    	},
    	initBusiStatus:function(){
    		$.get(initBusiStatusUrl,function(ret){
    			
    			$("#busiStatus").empty();
    			var options = '';
    			options+='<option  value="">全部</option>';
    			for(var d in ret){
    				options += '<option  value="'+ret[d].statusId+'">'+ret[d].busiStatus+'</option>';
    			}    
    			
        		$("#busiStatus").append(options);
        		
        		
    		});
    	},
    	initCityNo:function(){
    		$.get(initCityNoUrl,function(ret){
    			$("#cityNo").empty();
    			var options = '';
    			options+='<option  value="">全部</option>';
    			for(var d in ret){
    				options += '<option  value="'+ret[d].cityNo+'">'+ret[d].cityNo+'</option>';
    			}    
    			
        		$("#cityNo").append(options);
        		
    		});
    	}
	    
	   
    };
    Operation.initName();
    Operation.initSpotName();
    Operation.initBusiStatus();
    Operation.initCityNo();
    //事件映射
    var eventMap = {
		"#searchBtn click" : Operation.find,
		"#submitBtn click" : Operation.submit,
		"#busiId change" : function(){Operation.Condition.busiId = $(this).trim();},
		"#cityNo change" : function(){Operation.Condition.cityNo= $(this).trim();},
		"#deviceListId change" : function(){Operation.Condition.deviceListId= $(this).trim();},
		"#busiStatus change" : function(){Operation.Condition.statusId= $(this).trim();},
		"#srh_startTime change" : function(){Operation.Condition.startDate = $(this).trim();},
		"#srh_endTime change" : function(){Operation.Condition.endDate = $(this).trim();}
	};
	
    J.FireEvent(eventMap);
    
});
	
