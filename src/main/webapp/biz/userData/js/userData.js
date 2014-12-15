/**
 * Title: 用户区域管理入口
 * Author: qt
 * Date: 2014-07-10
 */
define('biz/userData/js/userData',function(require,exports,module){
	var $ = require('lib/jquery');
	var zBox = require('util/zbox');
	var J = require('util/j_jquery');
	var baseUrl = J.BaseUrl();
	
	var searchUrl = baseUrl+'/organize/queryUserData.do';
	var saveUrl = baseUrl+'/organize/editUserData.do';//add or edit
	var deleteUrl = baseUrl+'/organize/deleteUserData.do';
	var initUserNameUrl = baseUrl+'/organize/findUserDataName.do';
	var initCityNameUrl = baseUrl+'/organize/findUserCityName.do';
	
	//表头
	var columns = [
        {title : '用户名',dataIndex :'userName', width:'15%',elCls:'center'},
        {title : '城市名',dataIndex :'cityName', width:'15%',elCls:'center'}
    ];
    
    //数据源
	var storeCFG = {
		url: searchUrl,
	};
	
	var dataGrid = zBox.grid(columns,storeCFG);
	
	//用户操作 (CRUD) 
    var Operation = {
    	Condition: {userName:''},
    	data: {id:null,userId:null,cityId:null},
    	initUser:function(){
    		$.get(initUserNameUrl,function(ret){
    			$("#userId").empty();
    			var options = '';
    			for(var d in ret){
    				options += '<option  value="'+ret[d].userId+'">'+ret[d].userName+'</option>';
    			}    			
        		$("#userId").append(options);
        		
    		});
    	},
    	initCity:function(){
    		$.get(initCityNameUrl,function(ret){
    			$("#cityId").empty();
    			var options = '';
    			for(var d in ret){
    				options += '<option  value="'+ret[d].cityId+'">'+ret[d].cityName+'</option>';
    			}    			
        		$("#cityId").append(options);
        		
    		});
    	},
    	add: function(){//增
    		$("#obj").hide();$("#optObj").show();$("#optTitle").html("新增用户角色");
    		$("#optObj").find('select').each(function(){
    			$(this).change();
    		});
    	},
	    deletes: function(){//删
	      	var selections = dataGrid.grid.getSelection();
	        if(!Operation.valiSelection(selections)) return;
	        
	        zBox.confirm("此操作不可恢复，确定要删除选中的用户角色吗？", "删除用户角色", function () {
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
	        $("#obj").hide();$("#optObj").show();$("#optTitle").html("编辑用户角色");
	        $("#userId").val(selections[0].userId).change();
	        $("#cityId").val(selections[0].cityId).change();
	        Operation.data.id = selections[0].id;
	    },
	    find: function(){//查
	    	dataGrid.store.load(Operation.Condition);
	    },
	    back: function(){//回退
	    	$("#optObj").hide();$("#obj").show();
	    	$("#userId").val("");
	        $("#cityId").val("");
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
	            zBox.tip("请选择一个用户角色！","warning");
	            return false;
	        }
	        if (selections.length > 1) {
	            zBox.tip("只能选择一个用户角色！","warning");
	            return false;
	        }
	        return true;
	    }
    };
    Operation.initUser();
    Operation.initCity();
    //事件映射
    var eventMap = {
		"#addBtn click" : Operation.add,
		"#deleteBtn click" : Operation.deletes,
		"#editBtn click" : Operation.edit,
		"#searchBtn click" : Operation.find,
		"#backBtn click" : Operation.back,
		"#submitBtn click" : Operation.submit,
		"#srh_username change" : function(){Operation.Condition.userName = $(this).trim();},
		"#userId change" : function(){Operation.data.userId = $(this).trim();},
		"#cityId change" : function(){Operation.data.cityId = $(this).trim();}
	};
	
    J.FireEvent(eventMap);
    
});
	
