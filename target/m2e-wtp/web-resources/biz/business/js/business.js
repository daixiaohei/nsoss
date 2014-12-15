/**
 * Title: 业务管理入口
 * Author: qt
 * Date: 2014-07-10
 */
define('biz/business/js/business',function(require,exports,module){
	var $ = require('lib/jquery');
	var zBox = require('util/zbox');
	var J = require('util/j_jquery');
	var baseUrl = J.BaseUrl();
	
	var searchUrl = baseUrl+'/business/queryBusiness.do';
	var saveUrl = baseUrl+'/business/editBusiness.do';//add or edit
	var deleteUrl = baseUrl+'/business/deleteBusiness.do';
	
	//表头
	var columns = [
        {title : '业务名称',dataIndex :'name', width:'10%',elCls:'center'},
        {title : '业务编号',dataIndex :'busiNo', width:'10%',elCls:'center'},
        //{title : '业务版本',dataIndex :'busiVersion', width:'15%',elCls:'center'},
        {title : '备注',dataIndex :'memo', width:'15%',elCls:'center'},
        {title : '平台URL地址',dataIndex :'url', width:'30%',elCls:'left'},
        {title : '图标',dataIndex :'logo', width:'15%',elCls:'center'},
        {title : '父节点',dataIndex :'parentId', width:'15%',elCls:'center'},
        {title : '是否有子节点',dataIndex :'leaf', width:'5%',elCls:'center'}
    ];
    
    //数据源
	var storeCFG = {
		url: searchUrl,
	};

	var parentIdType = {
			'无':0,
			'广佛通':1
		};
	var dataGrid = zBox.grid(columns,storeCFG);
	
	//用户操作 (CRUD) 
    var Operation = {
    	Condition: {name:''},
    	data: {id:null,name:null,busiNo:null,memo:null,url:null,logo:null,parentId:null,leaf:null,merchId:null},
    	add: function(){//增
    		$("#obj").hide();$("#optObj").show();$("#optTitle").html("新增业务");
    		$("#optObj").find('select').each(function(){
    			$(this).change();
    		});
    		$("#optObj").find('input').each(function(){
    			$(this).change();
    		});
    		
    	},
	    deletes: function(){//删
	      	var selections = dataGrid.grid.getSelection();
	        if(!Operation.valiSelection(selections)) return;
	        
	        zBox.confirm("此操作不可恢复，确定要删除选中的业务吗？", "删除业务", function () {
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

	        $("#obj").hide();$("#optObj").show();$("#optTitle").html("编辑业务");

	        $("#optObj").find('input[type="text"]').each(function(){
	    		var name = $(this).attr('name');
	    		$(this).val(selections[0][name]).change();
	    	});
	        $("#parentId").val(parentIdType[selections[0].parentId]);
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
	            zBox.tip("请选择一个业务！","warning");
	            return false;
	        }
	        if (selections.length > 1) {
	            zBox.tip("只能选择一个业务！","warning");
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
		"#busiNo change" : function(){Operation.data.busiNo = $(this).trim();},
		//"#busiVersion change" : function(){Operation.data.busiVersion = $(this).trim();},
		"#memo change" : function(){Operation.data.memo = $(this).trim();},
		"#url change" : function(){Operation.data.url = $(this).trim();},
		"#logo change" : function(){Operation.data.logo = $(this).trim();},
		"#parentId change" : function(){Operation.data.parentId = $(this).trim();},
		"#leaf change" : function(){Operation.data.leaf = $(this).trim();}
	};
	
    J.FireEvent(eventMap);
    
});
	
