/**
 * Title:  优惠券管理入口
 * Author: ZK
 * Date: 2014-07-10
 */
define('biz/coupon/js/coupon',function(require,exports,module){
	var $ = require('lib/jquery');
	var zBox = require('util/zbox');
	var J = require('util/j_jquery');
	var Index = require('global/index');
	var baseUrl = J.BaseUrl();
	
	var searchUrl = baseUrl+'/coupon/queryCoupon.do';
	var saveUrl = baseUrl+'/coupon/saveCoupon.do';//add or edit
	var deleteUrl = baseUrl+'/coupon/deleteCoupon.do';
	var loadCoopUrl = baseUrl+'/coupon/loadCoop.do';
	
	//表头
	var columns = [
        {title : '优惠券名',dataIndex :'name', width:'15%',elCls:'center'},
		{title : '合作公司',dataIndex :'coopName', width:'15%',elCls:'center'},
        {title : '起始时间',dataIndex :'startDate', width:'12%',elCls:'center'},
        {title : '结束时间',dataIndex :'endDate', width:'12%',elCls:'center'},
        {title : '优惠券类型',dataIndex :'couponType', width:'12%',elCls:'center'},
        {title : '绑定类型',dataIndex :'bindType', width:'12%',elCls:'center'},
        {title : '绑定银行卡',dataIndex :'bindBank', width:'16%',elCls:'center'},
        {title : '操作', width:'5%',elCls:'center',renderer : function(){return '<span class="grid-command btn-edit btnOper">明细</span>';}}
    ];
    
    //数据源
	var storeCFG = {
		url: searchUrl
	};
	
	var dataGrid = zBox.grid(columns,storeCFG);
	
	var bindTypeMap = {
		'无绑定':0,
		'绑定业务':1,
		'绑定银行卡':2
	};
	
	var couponTypeMap = {
		'无券号':0,
		'有券号':1
	};
	
	zBox.calendar({trigger:'.calendar1'});
	zBox.calendar({trigger:'.calendar2'});
	
	//用户操作 (CRUD) 
    var Operation = {
    	Condition: {name:''},
    	data: {id:null,name:null,coopId:null,startDate:null,endDate:null,couponType:null,bindType:null,bindBank:null,status:1},
    	add: function(){//增
    		$("#obj").hide();$("#optObj").show();$("#optTitle").html("新增优惠券");
    		$("#optObj").find('select').each(function(){
    			$(this).change();
    		});
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
	    	var This = Operation;
	    	var selections = dataGrid.grid.getSelection();
	    	if(!This.valiSelection(selections)) return;
	    	
	    	This.add();
	        
	        $("#optObj").find('input[type="text"]').each(function(){
	    		var name = $(this).attr('name');
	    		$(this).val(selections[0][name]).change();
	    	});
	    	
	    	$("#coopName").val(selections[0].coopId).change();
	    	$("#couponType").val(couponTypeMap[selections[0].couponType]).change();
	    	$("#bindType").val(bindTypeMap[selections[0].bindType]).change();
	    	
	        This.data.id = selections[0].id;
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
	            zBox.tip("请选择一条优惠券！","warning");
	            return false;
	        }
	        if (selections.length > 1) {
	            zBox.tip("只能选择一条优惠券！","warning");
	            return false;
	        }
	        return true;
	    },
	    btnOper: function(){
	    	var selections = dataGrid.grid.getSelection();
	    	Index.Open('biz/coupon/coupon_code.html?couponId='+selections[0].id);
	    },
	    bindBusi: function(){
	    	var selections = dataGrid.grid.getSelection();
	    	if(!Operation.valiSelection(selections)) return;
	    	Index.Open('biz/coupon/coupon_busi.html?couponId='+selections[0].id+'&couponName='+selections[0].name);
	    },
	    bindDevi: function(){
	    	var selections = dataGrid.grid.getSelection();
	    	if(!Operation.valiSelection(selections)) return;
	    	Index.Open('biz/coupon/coupon_devi.html?couponId='+selections[0].id+'&couponName='+selections[0].name);
	    },
	    initView: function(){
	    	J.GetJson(loadCoopUrl,{},function(data){
	    		var options = '';
	    		for(var i in data){
	    		   options += '<option value="'+data[i].id+'">'+data[i].nameFull+'</option>';
	    		}
	    		$("#coopName").append(options);
	    	},function(){
	    		console.log('加载合作公司失败');
	    	});
	    }
    };
    
    Operation.initView();
    
    //事件映射
    var eventMap = {
    	".btnOper click" : Operation.btnOper,
    	"#bindBusiBtn click" : Operation.bindBusi,
    	"#bindDeviBtn click" : Operation.bindDevi,
		"#addBtn click" : Operation.add,
		"#deleteBtn click" : Operation.deletes,
		"#editBtn click" : Operation.edit,
		"#searchBtn click" : Operation.find,
		"#backBtn click" : Operation.back,
		"#submitBtn click" : Operation.submit,
		"#srh_name change" : function(){Operation.Condition.name = $(this).trim();},
		"#name change" : function(){Operation.data.name = $(this).trim();},
		"#coopName change" : function(){Operation.data.coopId = $(this).trim();},
		"#couponType change" : function(){Operation.data.couponType = $(this).trim();},
		"#bindType change" : function(){Operation.data.bindType = $(this).trim();},
		"#bindBank change" : function(){Operation.data.bindBank = $(this).trim();},
		"#startDate change" : function(){Operation.data.startDate = $(this).trim();},
		"#endDate change" : function(){Operation.data.endDate = $(this).trim();}
	};
	
    J.FireEvent(eventMap);
    
});
	
