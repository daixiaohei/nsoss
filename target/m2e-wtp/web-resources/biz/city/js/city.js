/**
 * Title: 用户管理入口
 * Author: ZK
 * Date: 2014-07-10
 */
define('biz/city/js/city',function(require,exports,module){
	var Index = require('global/index');
	
	
	var $ = require('lib/jquery');
	var zBox = require('util/zbox');
	var J = require('util/j_jquery');
	var baseUrl = J.BaseUrl();
	
	var searchUrl = baseUrl+'/city/queryCityByPage.do';
	var saveUrl = baseUrl+'/city/saveCity.do';//add or edit
	var deleteUrl = baseUrl+'/city/deleteById.do';
	
	//表头
	var columns = [
        {title : '名称',dataIndex :'cityName', width:'15%',elCls:'center'},
        {title : '城市编号',dataIndex :'cityNo', width:'15%',elCls:'center'},
        {title : '等级',dataIndex :'level', width:'20%',elCls:'center'},
        {title : '所属上级编号',dataIndex :'parentNo', width:'20%',elCls:'center'}

    ];
    
    //数据源
	var storeCFG = {
		url: searchUrl
	};
	
	var dataGrid = zBox.grid(columns,storeCFG);
	
	//用户操作 (CRUD) 
    var Operation = {
    	Condition: {name:''},
    	data: {cityName:null,cityNo:null,level:null,parentNo:null},
    	add: function(){//增
    		$("#obj").hide();$("#optObj").show();$("#optTitle").html("新增城市");
    	},
	    deletes: function(){//删
	      	var selections = dataGrid.grid.getSelection();
	        if(!Operation.valiSelection(selections)) return;
	        
	        zBox.confirm("确定要删除选中的城市吗？", "删除城市", function () {
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
	    	
	        $("#obj").hide();$("#optObj").show();$("#optTitle").html("编辑城市");
	        $("#cityName").val(selections[0].cityName).change();
	        $("#cityNo").val(selections[0].cityNo).change();
	        $("#level").val(selections[0].level).change();
	        $("#parentNo").val(selections[0].parentNo).change();
	        Operation.data.id = selections[0].id;
	    },
	    find: function(){//查
	    	dataGrid.store.load(Operation.Condition);
	    },
	    back: function(){//回退
	    	$("#optObj").hide();$("#obj").show();
	    	$("#cityName").val("");
	        $("#cityNo").val("");
	        $("#level").val("");
	        $("#parentNo").val("");
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
	            zBox.tip("请选择一个城市！","warning");
	            return false;
	        }
	        if (selections.length > 1) {
	            zBox.tip("只能选择一个城市！","warning");
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
		"#srh_name change" : function(){Operation.Condition.cityName = $(this).trim();},
		"#cityName change" : function(){Operation.data.cityName = $(this).trim();},
		"#cityNo change" : function(){Operation.data.cityNo = $(this).trim();},
		"#level change" : function(){Operation.data.level = $(this).trim();},
		"#parentNo change" : function(){Operation.data.parentNo = $(this).trim();}
	};
	
    J.FireEvent(eventMap);
    
});
	
