$(function () {
    var ajaxUrl="http://localhost:8080/course_selection_system/userServlet";
    var token = null;
    $.getJSON(ajaxUrl, "action=ajaxGetToken&charset=utf-8", function (data) {
        token = String(data.token).toLowerCase();
        console.log(token);
    });

    $("#code").blur(function () {
        if ( token != null && token === String($("#code").val()).toLowerCase() ) {
            $("#codeInfo").html("");
            $("#login").attr("disabled", null);
            $("#regist").attr("disabled", null);
            $("#codeWarm").html("<img src=\"static/img/right.png\" height=\"18\" width=\"18\"/>");
        } else {
            $("#codeInfo").html("验证码错误");
            $("#login").attr("disabled", "disabled");
            $("#regist").attr("disabled", "disabled");
            $("#codeWarm").html("<img src=\"static/img/wrong.png\" height=\"18\" width=\"18\"/>");
        }
    })

    $("#codeImg").click(function () {
        this.src = "kaptcha.jpg?d=" + new Date();
        $.getJSON(ajaxUrl, "action=ajaxGetToken&charset=utf-8", function (data) {
            token = String(data.token).toLowerCase();
            console.log(token);
        });
    });
});