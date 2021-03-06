/**
 * Created by Maybe on 2018/7/1
 * Maybe has infinite possibilities
 */

let table;
let command;
$(function () {

    let commandId = getURLParameter("commandId");
    if (commandId) {
        console.log("commandId is:" + commandId);
    } else {
        console.log("命令不存在!");
        return;
    }


    var tpl = $("#tpl").html();
    //预编译模板
    var template = Handlebars.compile(tpl);

    table = $('#example').DataTable({
        "processing": false,//刷新的那个对话框
        "serverSide": true,  //设置为服务端分页，会自动将必要参数传到服务器
        "paging": true,//开启分页
        AutoWidth: false,
        lengthMenu: [ //自定义分页长度
            [10, 20, 50],
            ['10 页', '20 页', '50页']
        ],
        ordering: false,
        "ajax": {
            "url": requestPath + "statement/list",
            "type": "POST",
            "data": function (d) {
                //删除多余请求参数
                for (var key in d) {
                    if (key.indexOf("columns") == 0 || key.indexOf("order") == 0 || key.indexOf("search") == 0) { //以columns开头的参数删除
                        delete d[key];
                    }
                }
                //自定义参数都可以写在这里面
                let myParams = {
                    "commandId": commandId
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
            {"data": "description"},
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
                width: "180px",
                "render": function (a, b, c, d) {
                    var context =
                        {
                            func: [
                                {
                                    "id": "修改",
                                    "name": "修改",
                                    "fn": "edit(\'" + c.id + "\',\'" + c.description + "\',\'" + c.content + "\',\'" + c.remarks + "\')",
                                    "type": "primary"
                                },
                                {"name": "删除", "id": "删除", "fn": "del(\'" + c.id + "\')", "type": "danger"},
                                {
                                    "id": "详情",
                                    "name": "详情",
                                    "fn": "show(\'" + d.row + "\')",
                                    "type": "primary"
                                },
                                {
                                    "id": "copy",
                                    "name": "复制",
                                    "fn": "copy(\'" + d.row + "\')",
                                    "type": "primary"
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
            $("#mytool").append('<button id="delete" type="button" class="btn btn-primary btn-sm">删除</button>&nbsp')
            //点击“添加”，新增的模态框
                .append('<button type="button" class="btn btn-default btn-sm" data-toggle="modal" data-target="#myModal" id="showAdd">添加</button>');

            $("#delete").on("click", {id: "id"}, del);
            $("#showAdd").on("click", showAdd);

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

    $("#save").click(function () {
        /**
         * 加载数据
         **/
        let addJson = {
            "id": $("#id").val(),
            "commandId": commandId,
            "description": $("#description").val(),
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

    $("#clear").click(function () {
        clear();
    });
});

/**
 * 清除
 */
function clear() {
    $("#id").val("").attr("disabled", false);
    $("#description").val("");
    $("#content").val("");
    $("#remarks").val("");
}

/**
 *编辑方法
 **/
function edit(id, description, content, remarks) {
    $("#myModalLabel").text("修改");
    $("#id").val(id).attr("disabled", true);
    $("#description").val(description);
    $("#content").val(content);
    $("#remarks").val(remarks);
    $("#myModal").modal("show");
}

/**
 *点击新增展示框
 **/
function showAdd() {
    $("#myModalLabel").text("新增");
    $("#id").val(null).hide();
}

/**
 * 提交数据，修改或新增
 * @param functionName 方法名，add或者edit
 * @param obj
 */
function ajaxData(functionName, obj) {
    let url = requestPath + "statement/" + functionName;
    $.ajax({
        url: url,
        data: {
            "id": obj.id,
            "commandId": obj.commandId,
            "description": obj.description,
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
function del(event) {
    let id;
    if (event === typeof $.event) {
        id = event.data.id;
    } else {
        id = event;
    }
    console.log("del函数接收到参数：" + id);
    if (confirm("将要删除一条记录，你确定要添加么？")) {
        $.ajax({
            url: requestPath + "statement/del",
            data: {
                "id": id
            }, success: function (data) {
                table.ajax.reload();
            }
        });
    }
}

/**
 *
 * 显示当前行的具体内容
 */
function show(rowIndex) {
    //获取当前勾选的那一行
    let data = table.row(rowIndex).data();
    $("#myModalLabel").text("详情");
    $("#id").val(data.id).attr("disabled", true);
    $("#clear").hide();
    $("#content").val(data.content).attr("disabled", true);
    $("#description").val(data.description).attr("disabled", true);
    $("#remarks").val(data.remarks).attr("disabled", true);
    $("#myModal").modal("show");
}

/**
 *
 * 复制到剪贴板
 */
function copy(rowIndex) {

    let data = table.row(rowIndex).data();
    new ClipboardJS('#copy', {
        text: function (trigger) {
            console.log(trigger);
            return data.content;
        }
    });

}
