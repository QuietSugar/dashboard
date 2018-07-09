/**
 * Created by Maybe on 2018/7/1
 * Maybe has infinite possibilities
 */

var table;
var editFlag = false;
$(function () {
    //时间选择  渲染
    // $('#content').datetimepicker();

    var tpl = $("#tpl").html();
    //预编译模板
    var template = Handlebars.compile(tpl);

    table = $('#example').DataTable({
        "processing": true,//刷新的那个对话框
        "serverSide": true,  //设置为服务端分页，会自动将必要参数传到服务器
        "paging": true,//开启分页
        lengthMenu: [ //自定义分页长度
            [5, 10, 20],
            ['5 页', '10 页', '20页']
        ],
        ordering: false,
        "ajax": {
            "url": "task/list",
            "type": "POST",
            "data": function (d) {
                //删除多余请求参数
                for (var key in d) {
                    if (key.indexOf("columns") == 0 || key.indexOf("order") == 0 || key.indexOf("search") == 0) { //以columns开头的参数删除
                        delete d[key];
                    }
                }
                //自定义参数都可以写在这里面
                var myParams = {
                    "hello": "Hello World!"
                };
                //附加查询参数
                if (myParams) {
                    $.extend(d, myParams); //给d扩展参数
                }
            },
            "dataType": "json",
            "dataFilter": function (json) {//json是服务器端返回的数据
                json = JSON.parse(json);
                var returnData = {};
                returnData.draw = json.draw;
                returnData.recordsTotal = json.total;//返回数据全部记录
                returnData.recordsFiltered = json.total;//后台不实现过滤功能，每次查询均视作全部结果
                returnData.data = json.data;//返回的数据列表
                return JSON.stringify(returnData);//这几个参数都是datatable需要的，必须要
            }
        },
        "searching": false,
        "columns": [
            {"data": null}, //因为要加行号，所以要多一列，不然会把第一列覆盖
            {"data": "title"},
            {"data": "content"},
            {"data": null}
        ],
        // dt默认是第一列升序排列 这里第一列为序号列，所以设置为不排序，并把默认的排序列tru面
        "order": [[1, 'asc']],
        "columnDefs": [
            {
                "searchable": false,
                "orderable": false,
                "targets": [0. - 1]
            },
            {
                //下标是4的列
                "targets": 3,
                "render": function (a, b, c, d) {
                    var context =
                    {
                        func: [
                            {
                                "name": "修改",
                                "fn": "edit(\'" + c.id + "\',\'" + c.url + "\',\'" + c.title + "\',\'" + c.content + "\',\'" + c.remarks + "\')",
                                "type": "primary"
                            },
                            {"name": "删除", "fn": "del(\'" + c.id + "\')", "type": "danger"},
                            {"name": "完成", "fn": "complete(\'" + c.id + "\')", "type": "danger"}
                        ]
                    };
                    return template(context);
                }
            }

        ],
        "language": {
            "lengthMenu": "每页_MENU_ 条记录",
            "zeroRecords": "没有找到记录",
            "info": "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
            "infoEmpty": "无记录",
            "search": "搜索：",
            "infoFiltered": "(从 _MAX_ 条记录过滤)",
            "paginate": {
                "previous": "上一页",
                "next": "下一页"
            }
        },
        "dom": "<'row'<'col-xs-2'l><'#mytool.col-xs-4'><'col-xs-6'f>r>" +
        "t" +
        "<'row'<'col-xs-6'i><'col-xs-6'p>>",
        "initComplete": function () {
            $("#mytool").append('<button id="datainit" type="button" class="btn btn-primary btn-sm">增加基础数据</button>&nbsp')
                //点击“添加”，新增的模态框
                .append('<button type="button" class="btn btn-default btn-sm" data-toggle="modal" data-target="#myModal">添加</button>');
            $("#datainit").on("click", initData);
        }

    });

    //添加序号
    //不管是排序，还是分页，还是搜索最后都会重画，这里监听draw事件即可
    table.on('draw.dt', function () {
        table.column(0, {
            search: 'applied',
            order: 'applied'
        }).nodes().each(function (cell, i) {
            //i 从0开始，所以这里先加1
            i = i + 1;
            //服务器模式下获取分页信息
            var page = table.page.info();
            //当前第几页，从0开始
            var pageno = page.page;
            //每页数据
            var length = page.length;
            //行号等于 页数*每页数据长度+行号
            var columnIndex = (i + pageno * length);
            cell.innerHTML = columnIndex;
        });
    }).draw();

    $("#save").click(add);

    $("#initData").click(initSingleData);

});

/**
 * 初始化基础数据
 */
function initData() {
    var flag = confirm("本功能将添加数据到数据库，你确定要添加么？");
    if (flag) {
        $.ajax({
            url: "task/init",
            data: {
                "num": 10
            },
            success: function (data) {
                table.ajax.reload();
                clear();
            }
        });
    }
}

/**
 * 初始化基础数据
 */
function initSingleData() {
    $("#id").val("http://datatables.club");
    $("#url").val("成都");
    $("#title").val("10000");
    $("#content").val("2016/7/10");
    $("#remarks").val("java");
}

/**
 * 清除
 */
function clear() {
    $("#id").val("").attr("disabled", false);
    $("#url").val("");
    $("#title").val("");
    $("#content").val("");
    $("#remarks").val("");
    editFlag = false;
}

/**
 * 添加数据
 **/
function add() {
    var addJson = {
        "id": $("#id").val(),
        "url": $("#url").val(),
        "title": $("#title").val(),
        "content": $("#content").val(),
        "remarks": $("#remarks").val()
    };

    ajax(addJson);
}

/**
 *编辑方法
 **/
function edit(id, url, title, content, remarks) {
    editFlag = true;
    $("#myModalLabel").text("修改");
    $("#id").val(id).attr("disabled", true);
    $("#url").val(url);
    $("#title").val(title);
    $("#content").val(content);
    $("#remarks").val(remarks);
    $("#myModal").modal("show");
}

function ajax(obj) {
    var url = "task/add";
    if (editFlag) {
        url = "task/edit";
    }
    $.ajax({
        url: url,
        data: {
            "id": obj.id,
            "url": obj.url,
            "title": obj.title,
            "content": obj.content,
            "remarks": obj.remarks
        }, success: function (data) {
            table.ajax.reload();
            $("#myModal").modal("hide");
            $("#myModalLabel").text("新增");
            clear();
        }
    });
}


/**
 * 删除数据
 * @param id
 */
function del(id) {
    $.ajax({
        url: "task/del",
        data: {
            "id": id
        }, success: function (data) {
            table.ajax.reload();
        }
    });
}
/**
 * 任务完成
 * @param id
 */
function complete(id) {
    $.ajax({
        url: "task/complete",
        data: {
            "id": id
        }, success: function (data) {
            table.ajax.reload();
        }
    });
}
