/**
 * Title: 用户管理入口
 * Author: ZK
 * Date: 2014-07-10
 */
define('biz/partList/js/partList',function(require,exports,module){
	var Index = require('global/index');
	
	
	var $ = require('lib/jquery');
	var zBox = require('util/zbox');
	var J = require('util/j_jquery');
	var baseUrl = J.BaseUrl();
	
	var id = J.Request('id');
	var searchUrl = baseUrl+'/part/findall.do?deviceListId='+id;
	var saveUrl = baseUrl+'/part/editPartList.do';//add or edit
	var saveUrl2 = baseUrl+'/part/batchAdd.do';//add or edit
	var deleteUrl = baseUrl+'/part/deletePartListById.do';
	var searchToAddUrl = baseUrl+'/part/findallParts.do';
	

	
	//表头
	var columns = [
        {title : '类型',dataIndex :'partType', width:'15%',elCls:'center'},
        {title : '名称',dataIndex :'partName', width:'15%',elCls:'center'},
        {title : '编号',dataIndex :'partNo', width:'15%',elCls:'center'},
        {title : '设备扩展名',dataIndex :'partNameNick', width:'15%',elCls:'center'},
        {title : '端口号',dataIndex :'portNum', width:'15%',elCls:'center'},
        {title : '交易终端号',dataIndex :'transNo', width:'20%',elCls:'center'},
        {title : '设备id',dataIndex :'id', width:'20%',elCls:'center'}
    ];
	
	//表头2
	//表头
	var Addcolumns = [
        {title : '设备id',dataIndex :'partId', width:'15%',elCls:'center'},
        {title : '设备编号',dataIndex :'partNo', width:'15%',elCls:'center'},
        {title : '设备类型',dataIndex :'', width:'15%',elCls:'center'},
        {title : '设备名称',dataIndex :'partName', width:'15%',elCls:'center'}
    ];
    //数据源
	var storeCFG = {
		url: searchUrl
	};
	
	var storeAddCFG = {
		url:searchToAddUrl	
	};
	
	//不使用分页
	var gridCFG = {
		bbar:{pagingBar:false}
	};
	
	var gridAddCFG = {
		render:'#addGrid',
		bbar:{pagingBar:false}
	};
	
	var dataGrid = zBox.grid(columns,storeCFG,gridCFG);
	var dataAddGrid = zBox.grid(Addcolumns,storeAddCFG,gridAddCFG,true);
	
	//用户操作 (CRUD) 
    var Operation = {
    	Condition: {name:''},
    	data: {id:null,partType:null,partName:null,partNo:null,partNameNick:null,portNum:null,transNo:null},
    	add: function(){//增
    		$("#obj").hide();$("#optObj").show();$("#partListForm").hide();$("#addGrid").show();$("#submitBtnAdd").show();$("#optTitle").html("新增设备部件列表");
    	},
	    deletes: function(){//删
	      	var selections = dataGrid.grid.getSelection();
	        if(!Operation.valiSelection(selections)) return;
	        
	        zBox.confirm("确定要删除选中的设备部件列表吗？", "删除设备部件列表", function () {
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
	        $("#obj").hide();$("#optObj").show();$("#addGrid").hide();$("#partListForm").show();$("#optTitle").html("编辑设备部件列表");
	        $("#partNameNick").val(selections[0].partNameNick).change();
	        $("#portNum").val(selections[0].portNum).change();
	        $("#transNo").val(selections[0].transNo).change();
	        Operation.data.id = selections[0].id;
	    },
	    find: function(){//查
	    	dataGrid.store.load(Operation.Condition);
	    },
	    back: function(){//回退
	    	$("#optObj").hide();$("#obj").show();
	    	$("#partNameNick").val("");
	        $("#portNum").val("");
	        $("#transNo").val("");
	        Operation.data.id = null;
	    },
	    submitEdit: function(){//提交,这里有两张表，要分开处理
	    	var loading = zBox.loading('正在处理....');
	    	var data ={id:Operation.data.id,portNum:Operation.data.portNum,transNo:Operation.data.transNo,partNameNick:Operation.data.partNameNick};
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
	    submitAdd: function(){//提交
	    	var loading = zBox.loading('正在处理....');
	    	//由于展示的数据是通过多表连接查询得到的，所以保存到数据表的时候要分开保存
	    	var selections = dataAddGrid.grid.getSelection();
	    	var partIds="";
	    	for(var i=0;i<selections.length;i++){
	    		partIds +=selections[i].partId+","; 
	    	}
	    	partIds = partIds.substring(0, partIds.length-1);
	    	var data = {deviceListId:id,partIds:partIds};
	    	$.post(saveUrl2,data,function(ret){
	    		Operation.back();
	    		dataGrid.store.load(Operation.Condition);
	    		loading.close();
	    		zBox.tip('处理成功！', 'success');
	    	});
	    },
	    valiSelection: function(selections){//验证
	    	if (selections.length == 0) {
	            zBox.tip("请选择一个设备列表！","warning");
	            return false;
	        }
	        if (selections.length > 1) {
	            zBox.tip("只能选择一个设备列表！","warning");
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
		"#submitBtnEdit click" : Operation.submitEdit,
		"#submitBtnAdd click" : Operation.submitAdd,
		"#srh_name change" : function(){Operation.Condition.name = $(this).trim();},
		"#partNameNick change" : function(){Operation.data.partNameNick = $(this).trim();},
		"#portNum change" : function(){Operation.data.portNum = $(this).trim();},
		"#transNo change" : function(){Operation.data.transNo = $(this).trim();}

	};
	
    J.FireEvent(eventMap);
    
});
	
