/**
 * Title: 业务分组管理入口
 * Author: qt
 * Date: 2014-07-10
 */
define('biz/busiGroup/js/busiGroup',function(require,exports,module){
	var $ = require('lib/jquery');
	var zBox = require('util/zbox');
	var J = require('util/j_jquery');
	var baseUrl = J.BaseUrl();
	
	var searchUrl = baseUrl+'/business/queryBusiGroup.do';
	var saveUrl = baseUrl+'/business/editBusiGroup.do';//add or edit
	var deleteUrl = baseUrl+'/business/deleteBusiGroup.do';
	
	//表头
	var columns = [
        {title : '业务分组名称',dataIndex :'name', width:'15%',elCls:'center'},
        {title : '创建时间',dataIndex :'createdAt', width:'15%',elCls:'center'},
        {title : '更新时间',dataIndex :'updatedAt', width:'15%',elCls:'center'}
    ];
    
    //数据源
	var storeCFG = {
		url: searchUrl,
	};

	zBox.calendar({trigger:'.calendar1'});
	zBox.calendar({trigger:'.calendar2'});
	
	var dataGrid = zBox.grid(columns,storeCFG);
	
	//用户操作 (CRUD) 
    var Operation = {
    	Condition: {name:''},
    	data: {id:null,name:null,createdAt:null,updatedAt:null},
    	add: function(){//增
    		$("#obj").hide();$("#optObj").show();$("#optTitle").html("新增业务分组");
    		
    	},
	    deletes: function(){//删
	      	var selections = dataGrid.grid.getSelection();
	        if(!Operation.valiSelection(selections)) return;
	        
	        zBox.confirm("此操作不可恢复，确定要删除选中的业务分组吗？", "删除业务分组", function () {
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

	        $("#obj").hide();$("#optObj").show();$("#optTitle").html("编辑业务分组");

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
	    		$(this).val('').change();
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
	            zBox.tip("请选择一个业务分组！","warning");
	            return false;
	        }
	        if (selections.length > 1) {
	            zBox.tip("只能选择一个业务分组！","warning");
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
		"#createdAt change" : function(){Operation.data.createdAt = $(this).trim();},
		"#updatedAt change" : function(){Operation.data.updatedAt = $(this).trim();}
		
	};
	
    J.FireEvent(eventMap);
    
});
	
