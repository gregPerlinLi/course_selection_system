$(function () {
    var ajaxUrl = "http://localhost:8080/course_selection_system/admin/selectedCourseServlet";

    $.getJSON(ajaxUrl, "action=list&charset=utf-8", function (data) {
        console.log(data);
        var outputTable = "";
        $.each(data, function (index, selectedCoureses) {
            outputTable += "<tr id='tr_" + selectedCoureses.id + "' >" +
                "<td>" + selectedCoureses.id + "</td>" +
                "<td>" + selectedCoureses.stuNum + "</td>" +
                "<td>" + selectedCoureses.stuName + "</td>" +
                "<td>" + selectedCoureses.course + "</td>" +
                "<td><button class='cancel' data_id='" + selectedCoureses.id +"'>删除</button></td>" +
                "</tr>";
        });
        $("#courseList").append(outputTable);
        $("#title").prepend($("#account").html());
    });

    $(document).on("click", ".cancel", function () {
        if ( confirm("确定删除此已选课程信息？") ){
            var id = $(this).attr("data_id");
            $.getJSON(ajaxUrl, "action=delete&charset=utf-8&id=" + id, function (data) {
                console.log(data);
                if ( data.isCanceled ) {
                    alert("已删除id为" + id + "的课程！");
                } else {
                    alert("已删除id为" + id + "的课程失败，请重新尝试！");
                }
                window.location.reload()
            });
        }
    });
});