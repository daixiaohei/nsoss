/**
 * Title: 用户管理入口
 * Author: ZK
 * Date: 2014-07-10
 */
define('biz/busiGroupDevice/js/busiGroupDevice',function(require,exports,module){
	var $ = require('lib/jquery');
	var zBox = require('util/zbox');
	var J = require('util/j_jquery');
	var baseUrl = J.BaseUrl();
	
	var searchUrl = baseUrl+'/business/queryBusiGroupDevice.do';
	var saveUrl = baseUrl+'/business/editBusiGroupDevice.do';//add or edit
	var deleteUrl = baseUrl+'/business/deleteBusiGroupDevice.do';
	var initBusiGroupNameUrl = baseUrl+'/business/findBusiGroupName.do';
	var initDeviceListNameUrl = baseUrl+'/business/findDeviceListName.do';
	
	//表头
	var columns = [
        {title : '业务分组名',dataIndex :'busiGroupName', width:'15%',elCls:'center'},
        {title : '设备列表名',dataIndex :'deviceListName', width:'15%',elCls:'center'}
    ];
    
    //数据源
	var storeCFG = {
		url: searchUrl
	};
	
	var dataGrid = zBox.grid(columns,storeCFG);
	
	//用户操作 (CRUD) 
    var Operation = {
    	Condition: {busiGroupName:''},
    	data: {id:null,busiGroupId:null,deviceListId:null},
    	initRole:function(){
    		$.get(initBusiGroupNameUrl,function(ret){
    			$("#busiGroupId").empty();
    			var options = '';
    			for(var d in ret){
    				options += '<option  value="'+ret[d].busiGroupId+'">'+ret[d].busiGroupName+'</option>';
    			}    			
        		$("#busiGroupId").append(options);
        		
    		});
    	},
    	initMenu:function(){
    		$.get(initDeviceListNameUrl,function(ret){
    			$("#deviceListId").empty();
    			var options = '';
    			for(var d in ret){
    				options += '<option  value="'+ret[d].deviceListId+'">'+ret[d].deviceListName+'</option>';
    			}    			
        		$("#deviceListId").append(options);
        		
    		});
    	},
    	add: function(){//增
    		$("#obj").hide();$("#optObj").show();$("#optTitle").html("新增业务分组设备");
    		$("#optObj").find('select').each(function(){
    			$(this).change();
    		});
    	},
	    deletes: function(){//删
	      	var selections = dataGrid.grid.getSelection();
	        if(!Operation.valiSelection(selections)) return;
	        
	        zBox.confirm("确定要删除选中的业务分组设备吗？", "删除业务分组设备", function () {
	        	var loading = zBox.loading('正在处理....');
	        	var data = {id:selections[0].id};
	            $.post(deleteUrl,data,function(ret){
	            	console.log(ret);
	            	dataGrid.store.load(Operation.Condition);
	            	loading.close();
	    			zBox.tip('删除成功！', 'success');
	    		});
	            return true;
	        });
	        return true;
	    },
	    edit: function(){//改
	    	var selections = dataGrid.grid.getSelection();
	    	if(!Operation.valiSelection(selections)) return;
	        $("#obj").hide();$("#optObj").show();$("#optTitle").html("编辑业务分组设备");
	        $("#optObj").find('input[type="text"]').each(function(){
	    		var name = $(this).attr('name');
	    		$(this).val(selections[0][name]).change();
	    	});
	        $("#busiGroupId").val(selections[0].busiGroupId).change();
	        $("#deviceListId").val(selections[0].deviceListId).change();
	        Operation.data.id = selections[0].id;
	    },
	    find: function(){//查
	    	dataGrid.store.load(Operation.Condition);
	    },
	    back: function(){//回退
	    	$("#optObj").hide();$("#obj").show();
	    	$("#roleId").val("");
	        $("#menuId").val("");
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
	            zBox.tip("请选择一个业务分组设备！","warning");
	            return false;
	        }
	        if (selections.length > 1) {
	            zBox.tip("只能选择一个业务分组设备！","warning");
	            return false;
	        }
	        return true;
	    }
    };
    
    Operation.initRole();
    Operation.initMenu();
    
    //事件映射
    var eventMap = {
		"#addBtn click" : Operation.add,
		"#deleteBtn click" : Operation.deletes,
		"#editBtn click" : Operation.edit,
		"#searchBtn click" : Operation.find,
		"#backBtn click" : Operation.back,
		"#submitBtn click" : Operation.submit,
		"#srh_busiGroupId change" : function(){Operation.Condition.busiGroupName = $(this).trim();},
		"#busiGroupId change" : function(){Operation.data.busiGroupId = $(this).trim();},
		"#deviceListId change" : function(){Operation.data.deviceListId = $(this).trim();},
	};
	
    J.FireEvent(eventMap);
    
});
	
