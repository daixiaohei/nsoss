/**
 * Title: 菜单管理入口
 * Author: ZK
 * Date: 2014-07-10
 */
define('biz/menu/js/menu',function(require,exports,module){
	var $ = require('lib/jquery');
	var zBox = require('util/zbox');
	var J = require('util/j_jquery');
	var baseUrl = J.BaseUrl();
	
	var searchUrl = baseUrl+'/organize/queryMenu.do';
	var saveUrl = baseUrl+'/organize/saveMenu.do';//add or edit
	var deleteUrl = baseUrl+'/organize/deleteMenu.do';
	
	//表头
	var columns = [
        {title : '菜单名',dataIndex :'name', width:'15%',elCls:'center'},
        {title : '地址',dataIndex :'url', width:'25%',elCls:'left'},
        {title : '类型',dataIndex :'type', width:'15%',elCls:'center'},
    ];
    
    //数据源
	var storeCFG = {
		url: searchUrl
	};
	
	var dataGrid = zBox.grid(columns,storeCFG);
	
	//用户操作 (CRUD) 
    var Operation = {
    	Condition: {name:''},
    	data: {id:null,name:null,url:null,type:null},
    	add: function(){//增
    		$("#obj").hide();$("#optObj").show();$("#optTitle").html("新增菜单");
    		Operation.data.type = 0;
    	},
	    deletes: function(){//删
	      	var selections = dataGrid.grid.getSelection();
	        if(!Operation.valiSelection(selections)) return;
	        
	        zBox.confirm("确定要删除选中的菜单吗？", "删除菜单", function () {
	        	var loading = zBox.loading('正在处理....');
	        	var data = {id:selections[0].id};
	            J.PostJson(deleteUrl,data,function(ret){
	            	console.log(ret);
	            	dataGrid.store.load(Operation.Condition);
	            	loading.close();
	    			zBox.tip('删除成功！', 'success');
	    		},function(){
	    			loading.close();
	    			zBox.tip('删除失败！', 'error');
	    		});
	            return true;
	        });
	        return true;
	    },
	    edit: function(){//改
	    	var selections = dataGrid.grid.getSelection();
	    	if(!Operation.valiSelection(selections)) return;
	    	
	        $("#obj").hide();$("#optObj").show();$("#optTitle").html("编辑菜单");
	        $("#optObj").find('input[type="text"]').each(function(){
	    		var name = $(this).attr('name');
	    		$(this).val(selections[0][name]).change();
	    	});
	    	if(selections[0].type === '系统管理')selections[0].type = 0;
	    	if(selections[0].type === '运营管理')selections[0].type = 1;
	    	$("#type").val(selections[0].type).change();
	        Operation.data.id = selections[0].id;
	    },
	    find: function(){//查
	    	dataGrid.store.load(Operation.Condition);
	    },
	    back: function(){//回退
	    	$("#optObj").hide();$("#obj").show();
	    	$("#optObj").find('input[type="text"]').each(function(){
	    		$(this).val('');
	    	});
	    	$("#type").val(0);
	        Operation.data.id = null;
	        Operation.data.type = null;
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
	            zBox.tip("请选择一个菜单！","warning");
	            return false;
	        }
	        if (selections.length > 1) {
	            zBox.tip("只能选择一个菜单！","warning");
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
		"#name change" : function(){Operation.data.name = $(this).trim();},
		"#url change" : function(){Operation.data.url = $(this).trim();},
		"#type change" : function(){Operation.data.type = $(this).trim();}
	};
	
    J.FireEvent(eventMap);
    
});
	
