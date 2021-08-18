$(function () {
    var ajaxAdminManageUrl = "http://localhost:8080/course_selection_system/admin/adminManageServlet";
    var ajaxAdminInformUrl = "http://localhost:8080/course_selection_system/adminServlet"
    var updateObj = $("#update");

    var curId = null;
    var curStuNum = null;
    var curUsername = null;

    $.getJSON(ajaxAdminInformUrl, "action=ajaxGetAdminLogin&charset=utf-8", function (data) {
        console.log(data);
        $("#id").val(data.id);
        $("#username").val(data.username);
        curId = data.id;
        curUsername = data.username;
    });

    $("#username").blur( function () {
        var username = this.value;
        var infoObj = $("#userInfo");

        $.getJSON(ajaxAdminManageUrl, "action=ajaxExistAdminUsername&username=" + username, function (data) {
            console.log(data);
            if ( data.existUsername && curUsername !== $("#username").val() ) {
                $("#userWarm").html("<img src=\"static/img/wrong.png\" height=\"18\" width=\"18\"/>");
                updateObj.attr("disabled", "disabled");
                infoObj.text("姓名" + $("#username").val() + "已存在！");
            }
            if ( $("#username").val() === "" ) {
                $("#userWarm").html("<img src=\"static/img/wrong.png\" height=\"18\" width=\"18\"/>");
                updateObj.attr("disabled", "disabled");
                infoObj.text("姓名不能为空！");
            }
            if ( ( !data.existUsername || curUsername === $("#username").val() ) && $("#username").val() !== "" ) {
                $("#userWarm").html("<img src=\"static/img/right.png\" height=\"18\" width=\"18\"/>");
                updateObj.attr("disabled", null);
                infoObj.text("");
            }
        });
    });

    $("#password").blur(function () {
        var password = $("#password").val();
        var infoObj = $("#passwordInfo");
        var encryptedPasswordObj = $("#encryptedPassword");
        var patt = /^\w{6,32}$/;
        encryptedPasswordObj.val($.md5(password));
        console.log(encryptedPasswordObj.val());
        if (patt.test(password)) {
            $("#passwordWarm").html("<img src=\"static/img/right.png\" height=\"18\" width=\"18\"/>");
            updateObj.attr("disabled", null);
            infoObj.text("");
        } else {
            $("#passwordWarm").html("<img src=\"static/img/wrong.png\" height=\"18\" width=\"18\"/>");
            updateObj.attr("disabled", "disabled");
            infoObj.text("密码不合法，请输入6～32个英文或数字字符！");
        }
    });

    $("#confirmPassword").blur(function () {
        var password = $("#password").val();
        var confirmPassword = $("#confirmPassword").val();
        var infoObj = $("#confirmPasswordInfo");
        var patt = /^\w{6,32}$/;
        if ( password === confirmPassword && patt.test(password) ) {
            $("#confirmPasswordWarm").html("<img src=\"static/img/right.png\" height=\"18\" width=\"18\"/>");
            updateObj.attr("disabled", null);
            infoObj.text("");
        } else {
            $("#confirmPasswordWarm").html("<img src=\"static/img/wrong.png\" height=\"18\" width=\"18\"/>");
            updateObj.attr("disabled", "disabled");
            infoObj.text("密码不一致！");
        }
    });

    $("#update").click(function () {
        $("#id").attr("disabled", null);
        var notEncryptedPassword = $("#password").val();
        $("#password").val($.md5(notEncryptedPassword));
    });

});