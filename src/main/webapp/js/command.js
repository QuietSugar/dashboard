/**
 * Created by Maybe on 2018/7/1
 * Maybe has infinite possibilities
 */

var table;
$(function () {
    //时间选择  渲染
    // $('#content').datetimepicker();

    var tpl = $("#tpl").html();
    //预编译模板
    var template = Handlebars.compile(tpl);

    table = $('#example').DataTable({
        "processing": false,//刷新的那个对话框
        "serverSide": true,  //设置为服务端分页，会自动将必要参数传到服务器
        AutoWidth: false,
        "paging": true,//开启分页
        lengthMenu: [ //自定义分页长度
            [10, 20, 50],
            ['10 页', '20 页', '50页']
        ],
        ordering: false,
        bLengthChange: false,//不显示每页多少条
        "ajax": {
            "url": requestPath + "command/list",
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
        "searching": true,
        "columns": [
            //因为要加行号，所以要多一列，不然会把第一列覆盖
            {
                "data": null,
                "createdCell": createdCell
            },
            {"data": "name"},
            {"data": "content"},
            {
                "data": "type"
                , visible: false
            },
            {
                "data": "parameter"
                , visible: false
            },
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
                "targets": 5,
                width: "180px",
                "render": function (a, b, c, d) {
                    // console.log("渲染可操作参数：\n");
                    // console.log(a);
                    // console.log(b);
                    // console.log(c);
                    // console.log(d);

                    let context =
                        {
                            func: [
                                {
                                    "name": "弹",
                                    "fn": "show(\'" + d.row + "\')",
                                    "btn-type": "primary",
                                    "type": "button"
                                },
                                // {
                                //     "name": "删除",
                                //     "fn": "del(\'" + c.id + "\')",
                                //     "btn-type": "danger",
                                //     "type": "button"
                                // },
                                {
                                    "name": "详情",
                                    "url": requestPath + "statement.html?commandId=" + c.id,
                                    "btn-type": "primary",
                                    "type": "url"
                                }
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
            //初始化内容
            $("#mytool").append('<button id="reload" type="button" class="btn btn-primary btn-sm">页面刷新</button>&nbsp')
            //点击“添加”，新增的模态框
                .append('<button type="button" class="btn btn-default btn-sm" data-toggle="modal" data-target="#myModal">添加</button>')
                .append('<button type="button" class="btn btn-default btn-sm" onclick="edit()">修改</button>')
            ;

            $("#reload").click(function () {
                reload();
            });
            checkbox();
        }

    });

    $("#save").click(function () {
        /**
         * 加载数据
         **/
        let addJson = {
            "id": $("#id").val(),
            "url": $("#url").val(),
            "name": $("#name").val(),
            "content": $("#content").val(),
            "remarks": $("#remarks").val()
        };

        let functionName;
        if (addJson.id == null || addJson.id === "") {
            functionName = "add";
        } else {
            functionName = "edit";
        }
        ajaxData(functionName, addJson);
    });
});

/**
 * 清除
 */
function clear() {
    $("#id").val("").attr("disabled", false);
    $("#url").val("");
    $("#name").val("");
    $("#content").val("");
    $("#remarks").val("");
}

/**
 *编辑方法
 **/
function edit() {
    //获取当前勾选的那一行
    if (table.rows(".selected").data().length == 1) {
        let data = table.row(".selected").data();

        $("#myModalLabel").text("修改");
        $("#id").val(data.id).attr("disabled", true);
        $("#url").val(data.url);
        $("#name").val(data.name);
        $("#content").val(data.content);
        $("#remarks").val(data.remarks);
        $("#myModal").modal("show");
    } else {
        alert("请选择一条数据！");
    }
}

/**
 *
 * 显示当前行的具体内容
 */
function show(rowIndex) {
    //获取当前勾选的那一行
    let data = table.row(rowIndex).data();

    $("#myModalLabel").text("显示");
    $("#id").val(data.id).attr("disabled", true);
    $("#url").val(data.url).attr("disabled", true);
    $("#name").val(data.name).attr("disabled", true);
    $("#content").val(data.content).attr("disabled", true);
    $("#remarks").val(data.remarks).attr("disabled", true);
    $("#myModal").modal("show");
}

/**
 * 提交数据，修改或新增
 * @param functionName 方法名，add或者edit
 * @param obj
 */
function ajaxData(functionName, obj) {
    let url = requestPath + "command/" + functionName;
    $.ajax({
        url: url,
        data: {
            "id": obj.id,
            "url": obj.url,
            "name": obj.name,
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
    if (confirm("将要删除一条记录，你确定要添加么？")) {
        $.ajax({
            url: requestPath + "command/del",
            data: {
                "id": id
            }, success: function (data) {
                table.ajax.reload();
            }
        });
    }
}

/**
 * 详情查询
 * @param id
 */
function detail(id) {
    $.ajax({
        url: requestPath + "command/info",
        data: {
            "id": id
        }, success: function (data) {
            table.ajax.reload();
        }
    });
}

/**
 * init
 * @param id
 */
function initData() {
}

function createdCell(cell, cellData, rowData, rowIndex, colIndex) {
    //填充一个勾选框
    $(cell).html("<input type='checkbox' name='checkList' value='" + cellData + "'>");
}

//刷新页面
//重新加载数据
function reload() {
    console.log("页面刷新，--")
    table.ajax.reload();
}

function checkbox() {
    $('#example tbody').off("click", "tr").on("click", "tr", function () {
        $(this).toggleClass('selected');
        if ($(this).hasClass("selected")) {
            $(this).find("input").prop("checked", true);
        } else {
            $(this).find("input").prop("checked", false);
        }
    });
}
