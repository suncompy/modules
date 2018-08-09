$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'operate/operateactivitykeepdiscount/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '活动名称', name: 'name', index: 'name', width: 80 }, 			
			{ label: '启用时间', name: 'startTime', index: 'start_time', width: 80 }, 			
			{ label: '有效期，单位天', name: 'period', index: 'period', width: 80 }, 			
			{ label: '递增', name: 'proIncr', index: 'pro_incr', width: 80 }, 			
			{ label: '状态0=禁用，1=启用', name: 'use', index: 'use', width: 80 }, 			
			{ label: '描述', name: 'remark', index: 'remark', width: 80 }, 			
			{ label: '编辑人', name: 'updateBy', index: 'update_by', width: 80 }, 			
			{ label: '修改时间', name: 'updateTime', index: 'update_time', width: 80 }, 			
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
        	root: "data.list",
            page: "data.currPage",
            total: "data.totalPage",
            records: "data.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		operateActivityKeepDiscount: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.operateActivityKeepDiscount = {};
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
			var url = vm.operateActivityKeepDiscount.id == null ? "operate/operateactivitykeepdiscount/save" : "operate/operateactivitykeepdiscount/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.operateActivityKeepDiscount),
			    success: function(r){
			    	if(r.errcode == 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.errmsg);
					}
				}
			});
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "operate/operateactivitykeepdiscount/delete",
                    contentType: "application/json",
				    data: JSON.stringify(ids),
				    success: function(r){
						if(r.errcode == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.errmsg);
						}
					}
				});
			});
		},
		getInfo: function(id){
			$.get(baseURL + "operate/operateactivitykeepdiscount/info/"+id, function(r){
				if(r.errcode && r.errcode != 0){
            		return;
            	}
                vm.operateActivityKeepDiscount = r.data.operateActivityKeepDiscount;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});