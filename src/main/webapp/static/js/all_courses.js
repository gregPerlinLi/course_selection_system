$(function () {
    var ajaxUrl = "http://localhost:8080/course_selection_system/courseSelectionServlet";

    $.getJSON(ajaxUrl, "action=getAllCourse&charset=utf-8", function (data) {
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
                "<td style='background-color: red; color: white' class='enableStatus' id='st_" + course.id + "'>" + "未开选" + "</td>" +
                "</tr>";
        });
        $("#courseList").append(outputTable);
        $.getJSON(ajaxUrl, "action=getEnabledCourse&charset=utf-8", function (data) {
            console.log(data);
            $.each(data, function (index, enableCourse) {
                $("#st_" + enableCourse.id).attr("style", "background-color: green; color: white")
                    .text("已开选");
            });
        });
    });
});