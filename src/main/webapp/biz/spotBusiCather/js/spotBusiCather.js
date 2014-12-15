/**
 * Title: 业务管理入口
 * Author: qt
 * Date: 2014-07-10
 */
define('biz/spotBusiCather/js/spotBusiCather',function(require,exports,module){
	var $ = require('lib/jquery');
	var zBox = require('util/zbox');
	var J = require('util/j_jquery');
	var baseUrl = J.BaseUrl();
	
	var searchUrl = baseUrl+'/analysis/querySpotBusiGather.do';
	var initBusiNameUrl = baseUrl+'/analysis/findBusiName.do';
	var initSpotNameUrl = baseUrl+'/analysis/findSpotName.do';
	var initCityNoUrl = baseUrl+'/analysis/findCityNo.do';

	//表头
	var columns = [
	    {title : '网点',dataIndex :'deviceName', width:'10%',elCls:'center'},
        {title : '业务类型',dataIndex :'busiName', width:'10%',elCls:'center'},
        {title : '成功笔数',dataIndex :'successCount', width:'10%',elCls:'center'},
        {title : '成功金额',dataIndex :'successMoney', width:'10%',elCls:'center'},
        {title : '人工退款笔数',dataIndex :'cancelCount', width:'10%',elCls:'center'},
        {title : '人工退款金额',dataIndex :'cancelMoney', width:'10%',elCls:'center'},
        {title : '自动退款笔数',dataIndex :'autoCount', width:'10%',elCls:'center'},
        {title : '自动退款金额',dataIndex :'autoMoney', width:'10%',elCls:'center'},
        {title : '失败不退款笔数',dataIndex :'failCount', width:'10%',elCls:'center'},
        {title : '失败交易金额',dataIndex :'failMoney', width:'10%',elCls:'center'}
    ];
    
    //数据源
	var storeCFG = {
		url: searchUrl,
	};

	//var dataGrid = zBox.grid(columns,storeCFG);
	
	//不使用分页
	var gridCFG = {
		bbar:{pagingBar:false}
	};
	
	var dataGrid = zBox.grid(columns,storeCFG,gridCFG);
	
	zBox.calendar({trigger:'.calendar1',showTime:true});
	zBox.calendar({trigger:'.calendar2',showTime:true});
	
	//用户操作 (CRUD) 
    var Operation = {
    	Condition: {busiId:'',cityNo:'',deviceListId:'',startDate:'',endDate:''},
    	data: {busiId:null,busiName:null,deviceListId:null,dviceName:null,successCount:null,successMoney:null,cancelCount:null,cancelMoney:null,autoCount:null,autoMoney:null,failCount:null,failMoney:null},
    	
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
    	} ,
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
    Operation.initCityNo();
    //事件映射
    var eventMap = {
		"#searchBtn click" : Operation.find,
		"#submitBtn click" : Operation.submit,
		"#busiId change" : function(){Operation.Condition.busiId = $(this).trim();},
		"#deviceListId change" : function(){Operation.Condition.deviceListId= $(this).trim();},
		"#cityNo change" : function(){Operation.Condition.cityNo= $(this).trim();},
		"#srh_startTime change" : function(){Operation.Condition.startDate = $(this).trim();},
		"#srh_endTime change" : function(){Operation.Condition.endDate = $(this).trim();}
	};
	
    J.FireEvent(eventMap);
    
});
	
