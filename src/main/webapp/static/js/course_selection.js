$(function () {
    var ajaxUrl = "http://localhost:8080/course_selection_system/courseSelectionServlet";

    $.getJSON(ajaxUrl, "action=list&charset=utf-8", function (data) {
        console.log(data);
        var outputTable = "";
        $.each(data, function (index, enabledCourse) {
            outputTable += "<tr id='tr_" + enabledCourse.id + "' >" +
                "<td>" + enabledCourse.id + "</td>" +
                "<td id='courseId_" + enabledCourse.id + "'>" + enabledCourse.courseName + "</td>" +
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
        var id = $(this).attr("data_id");
        if ( confirm("确定选择课程：" + $("#courseId_" + id).html() + "？")) {

           $.getJSON(ajaxUrl, "action=selectCourse&charset=utf-8&id=" + id, function (data) {
               console.log(data);
               if ( data.selectStatus === 1 ) {
                   alert("已选择" + $("#courseId_" + id).html() + "课程！");
               } else if ( data.selectStatus === 2 ) {
                   alert("你以选过" + $("#courseId_" + id).html() + "课程，请选择其他课程！")
               } else {
                   alert( $("#courseId_" + id).html() + "课程已满，请选择其他课程！")
               }
               window.location.reload()
           });
        }
    });
});