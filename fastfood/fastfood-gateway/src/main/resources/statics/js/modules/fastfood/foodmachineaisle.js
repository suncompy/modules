$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'fastfood/foodmachineaisle/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '机器分类', name: 'macId', index: 'mac_id', width: 80 }, 			
			{ label: 'X坐标', name: 'x', index: 'x', width: 80 }, 			
			{ label: 'Y坐标', name: 'y', index: 'y', width: 80 }, 			
			{ label: 'Z坐标', name: 'z', index: 'z', width: 80 }, 			
			{ label: '总容量', name: 'size', index: 'size', width: 80 }, 			
			{ label: '库存', name: 'stock', index: 'stock', width: 80 }, 			
			{ label: '产品ID', name: 'productId', index: 'product_id', width: 80 }, 			
			{ label: '产品分类', name: 'productCatId', index: 'product_cat_id', width: 80 }, 			
			{ label: '创建时间', name: 'createTime', index: 'create_time', width: 80 }, 			
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
		foodMachineAisle: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.foodMachineAisle = {};
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
			var url = vm.foodMachineAisle.id == null ? "fastfood/foodmachineaisle/save" : "fastfood/foodmachineaisle/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.foodMachineAisle),
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
				    url: baseURL + "fastfood/foodmachineaisle/delete",
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
			$.get(baseURL + "fastfood/foodmachineaisle/info/"+id, function(r){
				if(r.errcode && r.errcode != 0){
            		return;
            	}
                vm.foodMachineAisle = r.data.foodMachineAisle;
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