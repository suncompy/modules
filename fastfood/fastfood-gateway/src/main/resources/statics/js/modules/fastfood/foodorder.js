$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'fastfood/foodorder/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '订单编号,系统生成', name: 'orderNo', index: 'order_no', width: 80 }, 			
			{ label: '用户ID', name: 'userId', index: 'user_id', width: 80 }, 			
			{ label: '支付方式 0=积分兑换，1=在线支付，2=余额支付', name: 'payType', index: 'pay_type', width: 80 }, 			
			{ label: '订单状态 -2=已删除，-1=已取消，0=待支付，1=已支付，2=已取单，3=已评价', name: 'orderStatus', index: 'order_status', width: 80 }, 			
			{ label: '订单金额', name: 'orderAmount', index: 'order_amount', width: 80 }, 			
			{ label: '订单金额', name: 'redPackedAmount', index: 'red_packed_amount', width: 80 }, 			
			{ label: '订单积分', name: 'orderScore', index: 'order_score', width: 80 }, 			
			{ label: '支付金额，折扣抵消', name: 'payAmount', index: 'pay_amount', width: 80 }, 			
			{ label: '商品总数量', name: 'buyNumber', index: 'buy_number', width: 80 }, 			
			{ label: '取餐机ID', name: 'macId', index: 'mac_id', width: 80 }, 			
			{ label: '活动ID', name: 'activityId', index: 'activity_id', width: 80 }, 			
			{ label: '活动类型', name: 'activityType', index: 'activity_type', width: 80 }, 			
			{ label: '优惠券抵扣', name: 'couponId', index: 'coupon_id', width: 80 }, 			
			{ label: '红包抵扣', name: 'redPackedId', index: 'red_packed_id', width: 80 }, 			
			{ label: '订单来源', name: 'source', index: 'source', width: 80 }, 			
			{ label: '创建时间', name: 'createTime', index: 'create_time', width: 80 }, 			
			{ label: '更新时间', name: 'updateTime', index: 'update_time', width: 80 }, 			
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
		foodOrder: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.foodOrder = {};
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
			var url = vm.foodOrder.id == null ? "fastfood/foodorder/save" : "fastfood/foodorder/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.foodOrder),
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
				    url: baseURL + "fastfood/foodorder/delete",
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
			$.get(baseURL + "fastfood/foodorder/info/"+id, function(r){
				if(r.errcode && r.errcode != 0){
            		return;
            	}
                vm.foodOrder = r.data.foodOrder;
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