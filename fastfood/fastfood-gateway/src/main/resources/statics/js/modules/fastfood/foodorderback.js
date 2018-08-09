$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'fastfood/foodorderback/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '订单编号,系统生成', name: 'orderNo', index: 'order_no', width: 80 }, 			
			{ label: '客服编号', name: 'serviceNo', index: 'service_no', width: 80 }, 			
			{ label: '客服姓名', name: 'serviceNickname', index: 'service_nickname', width: 80 }, 			
			{ label: '退款原因', name: 'remark', index: 'remark', width: 80 }, 			
			{ label: '反馈时间', name: 'feddbackTime', index: 'feddback_time', width: 80 }, 			
			{ label: '审核时间', name: 'checkTime', index: 'check_time', width: 80 }, 			
			{ label: '处理情况', name: 'checkRemark', index: 'check_remark', width: 80 }, 			
			{ label: '反馈用户', name: 'userId', index: 'user_id', width: 80 }, 			
			{ label: '审核人', name: 'checkId', index: 'check_id', width: 80 }, 			
			{ label: '退款金额', name: 'payAmount', index: 'pay_amount', width: 80 }, 			
			{ label: '创建时间', name: 'createTime', index: 'create_time', width: 80 }, 			
			{ label: '状态0=未处理，1=已处理', name: 'status', index: 'status', width: 80 }, 			
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
		foodOrderBack: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.foodOrderBack = {};
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
			var url = vm.foodOrderBack.id == null ? "fastfood/foodorderback/save" : "fastfood/foodorderback/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.foodOrderBack),
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
				    url: baseURL + "fastfood/foodorderback/delete",
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
			$.get(baseURL + "fastfood/foodorderback/info/"+id, function(r){
				if(r.errcode && r.errcode != 0){
            		return;
            	}
                vm.foodOrderBack = r.data.foodOrderBack;
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