$(function () {
    var ajaxUrl = "http://localhost:8080/course_selection_system/courseSelectionServlet";

    $.getJSON(ajaxUrl, "action=getSelectedCourse&charset=utf-8", function (data) {
        console.log(data);
        var outputTable = "";
        $.each(data, function (index, selectedCoureses) {
            outputTable += "<tr id='tr_" + selectedCoureses.id + "' >" +
                "<td>" + selectedCoureses.id + "</td>" +
                "<td>" + selectedCoureses.stuNum + "</td>" +
                "<td>" + selectedCoureses.stuName + "</td>" +
                "<td>" + selectedCoureses.course + "</td>" +
                "<td><button class='cancel' data_id='" + selectedCoureses.id +"'>退选</button></td>" +
                "</tr>";
        });
        $("#courseList").append(outputTable);
        $("#title").prepend($("#account").html());
    });

    $(document).on("click", ".cancel", function () {
       if ( confirm("确定退选此课程吗？") ){
           var id = $(this).attr("data_id");
           alert("退选id为" + id + "的课程");
       }
    });
});