let prefix = "/sys/menu"

$(function () {
    $.get(prefix + "/menuAll.json", function (result) {
        dataInit(result.data)
    });
});

/**
 * @description 菜单表格
 * @param
 * @return
 * @author liucancan
 * @date 2018/12/6
 */
function dataInit(backData) {
    layui.use(['element', 'layer', 'form', 'upload', 'treeGrid'], function () {
        let treeGrid = layui.treeGrid;
        let datatable = backData;

        let treeTable = treeGrid.render({
            elem: '#treeTable'
            , data: datatable
            , cellMinWidth: 100
            , treeId: 'id'//树形id字段名称
            , treeUpId: 'pid'//树形父id字段名称
            , treeShowName: 'name'//以树形式显示的字段
            , cols: [[
                // {type: 'checkbox'}
                {field: 'id', width: '5%', title: 'id'}
                , {field: 'name', width: '20%', title: '名称'}
                , {field: 'address', width: '30%', title: '地址'}
                , {
                    field: 'type', width: '20%', title: '类型',
                    templet: function (d) {
                        if (d.type === 0) {
                            return '<span class="label label-primary">目录</span>';
                        }
                        if (d.type === 1) {
                            return '<span class="label label-success">菜单</span>';
                        }
                        if (d.type === 2) {
                            return '<span class="label label-warning">按钮</span>';
                        }
                    }
                },
                {
                    width: '30%', title: '操作',
                    templet: function (d) {
                        let e = '<a class="btn btn-primary btn-sm" href="javascript:void(0)" onclick=editMenu("' + d.id + '") title="编辑"><i class="fa fa-edit"></i></a>';
                        let p = '<a class="btn btn-primary btn-sm" href="#" mce_href="#" title="添加下级"><i class="fa fa-plus"></i></a> ';
                        let d1 = '<a class="btn btn-warning btn-sm" href="#" title="删除"  mce_href="#"><i class="fa fa-remove"></i></a> ';
                        return e + d1 + p;
                    }
                }
            ]]
            , page: false
        });
    });
}

/**
 * @description  编辑菜单
 * @param
 * @return
 * @author liucancan
 * @date 2018/12/6
 */
function editMenu(id) {
    $.get(prefix + "/menuInfoById.json", {id: id}, function (result) {
        console.log(result);
        if (result.rtnCode == 200) {
            document.getElementById("menuformid").reset();
            $("#starModal").modal();
            if (result.data.pid != 0) {
                $('input[name="pname"]').val(result.data.parent.name);
            }
            $('input[name="name"]').val(result.data.name);
            console.log(result.data.type);
            $("input:radio[name=type][value='" + result.data.type + "']").prop("checked", "checked");
            if (result.data.pid == 0) {
                $("input[name='address']").attr("readonly", "readonly");
            } else {
                $("input[name='address']").removeAttr("readonly");
                $("input[name='address']").val(result.data.address);
            }

        }
    });

}