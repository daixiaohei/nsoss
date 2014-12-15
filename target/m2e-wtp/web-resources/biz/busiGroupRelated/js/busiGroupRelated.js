/**
 * Title: 用户管理入口
 * Author: ZK
 * Date: 2014-07-10
 */
define('biz/busiGroupRelated/js/busiGroupRelated',function(require,exports,module){
	var $ = require('lib/jquery');
	var zBox = require('util/zbox');
	var J = require('util/j_jquery');
	var baseUrl = J.BaseUrl();
	
	var searchUrl = baseUrl+'/business/queryBusiGroupRelated.do';
	var saveUrl = baseUrl+'/business/editBusiGroupRelated.do';//add or edit
	var deleteUrl = baseUrl+'/business/deleteBusiGroupRelated.do';
	var initBusiGroupNameUrl = baseUrl+'/business/findGroupName.do';
	var initBusiNameUrl = baseUrl+'/business/findBusiName.do';
	
	//表头
	var columns = [
        {title : '业务分组名',dataIndex :'busiGroupName', width:'15%',elCls:'center'},
        {title : '业务名称',dataIndex :'busiName', width:'15%',elCls:'center'}
    ];
    
    //数据源
	var storeCFG = {
		url: searchUrl
	};
	
	var dataGrid = zBox.grid(columns,storeCFG);
	
	//用户操作 (CRUD) 
    var Operation = {
    	Condition: {busiName:''},
    	data: {id:null,busiGroupId:null,busiId:null},
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
    		$.get(initBusiNameUrl,function(ret){
    			$("#busiId").empty();
    			var options = '';
    			for(var d in ret){
    				options += '<option  value="'+ret[d].busiId+'">'+ret[d].busiName+'</option>';
    			}    			
        		$("#busiId").append(options);
        		
    		});
    	},
    	add: function(){//增
    		$("#obj").hide();$("#optObj").show();$("#optTitle").html("新增分组关联业务");
    		$("#optObj").find('select').each(function(){
    			$(this).change();
    		});
    	},
	    deletes: function(){//删
	      	var selections = dataGrid.grid.getSelection();
	        if(!Operation.valiSelection(selections)) return;
	        
	        zBox.confirm("确定要删除选中的分组关联业务吗？", "删除分组关联业务", function () {
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
	        $("#obj").hide();$("#optObj").show();$("#optTitle").html("编辑分组关联业务");
	        $("#optObj").find('input[type="text"]').each(function(){
	    		var name = $(this).attr('name');
	    		$(this).val(selections[0][name]).change();
	    	});
	        $("#busiGroupId").val(selections[0].busiGroupId).change();
	        $("#busiId").val(selections[0].busiId).change();
	        Operation.data.id = selections[0].id;
	    },
	    find: function(){//查
	    	dataGrid.store.load(Operation.Condition);
	    },
	    back: function(){//回退
	    	$("#optObj").hide();$("#obj").show();
	    	$("#busiGroupId").val("");
	        $("#busiId").val("");
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
	            zBox.tip("请选择一个分组关联业务！","warning");
	            return false;
	        }
	        if (selections.length > 1) {
	            zBox.tip("只能选择一个分组关联业务！","warning");
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
		"#srh_busiId change" : function(){Operation.Condition.busiName = $(this).trim();},
		"#busiGroupId change" : function(){Operation.data.busiGroupId = $(this).trim();},
		"#busiId change" : function(){Operation.data.busiId = $(this).trim();},
	};
	
    J.FireEvent(eventMap);
    
});
	
