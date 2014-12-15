/**
 * Title: 用户管理入口
 * Author: ZK
 * Date: 2014-07-10
 */
define('biz/part/js/part',function(require,exports,module){
	var Index = require('global/index');
	
	
	var $ = require('lib/jquery');
	var zBox = require('util/zbox');
	var J = require('util/j_jquery');
	var baseUrl = J.BaseUrl();
	
	var searchUrl = baseUrl+'/part/queryPartByPage.do';
	var saveUrl = baseUrl+'/part/editpart.do';//add or edit
	var deleteUrl = baseUrl+'/part/deleteById.do';
	
	//表头
	var columns = [
        {title : '设备id',dataIndex :'deviceId', width:'15%',elCls:'center'},
        {title : '逻辑类型',dataIndex :'logicType', width:'15%',elCls:'center'},
        {title : '名称',dataIndex :'name', width:'20%',elCls:'center'},
        {title : '设备类型',dataIndex :'partType', width:'20%',elCls:'center'},
        {title : '售卖者',dataIndex :'vender', width:'20%',elCls:'center'},
        {title : '设备编号',dataIndex :'partNo', width:'20%',elCls:'center'}

    ];
    
    //数据源
	var storeCFG = {
		url: searchUrl
	};
	
	var dataGrid = zBox.grid(columns,storeCFG);
	
	//用户操作 (CRUD) 
    var Operation = {
    	Condition: {name:''},
    	data: {deviceId:null,logicType:null,name:null,partType:null,vender:null,partNo:null},
    	add: function(){//增
    		$("#obj").hide();$("#optObj").show();$("#optTitle").html("新增通用设备");
    	},
	    deletes: function(){//删
	      	var selections = dataGrid.grid.getSelection();
	        if(!Operation.valiSelection(selections)) return;
	        
	        zBox.confirm("确定要删除选中的通用设备吗？", "删除通用设备", function () {
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
	    	
	        $("#obj").hide();$("#optObj").show();$("#optTitle").html("编辑设备");
	        $("#deviceId").val(selections[0].deviceId).change();
	        $("#logicType").val(selections[0].logicType).change();
	        $("#name").val(selections[0].name).change();
	        $("#partType").val(selections[0].partType).change();
	        $("#vender").val(selections[0].vender).change();
	        $("#partNo").val(selections[0].partNo).change();
	        Operation.data.id = selections[0].id;
	    },
	    find: function(){//查
	    	dataGrid.store.load(Operation.Condition);
	    },
	    back: function(){//回退
	    	$("#optObj").hide();$("#obj").show();
	    	$("#deviceId").val("");
	        $("#logicType").val("");
	        $("#name").val("");
	        $("#partType").val("");
	        $("#vender").val("");
	        $("#partNo").val("");
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
	            zBox.tip("请选择一个设备！","warning");
	            return false;
	        }
	        if (selections.length > 1) {
	            zBox.tip("只能选择一个设备！","warning");
	            return false;
	        }
	        return true;
	    }
    };
    
    //事件映射
    var eventMap = {
		"#addBtn click" : Operation.add,
		"#deleteBtn click" : Operation.deletes,
		"#editBtn click" : Operation.edit,
		"#searchBtn click" : Operation.find,
		"#backBtn click" : Operation.back,
		"#submitBtn click" : Operation.submit,
		"#srh_name change" : function(){Operation.Condition.name = $(this).trim();},
		"#deviceId change" : function(){Operation.data.deviceId = $(this).trim();},
		"#logicType change" : function(){Operation.data.logicType = $(this).trim();},
		"#name change" : function(){Operation.data.name = $(this).trim();},
		"#vender change" : function(){Operation.data.vender = $(this).trim();},
		"#partNo change" : function(){Operation.data.partNo = $(this).trim();},
		"#partType change" : function(){Operation.data.partType = $(this).trim();}
	};
	
    J.FireEvent(eventMap);
    
});
	
