/**
 * Title: 用户管理入口
 * Author: ZK
 * Date: 2014-07-10
 */
define('biz/user/js/user',function(require,exports,module){
	var $ = require('lib/jquery');
	var zBox = require('util/zbox');
	var J = require('util/j_jquery');
	var baseUrl = J.BaseUrl();
	
	var searchUrl = baseUrl+'/user/queryUser.do';
	var saveUrl = baseUrl+'/user/saveUser.do';//add or edit
	var deleteUrl = baseUrl+'/user/deleteUser.do';
	
	//表头
	var columns = [
        {title : '用户名',dataIndex :'name', width:'15%',elCls:'center'},
        {title : '昵称',dataIndex :'nameNick', width:'15%',elCls:'center'},
        {title : '密码',dataIndex :'password', width:'10%',elCls:'center'},
        {title : '邮件',dataIndex :'email', width:'20%',elCls:'center'},
        {title : '职务',dataIndex :'position', width:'20%',elCls:'center'},
        {title : '介绍',dataIndex :'intro', width:'20%',elCls:'center'}
    ];
    
    //数据源
	var storeCFG = {
		url: searchUrl
	};
	
	var dataGrid = zBox.grid(columns,storeCFG);
	
	//用户操作 (CRUD) 
    var Operation = {
    	Condition: {name:''},
    	data: {id:null,name:null,nameNick:null,password:null,position:null,intro:null,email:null,status:1},
    	add: function(){//增
    		$("#obj").hide();$("#optObj").show();$("#optTitle").html("新增用户");
    	},
	    deletes: function(){//删
	      	var selections = dataGrid.grid.getSelection();
	        if(!Operation.valiSelection(selections)) return;
	        
	        zBox.confirm("确定要删除选中的用户吗？", "删除用户", function () {
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
	    	
	        $("#obj").hide();$("#optObj").show();$("#optTitle").html("编辑用户");
	        $("#optObj").find('input[type="text"]').each(function(){
	    		var name = $(this).attr('name');
	    		$(this).val(selections[0][name]).change();
	    	});
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
	    },
	    btnOper : function(){
	    	var selections = dataGrid.grid.getSelection();
	    	alert(selections[0].name);
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
		"#nameNick change" : function(){Operation.data.nameNick = $(this).trim();},
		"#password change" : function(){Operation.data.password = $(this).trim();},
		"#position change" : function(){Operation.data.position = $(this).trim();},
		"#intro change" : function(){Operation.data.intro = $(this).trim();},
		"#email change" : function(){Operation.data.email = $(this).trim();}
	};
	
    J.FireEvent(eventMap);
    
});
	
