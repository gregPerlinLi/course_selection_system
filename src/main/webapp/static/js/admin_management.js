$(function () {
    var ajaxUrl = "http://localhost:8080/course_selection_system/admin/adminManageServlet";

    $.getJSON(ajaxUrl, "action=list&charset=utf-8", function (data) {
        console.log(data);
        var outputTable = "";
        $.each(data, function (index, admin) {
            outputTable += "<tr id='tr_" + admin.id + "' >" +
                "<td>" + admin.id + "</td>" +
                "<td>" + admin.username + "</td>" +
                "<td>" + admin.password + "</td>" +
                "<td><a class='update' data_id='" + admin.id +"' href='pages/admin/update_admin.html?id=" + admin.id + "'>修改</a></td>" +
                "<td><button class='delete' data_id='" + admin.id +"'>删除</button></td>" +
                "</tr>";
        });
        $("#studentList").append(outputTable);
    });

    $(document).on("click", ".delete", function () {
        if (confirm("确定删除数据？")) {
            var id = $(this).attr("data_id");
            $.getJSON(ajaxUrl, "action=delete&charset=utf-8&id=" + id, function (data) {
                console.log(data);
                if (data.isDeleted) {
                    alert("删除成功");
                    $("#tr_" + id).remove();
                } else {
                    alert("删除失败");
                }
            });
        }
    });
});