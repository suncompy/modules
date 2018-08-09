$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'operate/operatecoupon/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '优惠券名称', name: 'name', index: 'name', width: 80 }, 			
			{ label: '总发行量', name: 'total', index: 'total', width: 80 }, 			
			{ label: '每人限领', name: 'peoNumRestrict', index: 'peo_num_restrict', width: 80 }, 			
			{ label: '0=现金，1=折扣', name: 'type', index: 'type', width: 80 }, 			
			{ label: '折扣额度或现金数', name: 'amount', index: 'amount', width: 80 }, 			
			{ label: '使用限制，满x元可用', name: 'useRestrict', index: 'use_restrict', width: 80 }, 			
			{ label: '启用时间', name: 'startTime', index: 'start_time', width: 80 }, 			
			{ label: '有效期，单位天', name: 'period', index: 'period', width: 80 }, 			
			{ label: '领取开始时间', name: 'startGetTime', index: 'start_get_time', width: 80 }, 			
			{ label: '领取结束时间', name: 'endGetTime', index: 'end_get_time', width: 80 }, 			
			{ label: '备注', name: 'remark', index: 'remark', width: 80 }, 			
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
		operateCoupon: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.operateCoupon = {};
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
			var url = vm.operateCoupon.id == null ? "operate/operatecoupon/save" : "operate/operatecoupon/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.operateCoupon),
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
				    url: baseURL + "operate/operatecoupon/delete",
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
			$.get(baseURL + "operate/operatecoupon/info/"+id, function(r){
				if(r.errcode && r.errcode != 0){
            		return;
            	}
                vm.operateCoupon = r.data.operateCoupon;
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