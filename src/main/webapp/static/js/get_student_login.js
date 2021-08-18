$(function () {
    var ajaxUrl = "http://localhost:8080/course_selection_system/userServlet";

    $.getJSON(ajaxUrl, "action=ajaxGetStudentLogin&charset=utf-8", function (data) {
        console.log(data);
        $("#account").html(data.username);
        // $("#updateSelf").attr("href", "pages/user/update_inform.html?id=" + data.id);
    });

});