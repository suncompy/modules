$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'fastfood/foodmaterialcat/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '分类名称', name: 'name', index: 'name', width: 80 }, 			
			{ label: '上级分类ID', name: 'parentId', index: 'parent_id', width: 80 }, 			
			{ label: '子分类数量', name: 'childs', index: 'childs', width: 80 }, 			
			{ label: '状态0=不展示，1=展示', name: 'status', index: 'status', width: 80 }, 			
			{ label: '关键词', name: 'keyword', index: 'keyword', width: 80 }, 			
			{ label: '描述', name: 'descr', index: 'descr', width: 80 }, 			
			{ label: '数量单位', name: 'unit', index: 'unit', width: 80 }, 			
			{ label: '图标', name: 'icon', index: 'icon', width: 80 }, 			
			{ label: '创建时间', name: 'createTime', index: 'create_time', width: 80 }, 			
			{ label: '创建人', name: 'createBy', index: 'create_by', width: 80 }, 			
			{ label: '修改人', name: 'updateBy', index: 'update_by', width: 80 }, 			
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
		foodMaterialCat: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.foodMaterialCat = {};
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
			var url = vm.foodMaterialCat.id == null ? "fastfood/foodmaterialcat/save" : "fastfood/foodmaterialcat/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.foodMaterialCat),
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
				    url: baseURL + "fastfood/foodmaterialcat/delete",
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
			$.get(baseURL + "fastfood/foodmaterialcat/info/"+id, function(r){
				if(r.errcode && r.errcode != 0){
            		return;
            	}
                vm.foodMaterialCat = r.data.foodMaterialCat;
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