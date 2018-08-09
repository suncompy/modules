$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'fastfood/foodorderchilds/list',
        datatype: "json",
        colModel: [			
			{ label: 'orderProductId', name: 'orderProductId', index: 'order_product_id', width: 50, key: true },
			{ label: '订单ID', name: 'orderId', index: 'order_id', width: 80 }, 			
			{ label: '餐品分类ID', name: 'productCatId', index: 'product_cat_id', width: 80 }, 			
			{ label: '餐品ID', name: 'productId', index: 'product_id', width: 80 }, 			
			{ label: '价格', name: 'price', index: 'price', width: 80 }, 			
			{ label: '积分', name: 'score', index: 'score', width: 80 }, 			
			{ label: '商品总数量', name: 'buyNumber', index: 'buy_number', width: 80 }, 			
			{ label: '商品总积分', name: 'productScore', index: 'product_score', width: 80 }, 			
			{ label: '商品总金额', name: 'productAmount', index: 'product_amount', width: 80 }, 			
			{ label: '订单状态 -2=已删除，-1=已取消，0=待支付，1=已支付，2=已取单，3=已评价', name: 'status', index: 'status', width: 80 }, 			
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
		foodOrderChilds: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.foodOrderChilds = {};
		},
		update: function (event) {
			var orderProductId = getSelectedRow();
			if(orderProductId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(orderProductId)
		},
		saveOrUpdate: function (event) {
			var url = vm.foodOrderChilds.orderProductId == null ? "fastfood/foodorderchilds/save" : "fastfood/foodorderchilds/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.foodOrderChilds),
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
			var orderProductIds = getSelectedRows();
			if(orderProductIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "fastfood/foodorderchilds/delete",
                    contentType: "application/json",
				    data: JSON.stringify(orderProductIds),
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
		getInfo: function(orderProductId){
			$.get(baseURL + "fastfood/foodorderchilds/info/"+orderProductId, function(r){
				if(r.errcode && r.errcode != 0){
            		return;
            	}
                vm.foodOrderChilds = r.data.foodOrderChilds;
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