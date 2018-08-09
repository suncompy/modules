$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'fastfood/foodmachine/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '机器编号', name: 'imei', index: 'IMEI', width: 80 }, 			
			{ label: '通信端口，IP', name: 'host', index: 'host', width: 80 }, 			
			{ label: '所在地区', name: 'areaCode', index: 'area_code', width: 80 }, 			
			{ label: '地图经纬度', name: 'pos', index: 'pos', width: 80 }, 			
			{ label: '机器公告', name: 'notice', index: 'notice', width: 80 }, 			
			{ label: '机器分类', name: 'catId', index: 'cat_id', width: 80 }, 			
			{ label: '合作公司ID', name: 'partnerId', index: 'partner_id', width: 80 }, 			
			{ label: '状态0=正常，-1=维修', name: 'status', index: 'status', width: 80 }, 			
			{ label: '补货员', name: 'replenishMan', index: 'replenish_man', width: 80 }, 			
			{ label: '配货员', name: 'pickingMan', index: 'picking_man', width: 80 }, 			
			{ label: '维修员', name: 'maintenanceMan', index: 'maintenance_man', width: 80 }, 			
			{ label: '描述', name: 'remark', index: 'remark', width: 80 }, 			
			{ label: '创建时间', name: 'createTime', index: 'create_time', width: 80 }, 			
			{ label: '创建人', name: 'createBy', index: 'create_by', width: 80 }, 			
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
		foodMachine: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.foodMachine = {};
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
			var url = vm.foodMachine.id == null ? "fastfood/foodmachine/save" : "fastfood/foodmachine/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.foodMachine),
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
				    url: baseURL + "fastfood/foodmachine/delete",
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
			$.get(baseURL + "fastfood/foodmachine/info/"+id, function(r){
				if(r.errcode && r.errcode != 0){
            		return;
            	}
                vm.foodMachine = r.data.foodMachine;
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