/**
 * Title: 业务管理入口
 * Author: qt
 * Date: 2014-07-10
 */
define('biz/news/js/news',function(require,exports,module){
	var $ = require('lib/jquery');
	var zBox = require('util/zbox');
	var J = require('util/j_jquery');
	var baseUrl = J.BaseUrl();
	
	var saveUrl = baseUrl+'/news/saveNews.do';//add or edit
	var searchUrl = baseUrl+'/news/queryNews.do';
	var deleteUrl = baseUrl+'/news/deleteNews.do';
	var initPublishStatusUrl = baseUrl+'/news/findPublishStatus.do';

	//表头
	var columns = [
        {title : '发布日期',dataIndex :'publishDate', width:'15%',elCls:'center'},
        {title : '标题',dataIndex :'title', width:'15%',elCls:'center'},
        {title : '作者',dataIndex :'author', width:'15%',elCls:'center'},
        {title : '关键字',dataIndex :'digest', width:'15%',elCls:'center'},
        {title : '发布状态',dataIndex :'publishStatus', width:'15%',elCls:'center'}
       
    ];
    
    //数据源
	var storeCFG = {
		url: searchUrl
	};

	//var dataGrid = zBox.grid(columns,storeCFG);
	
	//不使用分页
	var gridCFG = {
		bbar:{pagingBar:false}
	};
	
	var dataGrid = zBox.grid(columns,storeCFG,gridCFG);
	
	zBox.calendar({trigger:'.calendar1',showTime:true});
	zBox.calendar({trigger:'.calendar2',showTime:true});
	zBox.calendar({trigger:'.calendar3',showTime:true});

    var editor = CKEDITOR.replace("content",{
        allowedContent: true,
        //extraPlugins: 'uicolor,tableresize,image2,sourcedialog',
        fullPage: false
    });
	
	//用户操作 (CRUD) 
    var Operation = {
    	Condition: {title:'',publishDate:'',publishDate1:'',publishStatusId:''},
    	data: {id:null,title:null,author:null,digest:null,keyword:null,
            content:null,publishStatus:null},

	    find: function(){//查
	    	dataGrid.store.load(Operation.Condition);
	    },
	    add: function(){//增
    		$("#obj").hide();$("#optObj").show();$("#optTitle").html("新增新闻");
    		$("#optObj").find('select').each(function(){
    			$(this).change();
    		});
    	},
    	edit: function(){//改
	    	var selections = dataGrid.grid.getSelection();
	    	if(!Operation.valiSelection(selections)) return;
	    	
	        $("#obj").hide();$("#optObj").show();$("#optTitle").html("编辑新闻");
	        $("#optObj").find('input[type="text"]').each(function(){
	    		  var name = $(this).attr('name');
	    		  $(this).val(selections[0][name]).change();
	    	});
	        if(selections[0].publishStatus === '关闭'){
                $("#publishStatusClose").attr("checked","checked");
                selections[0].publishStatus = 1;
            }
	    	if(selections[0].publishStatus === '发布'){
                $("#publishStatusRelease").attr("checked","checked");
                selections[0].publishStatus = 0;
            }
	        $("#digest").val(selections[0].digest).change();
	        $("#content").val(selections[0].content).change();
            editor.setData(selections[0].content);
	        Operation.data.id = selections[0].id;
	    },
	    deletes: function(){//删
	      	var selections = dataGrid.grid.getSelection();
	        if(!Operation.valiSelection(selections)) return;
	        
	        zBox.confirm("确定要删除选中的新闻吗？", "删除新闻", function () {
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
	    initStatus:function(){
    		$.get(initPublishStatusUrl,function(ret){
    			console.log(ret);
    			$("#srh_publishStatusId").empty();
    			var options = '';
    			options+='<option  value="">全部</option>';
    			for(var d in ret){
    				options += '<option  value="'+ret[d].publishStatusId+'">'+ret[d].publishStatus+'</option>';
    			}    
    			
        		$("#srh_publishStatusId").append(options);
        		
    		});
    	},
    	 back: function(){//回退
 	    	$("#optObj").hide();$("#obj").show();
 	    	$("#optObj").find('input[type="text"]').each(function(){
 	    		$(this).val('');
 	    	});
 	    	$("#digest").val("");
 	    	$("#content").val("");
 	        Operation.data.id = null;
 	    },
    	submit: function(){//提交
            //得到radio的值
            $('input[name="publishStatus"]').each(function(){
                if(this.checked){
                    Operation.data.publishStatus = this.value;
                }
            });
            //得到content的值
            Operation.data.content = editor.getData();

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
	            zBox.tip("请选择一个菜单！","warning");
	            return false;
	        }
	        if (selections.length > 1) {
	            zBox.tip("只能选择一个菜单！","warning");
	            return false;
	        }
	        return true;
	    }
	   
    };
    Operation.initStatus();
    //事件映射
    var eventMap = {
        //按钮事件
        "#searchBtn click" : Operation.find,
        "#submitBtn click" : Operation.submit,
        "#addBtn click" : Operation.add,
        "#backBtn click" : Operation.back,
        "#deleteBtn click" : Operation.deletes,
        "#editBtn click" : Operation.edit,
        //查询条件
        "#srh_title change" : function(){Operation.Condition.title = $(this).trim();},
        "#srh_publishDate change" : function(){Operation.Condition.publishDate = $(this).trim();},
        "#srh_publishDate1 change" : function(){Operation.Condition.publishDate1 = $(this).trim();},
        "#srh_publishStatusId change" : function(){Operation.Condition.publishStatusId = $(this).trim();},
        //修改数据
        "#title change" : function(){Operation.data.title = $(this).trim();},
        "#author change" : function(){Operation.data.author = $(this).trim();},
        "#keyword change" : function(){Operation.data.keyword = $(this).trim();},
        "#digest change" : function(){Operation.data.digest = $(this).trim();},
        "#content change" : function(){Operation.data.content = $(this).trim();}
	};
	
    J.FireEvent(eventMap);
    
});
	
