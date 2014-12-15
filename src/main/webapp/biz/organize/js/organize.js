/**
 * Title: 角色管理入口
 * Author: ZK
 * Date: 2014-07-10
 */
define('biz/organize/js/organize',function(require,exports,module){
	var $ = require('lib/jquery');
	var zBox = require('util/zbox');
	var J = require('util/j_jquery');
	var baseUrl = J.BaseUrl();
	
	var searchUrl = baseUrl+'/organize/queryOrganize.do';
	var saveUrl = baseUrl+'/organize/editOrganize.do';//add or edit
	var deleteUrl = baseUrl+'/organize/deleteOrganize.do';
	
	//表头
	var columns = [
        {title : '部门名称',dataIndex :'name', width:'15%',elCls:'center'},
        {title : '管理者',dataIndex :'manager', width:'15%',elCls:'center'},
        {title : '上级组织',dataIndex :'parentId', width:'15%',elCls:'center'}
    ];
    
	  //数据源
	var storeCFG = {
		url: searchUrl
	};
	
	var dataGrid = zBox.grid(columns,storeCFG);
	
	
	//用户操作 (CRUD) 
    var Operation = {
    	Condition: {name:''},
    	data: {id:null,manager:null,parentId:null},
    	add: function(){//增
    		$("#obj").hide();$("#optObj").show();$("#optTitle").html("新增部门");
    	},
	    deletes: function(){//删
	      	var selections = dataGrid.grid.getSelection();
	        if(!Operation.valiSelection(selections)) return;
	        
	        zBox.confirm("此操作不可恢复，确定要删除选中的部门吗？", "删除部门", function () {
	        	zBox.tip('正在处理....', 'loading');
	        	var data = {id:selections[0].id};
	            $.post(deleteUrl,data,function(ret){
	            	dataGrid.store.load(Operation.Condition);
	    			zBox.tip('删除成功！', 'success');
	    		});
	            return true;
	        });
	        return true;
	    },
	    edit: function(){//改
	    	var selections = dataGrid.grid.getSelection();
	    	if(!Operation.valiSelection(selections)) return;
	        $("#obj").hide();$("#optObj").show();$("#optTitle").html("编辑部门");
	        $("#name").val(selections[0].name).change();
	        $("#manager").val(selections[0].manager).change();
	        $("#parentId").val(selections[0].parentId).change();
	        Operation.data.id = selections[0].id;
	    },
	    find: function(){//查
	    	dataGrid.store.load(Operation.Condition);
	    },
	    back: function(){//回退
	    	$("#optObj").hide();$("#obj").show();
	    	$("#name").val("");
	        $("#manager").val("");
	        $("#parentId").val("");
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
	            zBox.tip("请选择一个部门！","warning");
	            return false;
	        }
	        if (selections.length > 1) {
	            zBox.tip("只能选择一个部门！","warning");
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
		"#srh_username change" : function(){Operation.Condition.name = $(this).trim();},
		"#name change" : function(){Operation.data.name = $(this).trim();},
		"#manager change" : function(){Operation.data.manager = $(this).trim();},
		"#parentId change" : function(){Operation.data.parentId = $(this).trim();}
	};
	
    J.FireEvent(eventMap);
    
});
	
