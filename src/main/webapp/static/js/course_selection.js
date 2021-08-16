$(function () {
    var ajaxUrl = "http://localhost:8080/course_selection_system/courseSelectionServlet";

    $.getJSON(ajaxUrl, "action=list&charset=utf-8", function (data) {
        console.log(data);
        var outputTable = "";
        $.each(data, function (index, enabledCourse) {
            outputTable += "<tr id='tr_" + enabledCourse.id + "' >" +
                "<td>" + enabledCourse.id + "</td>" +
                "<td>" + enabledCourse.courseName + "</td>" +
                "<td>" + enabledCourse.startDate + "</td>" +
                "<td>" + enabledCourse.startTime + "</td>" +
                "<td>" + enabledCourse.maxStu + "</td>" +
                "<td>" + enabledCourse.currentStu + "</td>" +
                "<td><button class='select' data_id='" + enabledCourse.id +"'>选课</button></td>" +
                "</tr>";
        });
        $("#courseList").append(outputTable);
    });

    $(document).on("click", ".select", function () {
       if ( confirm("确定选择此课程？") ) {
           var id = $(this).attr("data_id");
           alert("已选择id为" + id + "的课程");
       } 
    });
});