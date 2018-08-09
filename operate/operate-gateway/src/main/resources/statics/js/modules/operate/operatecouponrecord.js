$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'operate/operatecouponrecord/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '优惠券ID', name: 'couponId', index: 'coupon_id', width: 80 }, 			
			{ label: '优惠券名称', name: 'name', index: 'name', width: 80 }, 			
			{ label: '0=现金，1=折扣', name: 'type', index: 'type', width: 80 }, 			
			{ label: '折扣额度或现金数', name: 'amount', index: 'amount', width: 80 }, 			
			{ label: '使用限制，满x元可用', name: 'useRestrict', index: 'use_restrict', width: 80 }, 			
			{ label: '启用时间', name: 'startTime', index: 'start_time', width: 80 }, 			
			{ label: '有效期，单位天', name: 'period', index: 'period', width: 80 }, 			
			{ label: '创建时间', name: 'createTime', index: 'create_time', width: 80 }, 			
			{ label: '领取人', name: 'userId', index: 'user_id', width: 80 }, 			
			{ label: '状态0=未使用，1=已使用', name: 'use', index: 'use', width: 80 }, 			
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
		operateCouponRecord: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.operateCouponRecord = {};
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
			var url = vm.operateCouponRecord.id == null ? "operate/operatecouponrecord/save" : "operate/operatecouponrecord/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.operateCouponRecord),
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
				    url: baseURL + "operate/operatecouponrecord/delete",
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
			$.get(baseURL + "operate/operatecouponrecord/info/"+id, function(r){
				if(r.errcode && r.errcode != 0){
            		return;
            	}
                vm.operateCouponRecord = r.data.operateCouponRecord;
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