/**
 * Title: 用户管理入口
 * Author: ZK
 * Date: 2014-07-10
 */
define('biz/street/js/street',function(require,exports,module){
	var Index = require('global/index');
	
	var $ = require('lib/jquery');
	var zBox = require('util/zbox');
	var J = require('util/j_jquery');
	var baseUrl = J.BaseUrl();
	
	var searchUrl = baseUrl+'/street/queryStreetByPage.do';
	var saveUrl = baseUrl+'/street/saveStreet.do';//add or edit
	var deleteUrl = baseUrl+'/street/deleteById.do';
	
	//表头
	var columns = [
        {title : '名称',dataIndex :'name', width:'15%',elCls:'center'},
        {title : '地址',dataIndex :'address', width:'15%',elCls:'center'},
        {title : '全名',dataIndex :'nameFull', width:'20%',elCls:'center'},
        {title : '城市编号',dataIndex :'cityNo', width:'20%',elCls:'center'}
    ];
    
    //数据源
	var storeCFG = {
		url: searchUrl
	};
	
	var dataGrid = zBox.grid(columns,storeCFG);
	
	//用户操作 (CRUD) 
    var Operation = {
    	Condition: {name:''},
    	data: {id:null,name:null,address:null,nameFull:null,cityNo:null},
    	add: function(){//增
    		$("#obj").hide();$("#optObj").show();$("#optTitle").html("新增街道");
    	},
	    deletes: function(){//删
	      	var selections = dataGrid.grid.getSelection();
	        if(!Operation.valiSelection(selections)) return;
	        
	        zBox.confirm("确定要删除选中的街道吗？", "删除街道", function () {
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
	    	
	        $("#obj").hide();$("#optObj").show();$("#optTitle").html("编辑用户");
	        $("#name").val(selections[0].name).change();
	        $("#address").val(selections[0].address).change();
	        $("#nameFull").val(selections[0].nameFull).change();
	        $("#cityNo").val(selections[0].cityNo).change();
	        Operation.data.id = selections[0].id;
	    },
	    find: function(){//查
	    	dataGrid.store.load(Operation.Condition);
	    },
	    back: function(){//回退
	    	$("#optObj").hide();$("#obj").show();
	    	$("#name").val("");
	        $("#address").val("");
	        $("#nameFull").val("");
	        $("#cityNo").val("");
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
	            zBox.tip("请选择一条街道！","warning");
	            return false;
	        }
	        if (selections.length > 1) {
	            zBox.tip("只能选择一条街道！","warning");
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
		"#address change" : function(){Operation.data.address = $(this).trim();},
		"#nameFull change" : function(){Operation.data.nameFull = $(this).trim();},
		"#cityNo change" : function(){Operation.data.cityNo = $(this).trim();}
		
	};
	
    J.FireEvent(eventMap);
    
});
	
