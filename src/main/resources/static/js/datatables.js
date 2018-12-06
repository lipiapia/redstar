// http://blog.csdn.net/mickey_miki/article/details/8240477/
function initTables(url, columns, columnDefs) {
    var table = $("#initTables").DataTable({
        "bProcessing": true,
        "bServerSide": true,
        "bPaginate": true, //开关，是否显示分页器
        "bAutoWidth": true,////自动宽度
        "bInfo": true,//页脚信息
        "bFilter": false,//禁用搜索
        "bSort": false,//禁用排序
        "bLengthChange": true,//显示每页条数
        "aLengthMenu": [20, 50, 100, 200],
        "sPaginationType": 'full_numbers',
        "oLanguage": {
            "sProcessing": "正在加载中......",
            "sLengthMenu": "每页 _MENU_ 条",
            "sZeroRecords": "对不起，查询不到相关数据!",
            "sInfo": " _START_ 至 _END_ 项结果，共 _TOTAL_ 项",//显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项
            "sInfoEmpty": "",//显示第 0 至 0 项结果，共 0 项
            "sInfoFiltered": "",//(由 _MAX_ 项结果过滤)
            "sInfoPostFix": "",
            "sSearch": "搜索：",
            "sUrl": "",
            "sEmptyTable": "暂无数据!",
            "sLoadingRecords": "载入中...",
            "sInfoThousands": ",",
            "oPaginate": {
                "sFirst": "首页",
                "sPrevious": "上一页",
                "sNext": "下一页",
                "sLast": "末页"
            }
        },
        "ajax": {
            "url": url,
            "type": "POST",
            data: function (d) {
                var formData = $("#filter_form").serializeArray();//把form里面的数据序列化成数组
                formData.forEach(function (e) {
                    d[e.name] = e.value;
                    d._csrf = $("input[name=_csrf]").val();
                });
            }
        },
        "columns": columns,
        "columnDefs": columnDefs,
        "fnRowCallback": function (nRow, aData, iDisplayIndex) {
            $("td:first", nRow).html(iDisplayIndex + 1);
            return nRow;
        },
        initComplete: initComplete
    });


    function initComplete() {
        //当选择时间后，出发dt的重新加载数据的方法
        $("#search-button").on('click', function () {
            //当选择时间后，出发dt的重新加载数据的方法
            table.draw();
        });
    };

    return table;
}