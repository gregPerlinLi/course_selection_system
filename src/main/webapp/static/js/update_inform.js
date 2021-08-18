$(function () {
    var ajaxUrl = "http://localhost:8080/course_selection_system/userServlet";
    var updateObj = $("#update");

    var curId = null;
    var curStuNum = null;
    var curStuUsername = null;

    $.getJSON(ajaxUrl, "action=ajaxSearchCollege&charset=utf-8", function (data) {
        console.log(data);
        var outputString = "";
        $.each(data, function (index, college) {
            outputString += "<option>" + college.collegeName + "</option>";
        });
        $("#college").append(outputString);
    });
    $.getJSON(ajaxUrl, "action=ajaxSearchGrade&charset=utf-8", function (data) {
        console.log(data);
        var outputString = "";
        $.each(data, function (index, grade) {
            outputString += "<option>" + grade.gradeName + "</option>";
        });
        $("#grade").append(outputString);
    });

    $("#college").change(function () {
        var college = this.value;
        var grade = $("#grade").val();
        getClasses(college, grade);
    });

    $("#grade").change(function () {
        var college = $("#college").val();
        var grade = this.value
        getClasses(college, grade);
    });

    $.getJSON(ajaxUrl, "action=ajaxGetStudentLogin&charset=utf-8", function (data) {
        // 这里需要现关闭异步
        $.ajaxSettings.async = false;
        console.log(data);
        $("#id").val(data.id);
        $("#stuNum").val(data.stuNum);
        $("#username").val(data.username);
        $("#college").val(data.college);
        $("#grade").val(data.grade);
        getClasses(data.college, data.grade);
        $("#stuClass").val(data.stuClass);
        curId = data.id;
        curStuNum = data.stuNum;
        curStuUsername = data.username;
        // 使用完后记得打开异步
        $.ajaxSettings.async = true;
    });

    /**
     * 获取该学院和年级下的所有班级
     *
     * @param college 提供班级所在的学院
     * @param grade 提供班级所在的班级
     */
    function getClasses(college, grade) {
        $("#stuClass").empty().append("<option disabled selected>----请选择----</option>");
        $.getJSON(ajaxUrl, "action=ajaxSearchClasses&charset=utf-8&college=" + college + "&grade=" + grade, function (data) {
            console.log(data);
            var outputString = "";
            $.each(data, function (index, classes) {
                outputString += "<option>" + classes.classesName + "</option>";
            });
            $("#stuClass").append(outputString);
        });
    }

    $("#stuNum").blur( function () {
        var stuNum = this.value;
        var infoObj = $("#stuNumInfo");
        var patt = /^\d{10}$/;

        $.getJSON(ajaxUrl, "action=ajaxExistStuNum&stuNum=" + stuNum, function (data) {
            console.log(data);
            if ( data.existStuNum && curStuNum !== $("#stuNum").val() ) {
                $("#stuNumWarm").html("<img src=\"static/img/wrong.png\" height=\"18\" width=\"18\"/>");
                updateObj.attr("disabled", "disabled");
                infoObj.text("学号" + $("#stuNum").val() + "已存在！");
            }
            if ( !patt.test($("#stuNum").val()) ) {
                $("#stuNumWarm").html("<img src=\"static/img/wrong.png\" height=\"18\" width=\"18\"/>");
                updateObj.attr("disabled", "disabled");
                infoObj.text("学号格式错误！");
            }
            if ( ( !data.existStuNum || curStuNum === $("#stuNum").val() ) && patt.test($("#stuNum").val()) ) {
                $("#stuNumWarm").html("<img src=\"static/img/right.png\" height=\"18\" width=\"18\"/>");
                updateObj.attr("disabled", null);
                infoObj.text("");
            }
        });
    });

    $("#username").blur( function () {
        var username = this.value;
        var infoObj = $("#userInfo");

        $.getJSON(ajaxUrl, "action=ajaxExistStuUsername&username=" + username, function (data) {
            console.log(data);
            if ( data.existUsername && curStuUsername !== $("#username").val() ) {
                $("#userWarm").html("<img src=\"static/img/wrong.png\" height=\"18\" width=\"18\"/>");
                updateObj.attr("disabled", "disabled");
                infoObj.text("姓名" + $("#username").val() + "已存在！");
            }
            if ( $("#username").val() === "" ) {
                $("#userWarm").html("<img src=\"static/img/wrong.png\" height=\"18\" width=\"18\"/>");
                updateObj.attr("disabled", "disabled");
                infoObj.text("姓名不能为空！");
            }
            if ( ( !data.existUsername || curStuUsername === $("#username").val() ) && $("#username").val() !== "" ) {
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
        var wrongNum = 0;
        if ( $("#college").val() === null ) {
            $("#collegeWarm").html("<img src=\"static/img/wrong.png\" height=\"18\" width=\"18\"/>");
            $("#collegeInfo").text("请选择所在学院");
            wrongNum++;
        }
        if ( $("#grade").val() === null ) {
            $("#gradeWarm").html("<img src=\"static/img/wrong.png\" height=\"18\" width=\"18\"/>");
            $("#gradeInfo").text("请选择所在年级");
            wrongNum++;
        }
        if ( $("#stuClass").val() === null ) {
            $("#classWarm").html("<img src=\"static/img/wrong.png\" height=\"18\" width=\"18\"/>");
            $("#classInfo").text("请选择所在班级");
            wrongNum++;
        }
        if ( wrongNum > 0 ) {
            return false;
        }
        $("#id").attr("disabled", null);
        var notEncryptedPassword = $("#password").val();
        $("#password").val($.md5(notEncryptedPassword));
    });

});