/**
 * Title: 用户管理入口
 * Author: ZK
 * Date: 2014-07-10
 */
define('biz/busiDeviceSetting/js/busiDeviceSetting',function(require,exports,module){
	var Index = require('global/index');
	
	
	var $ = require('lib/jquery');
	var zBox = require('util/zbox');
	var J = require('util/j_jquery');
	var baseUrl = J.BaseUrl();
	
	var searchUrl = baseUrl+'/busiDeviceSetting/querybusiDeviceSettingByPage.do';
	var saveUrl = baseUrl+'/busiDeviceSetting/saveBusiDeviceSetting.do';//add or edit
	var deleteUrl = baseUrl+'/busiDeviceSetting/deleteById.do';
	var initBusiIdUrl = baseUrl+'/busiDeviceSetting/queryAllBusinessId.do';
	var initDeviceListIdUrl = baseUrl+'/busiDeviceSetting/queryAllDeviceListId.do';
	
	//表头
	var columns = [
        {title : '业务id',dataIndex :'busiId', width:'15%',elCls:'center'},
        {title : '加密密钥',dataIndex :'cryptKey', width:'15%',elCls:'center'},
        {title : '加密主密钥',dataIndex :'cryptKeyMain', width:'20%',elCls:'center'},
        {title : '设备列表id',dataIndex :'deviceListId', width:'20%',elCls:'center'},
        {title : '业务终端号',dataIndex :'deviceNo', width:'20%',elCls:'center'},
        {title : '业务商户号',dataIndex :'merchNo', width:'20%',elCls:'center'}

    ];
    
    //数据源
	var storeCFG = {
		url: searchUrl
	};
	
	var dataGrid = zBox.grid(columns,storeCFG);
	
	//用户操作 (CRUD) 
    var Operation = {
    	Condition: {merchNo:''},
    	data: {busiId:null,cryptKey:null,cryptKeyMain:null,deviceListId:null,deviceNo:null,merchNo:null},
    	initBusiId:function(){//初始化业务Id
    		$.get(initBusiIdUrl,{},function(ret){//level:1得到全国各市
    			$("#busiId").empty();
    			var options = '';
    			for(var d in ret){
    				
    				options += '<option  value="'+ret[d].id+'">'+ret[d].name+'</option>';
    			}    			
    			
        		$("#busiId").append(options);
        		$("#busiId").change();
        		
    		});
    		
    	},
    	initDeviceListId:function(){//初始化网点Id
    		$.get(initDeviceListIdUrl,{},function(ret){//level:1得到全国各市
    			$("#deviceListId").empty();
    			var options = '';
    			for(var d in ret){
    				
    				options += '<option  value="'+ret[d].id+'">'+ret[d].name+'</option>';
    			}    			
    			
        		$("#deviceListId").append(options);
        		$("#deviceListId").change();

    		});
    		
    	},
    	add: function(){//增
    		$("#obj").hide();$("#optObj").show();$("#optTitle").html("新增信用卡密码信息");
    	},
	    deletes: function(){//删
	      	var selections = dataGrid.grid.getSelection();
	        if(!Operation.valiSelection(selections)) return;
	        
	        zBox.confirm("确定要删除选中的城市吗？", "删除信用卡密码信息", function () {
	        	var loading = zBox.loading('正在处理....');
	        	var data = {id:selections[0].id};
	        	$.post(deleteUrl,data,function(ret){
		    			zBox.tip('删除成功！', 'success');
		    			dataGrid.store.load(Operation.Condition);
		    		});
	            return true;
	        });
	        return true;
	    },
	    edit: function(){//改
	    	var selections = dataGrid.grid.getSelection();
	    	if(!Operation.valiSelection(selections)) return;
	    	
	        $("#obj").hide();$("#optObj").show();$("#optTitle").html("编辑信用卡密码信息");
	        $("#busiId").val(selections[0].busiId).change();
	        $("#cryptKey").val(selections[0].cryptKey).change();
	        $("#cryptKeyMain").val(selections[0].cryptKeyMain).change();
	        $("#deviceListId").val(selections[0].deviceListId).change();
	        $("#deviceNo").val(selections[0].deviceNo).change();
	        $("#merchNo").val(selections[0].merchNo).change();
	        Operation.data.id = selections[0].id;
	    },
	    find: function(){//查
	    	dataGrid.store.load(Operation.Condition);
	    },
	    back: function(){//回退
	    	$("#optObj").hide();$("#obj").show();
	    	$("#busiId").val("");
	        $("#cryptKey").val("");
	        $("#cryptKeyMain").val("");
	        $("#deviceListId").val("");
	        $("#deviceNo").val("");
	        $("#merchNo").val("");
	        Operation.data.id = null;
	    },
	    submit: function(){//提交
	    	var loading = zBox.loading('正在处理....');
	    	var data = Operation.data;
	    	J.PostJson(saveUrl,data,function(ret){
	    		Operation.back();
	    		dataGrid.store.load(Operation.Condition);
	    		loading.close();
	    		zBox.tip('处理成功！', 'success');
	    	},function(){
	    		loading.close();
	    		zBox.tip('处理失败！', 'error');
	    	});
	    },
	    valiSelection: function(selections){//验证
	    	if (selections.length == 0) {
	            zBox.tip("请选择一个信用卡密码信息！","warning");
	            return false;
	        }
	        if (selections.length > 1) {
	            zBox.tip("只能选择一个信用卡密码信息！","warning");
	            return false;
	        }
	        return true;
	    }
    };
    //初始化界面
    Operation.initBusiId();
    Operation.initDeviceListId();
    //事件映射
    var eventMap = {
		"#addBtn click" : Operation.add,
		"#deleteBtn click" : Operation.deletes,
		"#editBtn click" : Operation.edit,
		"#searchBtn click" : Operation.find,
		"#backBtn click" : Operation.back,
		"#submitBtn click" : Operation.submit,
		"#srh_name change" : function(){Operation.Condition.merchNo = $(this).trim();},
		"#busiId change" : function(){Operation.data.busiId = $(this).trim();},
		"#cryptKey change" : function(){Operation.data.cryptKey = $(this).trim();},
		"#cryptKeyMain change" : function(){Operation.data.cryptKeyMain = $(this).trim();},
		"#deviceListId change" : function(){Operation.data.deviceListId = $(this).trim();},
		"#deviceNo change" : function(){Operation.data.deviceNo = $(this).trim();},
		"#merchNo change" : function(){Operation.data.merchNo = $(this).trim();},
	};
	
    J.FireEvent(eventMap);
    
});
	
