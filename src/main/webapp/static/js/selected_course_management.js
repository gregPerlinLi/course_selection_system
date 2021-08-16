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
                "<td><button class='cancel' data-id='" + selectedCoureses.id +"'>删除</button></td>" +
                "</tr>";
        });
        $("#courseList").append(outputTable);
        $("#title").prepend($("#account").html());
    });

    $(document).on("click", ".cancel", function () {
        if ( confirm("确定删除此已选课程信息？") ){
            var id = $(this).attr("data-id");
            alert("已删除id为" + id + "的课程");
        }
    });
});