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
                "<td id='courseId_" + selectedCoureses.id + "'>" + selectedCoureses.course + "</td>" +
                "<td><button class='cancel' data_id='" + selectedCoureses.id +"'>退选</button></td>" +
                "</tr>";
        });
        $("#courseList").append(outputTable);
        $("#title").prepend($("#account").html());
    });

    $(document).on("click", ".cancel", function () {
        var id = $(this).attr("data_id");
        if ( confirm("确定退选课程" + $("#courseId_" + id).html() + "？") ){
            $.getJSON(ajaxUrl, "action=cancelCourse&charset=utf-8&id=" + id, function (data) {
                console.log(data);
                if ( data.isCanceled ) {
                    alert("成功退选" + $("#courseId_" + id).html() + "课程！");
                } else {
                    alert("退选" + $("#courseId_" + id).html() + "课程失败，请重新尝试！");
                }
                window.location.reload()
            });
        }
    });
});