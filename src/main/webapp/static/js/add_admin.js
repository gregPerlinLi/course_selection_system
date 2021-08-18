$(function () {
    var ajaxUrl = "http://localhost:8080/course_selection_system/admin/adminManageServlet";
    var addObj = $("#add");

    $("#username").blur( function () {
        var username = this.value;
        var infoObj = $("#userInfo");

        $.getJSON(ajaxUrl, "action=ajaxExistAdminUsername&username=" + username, function (data) {
            console.log(data);
            if ( data.existUsername ) {
                $("#userWarm").html("<img src=\"static/img/wrong.png\" height=\"18\" width=\"18\"/>");
                addObj.attr("disabled", "disabled");
                infoObj.text("用户名" + $("#username").val() + "已存在！");
            }
            if ( $("#username").val() === "" ) {
                $("#userWarm").html("<img src=\"static/img/wrong.png\" height=\"18\" width=\"18\"/>");
                addObj.attr("disabled", "disabled");
                infoObj.text("用户名不能为空！");
            }
            if ( !data.existUsername && $("#username").val() !== "" ) {
                $("#userWarm").html("<img src=\"static/img/right.png\" height=\"18\" width=\"18\"/>");
                addObj.attr("disabled", null);
                infoObj.text("");
            }
        });
    });

    $("#password").change(function () {
        var password = $("#password").val();
        var infoObj = $("#passwordInfo");
        var encryptedPasswordObj = $("#encryptedPassword");
        var patt = /^\w{6,32}$/;
        encryptedPasswordObj.val($.md5(password));
        console.log(encryptedPasswordObj.val());
        if (patt.test(password)) {
            $("#passwordWarm").html("<img src=\"static/img/right.png\" height=\"18\" width=\"18\"/>");
            addObj.attr("disabled", null);
            infoObj.text("");
        } else {
            $("#passwordWarm").html("<img src=\"static/img/wrong.png\" height=\"18\" width=\"18\"/>");
            addObj.attr("disabled", "disabled");
            infoObj.text("密码不合法，请输入6～32个英文或数字字符！");
        }
    });

    $("#confirmPassword").change(function () {
        var password = $("#password").val();
        var confirmPassword = $("#confirmPassword").val();
        var infoObj = $("#confirmPasswordInfo");
        var patt = /^\w{6,32}$/;
        if ( password === confirmPassword && patt.test(password) ) {
            $("#confirmPasswordWarm").html("<img src=\"static/img/right.png\" height=\"18\" width=\"18\"/>");
            addObj.attr("disabled", null);
            infoObj.text("");
        } else {
            $("#confirmPasswordWarm").html("<img src=\"static/img/wrong.png\" height=\"18\" width=\"18\"/>");
            addObj.attr("disabled", "disabled");
            infoObj.text("密码不一致！");
        }
    });

    addObj.click(function () {
        var notEncryptedPassword = $("#password").val();
        $("#password").val($.md5(notEncryptedPassword));
    });
});