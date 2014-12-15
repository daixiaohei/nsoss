/**
 * Title: 网点管理入口
 * Author: dwz
 * Date: 2014-07-17
 */
define('biz/spot/js/spot',function(require,exports,module){
	var $ = require('lib/jquery');
	var zBox = require('util/zbox');
	var J = require('util/j_jquery');
	var baseUrl = J.BaseUrl();
	
	var searchUrl = baseUrl+'/spot/querySpotByPage.do';
	var saveUrl = baseUrl+'/spot/saveSpot.do';//add or edit
	var deleteUrl = baseUrl+'/spot/deleteById.do';
	var initCityUrl = baseUrl+'/spot/initStreet.do';
	
	//表头
	var columns = [
    	{title : '序号',dataIndex :'id', width:'10%',elCls:'center'},
        {title : '名称',dataIndex :'name', width:'15%',elCls:'center'},
        {title : '网点编号',dataIndex :'spotNo', width:'20%',elCls:'right'},
        {title : '城市编号',dataIndex :'cityNo', width:'15%',elCls:'center'},
        {title : '街道名称',dataIndex :'address', width:'20%',elCls:'center'},
        {title : '邮编',dataIndex :'postcode', width:'20%',elCls:'center'}
    ];
    
    //数据源
	var storeCFG = {
		url: searchUrl,
	};
	
	var dataGrid = zBox.grid(columns,storeCFG);
	
	//用户操作 (CRUD) 
    var Operation = {
    	Condition: {name:''},
    	data: {id:null,name:null,spotNo:null,postcode:null,cityNo:null,streetId:null,address:null},
    	initCity:function(){
    		$.get(initCityUrl,{level:"1"},function(ret){//level:1得到全国各市
    			$("#cityId").empty();
    			var options = '';
    			for(var d in ret){
    				
    				options += '<option  value="'+ret[d].cityNo+'">'+ret[d].cityName+'</option>';
    			}    			
    			
        		$("#cityId").append(options);
        		//初始化下级目录
            	var value = $("#cityId").find("option:selected").val();
            	var data={level:2,cityNO:$.trim(value)};
            	Operation.initTown(data);
    		});
    	},
    	add: function(){//增
    		$("#obj").hide();$("#optObj").show();$("#optTitle").html("新增网点");
    	},
	    deletes: function(){//删
	      	var selections = dataGrid.grid.getSelection();
	        if (selections.length == 0) {
	            zBox.tip("请选择至少一个网点！","warning");
	            return;
	        }
	        zBox.confirm("此操作不可恢复，确定要删除选中的网点吗？", "删除网点", function () {
	        	zBox.tip('正在处理....', 'loading');
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
	    	if (selections.length == 0) {
	            zBox.tip("请选择一个网点！","warning");
	            return;
	        }
	        if (selections.length > 1) {
	            zBox.tip("只能选择一个网点！","warning");
	            return;
	        }
	        $("#obj").hide();$("#optObj").show();$("#optTitle").html("编辑网点");
	        $("#name").val(selections[0].name).change();
	        $("#spotNo").val(selections[0].spotNo).change();
	        $("#postcode").val(selections[0].postcode).change();
	        Operation.data.id = selections[0].id;
	    },
	    find: function(){//查
	    	dataGrid.store.load(Operation.Condition);
	    },
	    back: function(){//回退
	    	$("#optObj").hide();$("#obj").show();
	    	$("#name").val("");
	        $("#spotNo").val("");
	        $("#postcode").val("");
	        $("#address").val("");
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
	    initTown:function(data){
    		$.get(baseUrl+'/spot/initStreet.do',data,function(ret){
    			$("#townId").empty();
    			var options = '';
    			for(var d in ret){
    				options += '<option  value="'+ret[d].cityNo+'">'+ret[d].cityName+'</option>';
    			}    			
        		$("#townId").append(options);
        		//设置初始值
        		var value = $("#townId").find("option:selected").val();
            	Operation.data.cityNo = $.trim(value);
            	//初始化下级目录
            	var data={cityNo:$.trim(value)};
            	Operation.initStreet(data);
            	
    		});
    	
	    },
	    initStreet:function(data){
    		$.get(baseUrl+'/spot/queryStreetsByCityNo.do',data,function(ret){
    			$("#streetId").empty();
    			var options = '';
    			for(var d in ret){
    				options += '<option  value="'+ret[d].id+'">'+ret[d].name+'</option>';
    			}    			
        		$("#streetId").append(options);
        		//设置初始值
        		var value = $("#streetId").find("option:selected").val();
            	Operation.data.streetId = $.trim(value);
            	
            	var text = $("#streetId").find("option:selected").text();
            	Operation.data.address = $.trim(text);

    		});
	    }
	    
    };
    
    //初始化界面
    Operation.initCity();
    $("#cityId").change(function(){	
    	var value = $(this).find("option:selected").val();
    	var data={level:2,cityNO:$.trim(value)};
    	Operation.initTown(data);

    });
    $("#townId").change(function(){
    	var value = $(this).find("option:selected").val();
    	var data={cityNo:$.trim(value)};
    	Operation.data.cityNo=$.trim(value);
    	Operation.initStreet(data);
    });
    $("#streetId").change(function(){
    	var value = $(this).find("option:selected").val();
    	var text = $(this).find("option:selected").text();
    	Operation.data.streetId = $.trim(value);
    	Operation.data.address = $.trim(text);
    });
    
    //事件映射
    var eventMap = {
		"#addBtn click" : Operation.add,
		"#deleteBtn click" : Operation.deletes,
		"#editBtn click" : Operation.edit,
		"#searchBtn click" : Operation.find,
		"#backBtn click" : Operation.back,
		"#submitBtn click" : Operation.submit,
		"#srh_name change" : function(){Operation.Condition.name = $.trim($(this).val());},
		"#name change" : function(){Operation.data.name = $.trim($(this).val());},
		"#spotNo change" : function(){Operation.data.spotNo = $.trim($(this).val());},
		"#postcode change" : function(){Operation.data.postcode = $.trim($(this).val());}
	};
	
    J.FireEvent(eventMap);
    
});
	
