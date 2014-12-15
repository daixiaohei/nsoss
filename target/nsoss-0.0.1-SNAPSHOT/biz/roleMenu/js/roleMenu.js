/**
 * Title: 用户管理入口
 * Author: ZK
 * Date: 2014-07-10
 */
define('biz/roleMenu/js/roleMenu',function(require,exports,module){
	var $ = require('lib/jquery');
	var zBox = require('util/zbox');
	var J = require('util/j_jquery');
	var baseUrl = J.BaseUrl();
	
	var searchUrl = baseUrl+'/organize/queryRoleMenu.do';
	var saveUrl = baseUrl+'/organize/editRoleMenu.do';//add or edit
	var deleteUrl = baseUrl+'/organize/deleteRoleMenu.do';
	var initRoleNameUrl = baseUrl+'/organize/findRoleName.do';
	
	//表头
	var columns = [
        {title : '角色名',dataIndex :'roleName', width:'15%',elCls:'center'},
        {title : '菜单名',dataIndex :'menuName', width:'15%',elCls:'center'}
    ];
    
    //数据源
	var storeCFG = {
		url: searchUrl
	};
	
	var dataGrid = zBox.grid(columns,storeCFG);
	
	//用户操作 (CRUD) 
    var Operation = {
    	Condition: {roleName:''},
    	data: {id:null,roleId:null,menuId:null},
    	initRole:function(){
    		$.get(initRoleNameUrl,function(ret){
    			$("#roleId").empty();
    			var options = '';
    			for(var d in ret){
    				options += '<option  value="'+ret[d].roleid+'">'+ret[d].roleName+'</option>';
    			}    			
        		$("#roleId").append(options);
        		
    		});
    	},
    	add: function(){//增
    		$("#obj").hide();$("#optObj").show();$("#optTitle").html("新增用户");
    	},
	    deletes: function(){//删
	      	var selections = dataGrid.grid.getSelection();
	        if(!Operation.valiSelection(selections)) return;
	        
	        zBox.confirm("确定要删除选中的用户吗？", "删除用户", function () {
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
	    	alert(selections[0].id);
	        $("#obj").hide();$("#optObj").show();$("#optTitle").html("编辑用户");
	        $("#roleId").val(selections[0].roleName).change();
	        $("#menuId").val(selections[0].menuName).change();
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
	            zBox.tip("请选择一个用户！","warning");
	            return false;
	        }
	        if (selections.length > 1) {
	            zBox.tip("只能选择一个用户！","warning");
	            return false;
	        }
	        return true;
	    }
    };
    
    Operation.initRole();
    
    //事件映射
    var eventMap = {
		"#addBtn click" : Operation.add,
		"#deleteBtn click" : Operation.deletes,
		"#editBtn click" : Operation.edit,
		"#searchBtn click" : Operation.find,
		"#backBtn click" : Operation.back,
		"#submitBtn click" : Operation.submit,
		"#srh_roleId change" : function(){Operation.Condition.roleName = $(this).trim();},
		"#roleId change" : function(){Operation.data.roleId = $(this).trim();},
		"#menuId change" : function(){Operation.data.menuId = $(this).trim();},
	};
	
    J.FireEvent(eventMap);
    
});
	
