/**
 * Title: 用户管理入口
 * Author: ZK
 * Date: 2014-07-10
 */
define('biz/deviceList/js/deviceList',function(require,exports,module){
	var Index = require('global/index');
	
	
	var $ = require('lib/jquery');
	var zBox = require('util/zbox');
	var J = require('util/j_jquery');
	var baseUrl = J.BaseUrl();
	
	var searchUrl = baseUrl+'/device/queryDeviceListByPage.do';
	var saveUrl = baseUrl+'/device/saveDeviceList.do';//add or edit
	var deleteUrl = baseUrl+'/device/deleteById.do';
	
	//表头
	var columns = [
        {title : '类型',dataIndex :'deviceType', width:'15%',elCls:'center'},
        {title : '名称',dataIndex :'name', width:'15%',elCls:'center'},
        {title : '编号',dataIndex :'ipAddr', width:'15%',elCls:'center'},
        {title : 'ip',dataIndex :'remoteAddr', width:'15%',elCls:'center'},
        {title : '用户名',dataIndex :'drvUsername', width:'15%',elCls:'center'},
        {title : '密码',dataIndex :'drvPwd', width:'20%',elCls:'center'},
        {title : '操作',elCls:'center',renderer : function(){return '<span class="grid-command btn-edit btnOper">设备明细</span>'}}

    ];
    
    //数据源
	var storeCFG = {
		url: searchUrl
	};
	
	var dataGrid = zBox.grid(columns,storeCFG);
	
	//用户操作 (CRUD) 
    var Operation = {
    	Condition: {name:''},
    	data: {deviceType:null,name:null,ipAddr:null,remoteAddr:null,drvUsername:null,drvPwd:null},
    	add: function(){//增
    		$("#obj").hide();$("#optObj").show();$("#optTitle").html("新增设备列表");
    	},
	    deletes: function(){//删
	      	var selections = dataGrid.grid.getSelection();
	        if(!Operation.valiSelection(selections)) return;
	        
	        zBox.confirm("确定要删除选中的设备列表吗？", "删除设备列表", function () {
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
	    	
	        $("#obj").hide();$("#optObj").show();$("#optTitle").html("编辑设备列表");
	        Operation.data.id = selections[0].id;
	        $("#optObj").find('input[type="text"]').each(function(){
	    		var name = $(this).attr('name');
	    		$(this).val(selections[0][name]).change();
	    	});
	    },
	    find: function(){//查
	    	dataGrid.store.load(Operation.Condition);
	    },
	    back: function(){//回退
	    	$("#optObj").hide();$("#obj").show();
	        Operation.data.id = null;
	        $("#optObj").find('input[type="text"]').each(function(){
	    		$(this).val('');
	    	});
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
	            zBox.tip("请选择一个设备列表！","warning");
	            return false;
	        }
	        if (selections.length > 1) {
	            zBox.tip("只能选择一个设备列表！","warning");
	            return false;
	        }
	        return true;
	    },
	    btnOper : function(){
	    	var selections = dataGrid.grid.getSelection();
	    	var id = selections[0].id;
	    	Index.Open('biz/partList/partList.html?id='+id);
	    }
    };
    
    //事件映射
    var eventMap = {
    	".btnOper click" : Operation.btnOper,
		"#addBtn click" : Operation.add,
		"#deleteBtn click" : Operation.deletes,
		"#editBtn click" : Operation.edit,
		"#searchBtn click" : Operation.find,
		"#backBtn click" : Operation.back,
		"#submitBtn click" : Operation.submit,
		"#srh_name change" : function(){Operation.Condition.name = $(this).trim();},
		"#name change" : function(){Operation.data.name = $(this).trim();},
		"#deviceType change" : function(){Operation.data.deviceType = $(this).trim();},
		"#ipAddr change" : function(){Operation.data.ipAddr = $(this).trim();},
		"#drvUsername change" : function(){Operation.data.drvUsername = $(this).trim();},
		"#drvPwd change" : function(){Operation.data.drvPwd = $(this).trim();},
		"#remoteAddr change" : function(){Operation.data.remoteAddr = $(this).trim();}
	};
	
    J.FireEvent(eventMap);
    
});
	
