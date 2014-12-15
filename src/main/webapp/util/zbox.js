/**
 * Author: zk
 * Date: 2014-07-10
 */
define('util/zbox',function (require, exports, module) {
	var $ = require('lib/jquery');
 	window.$ = window.jQuery = $;
	var Overlay = require('bui/overlay');
	var Message = Overlay.Message;
	var Dialog = Overlay.Dialog;
	var Grid = require('bui/grid'); 
	var Data = require('bui/data');
    var Store = Data.Store;
	var Calendar = require('bui/calendar');
	
	var zBox = {};
	
	zBox.tip = function(msg,type){
		var type = type || 'info';
		var config = {
          	msg : msg,
          	icon : type,
          	mask : false,
          	autoHideDelay : 2500,
          	buttons : []
      	};
		Message.Show(config);
	};
	
	zBox.loading = function(msg){
		var content = '<i class="x-icon x-icon-question">UN</i><span> '+msg+'</span>';
		var config = {
			title:'loading',
            width:180,
            height:50,
            mask:true,
            buttons:[],
            bodyContent:content
		};
		var load = new Dialog(config);
		load.show();
		return load;
	};
	
	zBox.confirm = function(msg,title,callback){
		var content = '<i class="x-icon x-icon-question">?</i><span> '+msg+'</span>';
		new Dialog({
			title:title,
            width:350,
            height:120,
            mask:false,
            buttons:[{
            	text:'确定',
                elCls : 'button button-primary',
                handler : function(){
                	this.close();
                  	callback.call();
                }
            },{
            	text:'取消',
                elCls : 'button',
                handler : function(){
                  this.close();
                }
            }],
            bodyContent:content
		}).show();
	};
	
	zBox.calendar = function(cfg){
		var config = $.extend({
            trigger:'.calendar',
            autoRender : true
        },cfg);
		var cld = new Calendar.DatePicker(config);
		return cld;
	};
	
	zBox.grid = function(columns,storeCFG,gridCFG,checkFlag){
		var check = [Grid.Plugins.CheckSelection,Grid.Plugins.RowNumber],
		radio =  [Grid.Plugins.RadioSelection,Grid.Plugins.RowNumber],
		plagin = radio;
		if(checkFlag) plagin = check;
		var storecfg = $.extend({
           url : '../../been/tableData.js',
           autoLoad:true, //自动加载数据
           params : {}, //配置初始请求的参数
           proxy : { 
              pageStart : 1,//设置起始页码
              method : 'post',
              //dataType : 'jsonp', //返回数据的类型
              limitParam : 'limit', //一页多少条记录   每页10条记录
              pageIndexParam : 'curPage', //页码
              startParam : 'start' //起始记录    从0开始
           },
           remoteFilter: true,
           root : 'items',               
    	   totalProperty : 'total',
           pageSize:10	// 配置分页数目
        },storeCFG);
        var store = new Store(storecfg);
		var gridcfg = $.extend({
           render:'#grid',
           idField : 'id',
           width:'100%',
           columns : columns,
           store: store,
           loadMask: true,
           itemStatusFields : { //设置数据跟状态的对应关系
              selected : 'selected',
              disabled : 'disabled'
           },
           emptyDataTpl : '<div class="centered"><img alt="Crying" src="../../global/images/cry.png"><h2>查询的数据不存在</h2></div>',
           plugins : plagin,
           bbar:{
           	  pagingBar:true
           }
        },gridCFG);
		var grid = new Grid.Grid(gridcfg);
		
		store.on('exception',function (ev) {
            Message.Alert("请求数据错误!");
        });
		
		grid.render();
		$(".bui-grid-width .bui-grid-body").css({"height":"240px","overflow":"auto"});
		
		//添加记录
        function addFunction(){
          var newData = {b : 0};
          store.addAt(newData,0);
          editing.edit(newData,'a'); //添加记录后，直接编辑
        }
        //删除选中的记录
        function delFunction(){
          var selections = grid.getSelection();
          store.remove(selections);
        }
        
		function validFn (value,obj) {
          var records = store.getResult(),
            rst = '';
          BUI.each(records,function (record) {
            if(record.a == value && obj != record){
              rst = '文本不能重复';
              return false;
            }
          });
          return rst;
        }
		
		return {grid:grid,store:store};
	};
	
	return zBox;
});