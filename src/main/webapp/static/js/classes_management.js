$(function () {
    var ajaxUrl = "http://localhost:8080/course_selection_system/admin/collegeGradeClassServlet";

    $.getJSON(ajaxUrl, "action=list&charset=utf-8&type=class", function (data) {
        console.log(data);
        var outputTable = "";
        $.each(data, function (index, classes) {
            outputTable += "<tr id='tr_" + classes.id + "' >" +
                "<td>" + classes.id + "</td>" +
                "<td>" + classes.classesName + "</td>" +
                "<td>" + classes.college + "</td>" +
                "<td>" + classes.grade + "</td>" +
                "<td><a class='update' data_id='" + classes.id +"' href='pages/admin/update_class.html?id=" + classes.id + "'>修改</a></td>" +
                "<td><button class='delete' data_id='" + classes.id +"'>删除</button></td>" +
                "</tr>";
        });
        $("#classesList").append(outputTable);
    });

    $(document).on("click", ".delete", function () {
        if (confirm("确定删除数据？")) {
            var id = $(this).attr("data_id");
            $.getJSON(ajaxUrl, "action=delete&charset=utf-8&type=class&id=" + id, function (data) {
                console.log(data);
                if ( data.isDeleted ) {
                    alert("删除成功");
                    $("#tr_" + id).remove();
                } else {
                    alert("删除失败");
                }
            });
        }
    });
});