$(function () {
    var ajaxUrl = "http://localhost:8080/course_selection_system/adminServlet";

    $.getJSON(ajaxUrl, "action=ajaxGetAdminLogin&charset=utf-8", function (data) {
        console.log(data);
        $("#account").html(data.username);
    });
});