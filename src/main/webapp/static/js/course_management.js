$(function () {
    var ajaxUrl = "http://localhost:8080/course_selection_system/admin/courseServlet";

    $.getJSON(ajaxUrl, "action=list&charset=utf-8", function (data) {
        console.log(data);
        var outputTable = "";
        $.each(data, function (index, course) {
            outputTable += "<tr id='tr_" + course.id + "' >" +
                "<td>" + course.id + "</td>" +
                "<td>" + course.courseName + "</td>" +
                "<td>" + course.startDate + "</td>" +
                "<td>" + course.startTime + "</td>" +
                "<td>" + course.maxStu + "</td>" +
                "<td>" + course.currentStu + "</td>" +
                "<td><a class='update' data-id='" + course.id +"' href='pages/admin/update_course.html?id=" + course.id + "'>修改</a></td>" +
                "<td><button class='delete' data-id='" + course.id +"'>删除</button></td>" +
                "</tr>";
        });
        $("#courseList").append(outputTable);
    });



    $(document).on("click", ".delete", function () {
        if (confirm("确定删除数据？")) {
            var id = $(this).attr("data-id");
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