$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'operate/operateredpacked/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '红包名称', name: 'name', index: 'name', width: 80 }, 			
			{ label: '版本号', name: 'version', index: 'version', width: 80 }, 			
			{ label: '金额', name: 'money', index: 'money', width: 80 }, 			
			{ label: '激活码', name: 'cdkey', index: 'cdkey', width: 80 }, 			
			{ label: '创建人', name: 'createBy', index: 'create_by', width: 80 }, 			
			{ label: '创建时间', name: 'createTime', index: 'create_time', width: 80 }, 			
			{ label: '领取人', name: 'userId', index: 'user_id', width: 80 }, 			
			{ label: '状态0=未激活，1=已激活，2=已使用', name: 'use', index: 'use', width: 80 }, 			
			{ label: '使用订单号', name: 'orderNo', index: 'order_no', width: 80 }, 			
			{ label: '使用时间', name: 'useTime', index: 'use_time', width: 80 }, 			
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
		operateRedPacked: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.operateRedPacked = {};
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
			var url = vm.operateRedPacked.id == null ? "operate/operateredpacked/save" : "operate/operateredpacked/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.operateRedPacked),
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
				    url: baseURL + "operate/operateredpacked/delete",
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
			$.get(baseURL + "operate/operateredpacked/info/"+id, function(r){
				if(r.errcode && r.errcode != 0){
            		return;
            	}
                vm.operateRedPacked = r.data.operateRedPacked;
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