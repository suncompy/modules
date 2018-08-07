$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'account/userscoreachievementaward/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '任务码', name: 'code', index: 'code', width: 80 }, 			
			{ label: '任务名', name: 'name', index: 'name', width: 80 }, 			
			{ label: '任务分类', name: 'cat', index: 'cat', width: 80 }, 			
			{ label: '任务分类名', name: 'catName', index: 'cat_name', width: 80 }, 			
			{ label: '任务条件', name: 'condi', index: 'condi', width: 80 }, 			
			{ label: '任务触发点', name: 'action', index: 'action', width: 80 }, 			
			{ label: '任务奖励', name: 'bonus', index: 'bonus', width: 80 }, 			
			{ label: '创建时间', name: 'createTime', index: 'create_time', width: 80 }, 			
			{ label: '最后更新时间', name: 'updateTime', index: 'update_time', width: 80 }, 			
			{ label: '创建人', name: 'updateBy', index: 'update_by', width: 80 }, 			
			{ label: '是否删除  -1：已删除  0：正常', name: 'delFlag', index: 'del_flag', width: 80 }, 			
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
		userScoreAchievementAward: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.userScoreAchievementAward = {};
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
			var url = vm.userScoreAchievementAward.id == null ? "account/userscoreachievementaward/save" : "account/userscoreachievementaward/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.userScoreAchievementAward),
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
				    url: baseURL + "account/userscoreachievementaward/delete",
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
			$.get(baseURL + "account/userscoreachievementaward/info/"+id, function(r){
				if(r.errcode && r.errcode != 0){
            		return;
            	}
                vm.userScoreAchievementAward = r.data.userScoreAchievementAward;
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