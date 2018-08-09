$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'fastfood/foodshoppingcart/list',
        datatype: "json",
        colModel: [			
			{ label: 'cartId', name: 'cartId', index: 'cart_id', width: 50, key: true },
			{ label: '餐品ID', name: 'productId', index: 'product_id', width: 80 }, 			
			{ label: '机器ID', name: 'macId', index: 'mac_id', width: 80 }, 			
			{ label: '用户ID', name: 'userId', index: 'user_id', width: 80 }, 			
			{ label: '购买数量', name: 'buyNumber', index: 'buy_number', width: 80 }, 			
			{ label: '创建时间', name: 'createTime', index: 'create_time', width: 80 }, 			
			{ label: '更新时间', name: 'updateTime', index: 'update_time', width: 80 }, 			
			{ label: '购物车状态：0,未选中；1,选中', name: 'checkStatus', index: 'check_status', width: 80 }, 			
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
		foodShoppingCart: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.foodShoppingCart = {};
		},
		update: function (event) {
			var cartId = getSelectedRow();
			if(cartId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(cartId)
		},
		saveOrUpdate: function (event) {
			var url = vm.foodShoppingCart.cartId == null ? "fastfood/foodshoppingcart/save" : "fastfood/foodshoppingcart/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.foodShoppingCart),
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
			var cartIds = getSelectedRows();
			if(cartIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "fastfood/foodshoppingcart/delete",
                    contentType: "application/json",
				    data: JSON.stringify(cartIds),
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
		getInfo: function(cartId){
			$.get(baseURL + "fastfood/foodshoppingcart/info/"+cartId, function(r){
				if(r.errcode && r.errcode != 0){
            		return;
            	}
                vm.foodShoppingCart = r.data.foodShoppingCart;
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