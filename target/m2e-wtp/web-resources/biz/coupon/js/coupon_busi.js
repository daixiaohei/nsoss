/**
 * Title:  优惠券代码管理入口
 * Author: ZK
 * Date: 2014-09-24
 */
define('biz/coupon/js/coupon_busi',function(require,exports,module){
	var $ = require('lib/jquery');
	var zBox = require('util/zbox');
	var J = require('util/j_jquery');
	var baseUrl = J.BaseUrl();
	
	var couponId = J.Request('couponId');
	var couponName = J.GetUrlQuery('couponName',true);
	
	var searchUrl = baseUrl+'/coupon/queryCouponBusi.do';
	var saveUrl = baseUrl+'/coupon/saveCouponBusi.do';//add or edit
	var deleteUrl = baseUrl+'/coupon/deleteCouponBusi.do';
	var loadBusiUrl = baseUrl+'/coupon/loadBusi.do';
	
	//表头
	var columns = [
        {title : '优惠券名',dataIndex :'couponName', width:'20%',elCls:'center'},
		{title : '业务名',dataIndex :'busiName', width:'20%',elCls:'center'},
    ];
    
    //数据源
	var storeCFG = {
		url: searchUrl,
		params:{
			couponId:couponId
		}
	};
	
	var dataGrid = zBox.grid(columns,storeCFG);
	
	//用户操作 (CRUD) 
    var Operation = {
    	Condition: {couponId:couponId,busiName:''},
    	data: {id:null,couponId:couponId,busiId:''},
    	add: function(){//增
    		$("#obj").hide();$("#optObj").show();$("#optTitle").html("新增绑定业务");
    		$("#optObj").find('select').each(function(){
    			$(this).change();
    		});
    	},
	    deletes: function(){//删
	      	var selections = dataGrid.grid.getSelection();
	        if(!Operation.valiSelection(selections)) return;
	        
	        zBox.confirm("确定要删除选中的记录吗？", "删除记录", function () {
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
	        
	    	$("#busiName").val(selections[0].busiId).change();
	    	
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
	    		loading.close();
	    		if(ret.exist){
	    			zBox.tip('已经存在此条记录！', 'error');
	    			return;
	    		}
	    		Operation.back();
	    		dataGrid.store.load(Operation.Condition);
	    		zBox.tip('处理成功！', 'success');
	    	},function(){
	    		loading.close();
	    		zBox.tip('处理失败！', 'error');
	    	});
	    },
	    valiSelection: function(selections){//验证
	    	if (selections.length == 0) {
	            zBox.tip("请选择一条优记录！","warning");
	            return false;
	        }
	        if (selections.length > 1) {
	            zBox.tip("只能选择一条记录！","warning");
	            return false;
	        }
	        return true;
	    },
	    initView: function(){
	    	$("#couponName").html(couponName);
	    	J.GetJson(loadBusiUrl,{},function(data){
	    		var options = '';
	    		for(var i in data){
	    		   options += '<option value="'+data[i].id+'">'+data[i].name+'</option>';
	    		}
	    		$("#busiName").append(options);
	    	},function(){
	    		console.log('加载业务失败');
	    	});
	    }
    };
    
	Operation.initView();
    
    //事件映射
    var eventMap = {
		"#searchBtn click" : Operation.find,
		"#addBtn click" : Operation.add,
		"#deleteBtn click" : Operation.deletes,
		"#editBtn click" : Operation.edit,
		"#backBtn click" : Operation.back,
		"#submitBtn click" : Operation.submit,
		"#srh_name change" : function(){Operation.Condition.busiName = $(this).trim();},
		"#busiName change" : function(){Operation.data.busiId = $(this).trim();}
	};
	
    J.FireEvent(eventMap);
    
});
	
