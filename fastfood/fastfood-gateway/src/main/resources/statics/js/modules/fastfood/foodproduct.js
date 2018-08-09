$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'fastfood/foodproduct/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '产品名称', name: 'name', index: 'name', width: 80 }, 			
			{ label: '产品食材，以;分割', name: 'materials', index: 'materials', width: 80 }, 			
			{ label: '参考价格', name: 'showPrice', index: 'show_price', width: 80 }, 			
			{ label: '参考价格', name: 'period', index: 'period', width: 80 }, 			
			{ label: '下架提醒时间', name: 'remind', index: 'remind', width: 80 }, 			
			{ label: '图片', name: 'picture', index: 'picture', width: 80 }, 			
			{ label: '总库存', name: 'totalStock', index: 'total_stock', width: 80 }, 			
			{ label: '总库存预警值', name: 'stockAlertVal', index: 'stock_alert_val', width: 80 }, 			
			{ label: '计量单位', name: 'unit', index: 'unit', width: 80 }, 			
			{ label: '主食重量', name: 'zsWeight', index: 'zs_weight', width: 80 }, 			
			{ label: '菜品重量', name: 'cpWeight', index: 'cp_weight', width: 80 }, 			
			{ label: '是否加热', name: 'warmFlag', index: 'warm_flag', width: 80 }, 			
			{ label: '加热时间', name: 'warmTime', index: 'warm_time', width: 80 }, 			
			{ label: '推荐指数', name: 'hotNum', index: 'hot_num', width: 80 }, 			
			{ label: '标签0=无，1=新品，2=推荐', name: 'label', index: 'label', width: 80 }, 			
			{ label: '状态0=下架，1=上架', name: 'status', index: 'status', width: 80 }, 			
			{ label: '上架时间', name: 'upTime', index: 'up_time', width: 80 }, 			
			{ label: '下架时间', name: 'downTime', index: 'down_time', width: 80 }, 			
			{ label: '关键词', name: 'keyword', index: 'keyword', width: 80 }, 			
			{ label: '描述', name: 'descr', index: 'descr', width: 80 }, 			
			{ label: '备注', name: 'remark', index: 'remark', width: 80 }, 			
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
		foodProduct: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.foodProduct = {};
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
			var url = vm.foodProduct.id == null ? "fastfood/foodproduct/save" : "fastfood/foodproduct/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.foodProduct),
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
				    url: baseURL + "fastfood/foodproduct/delete",
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
			$.get(baseURL + "fastfood/foodproduct/info/"+id, function(r){
				if(r.errcode && r.errcode != 0){
            		return;
            	}
                vm.foodProduct = r.data.foodProduct;
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