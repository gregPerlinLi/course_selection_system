$(function () {
    var ajaxUrlForClassManage = "http://localhost:8080/course_selection_system/admin/collegeGradeClassServlet";
    var ajaxUrlForClassInform = "http://localhost:8080/course_selection_system/userServlet";
    var updateObj = $("#update");

    var curId = null;
    var curClassName = null;

    $.getJSON(ajaxUrlForClassInform, "action=ajaxSearchCollege&charset=utf-8", function (data) {
        console.log(data);
        var outputString = "";
        $.each(data, function (index, college) {
            outputString += "<option>" + college.collegeName + "</option>";
        });
        $("#college").append(outputString);
    });
    $.getJSON(ajaxUrlForClassInform, "action=ajaxSearchGrade&charset=utf-8", function (data) {
        console.log(data);
        var outputString = "";
        $.each(data, function (index, grade) {
            outputString += "<option>" + grade.gradeName + "</option>";
        });
        $("#grade").append(outputString);
    });

    $.getJSON(ajaxUrlForClassManage, "action=getClass&charset=utf-8&id=" + getQueryString("id"), function (data) {
        // 这里需要现关闭异步
        $.ajaxSettings.async = false;
        console.log(getQueryString("id"));
        $("#id").val(data.id);
        $("#classesName").val(data.classesName);
        $("#college").val(data.college);
        $("#grade").val(data.grade);
        curClassName = data.classesName;
        // 使用完后记得打开异步
        $.ajaxSettings.async = true;
    });

    $("#classesName").blur( function () {
        var className = this.value;
        var infoObj = $("#classesNameInfo");

        $.getJSON(ajaxUrlForClassManage, "action=ajaxExistClassName&className=" + className, function (data) {
            console.log(data);
            if ( data.existClassName && curClassName !== $("#classesName").val() ) {
                $("#classesNameWarm").html("<img src=\"static/img/wrong.png\" height=\"18\" width=\"18\"/>");
                updateObj.attr("disabled", "disabled");
                infoObj.text("班级名" + $("#classesName").val() + "已存在！");
            }
            if ( $("#classesName").val() === "" ) {
                $("#classesNameWarm").html("<img src=\"static/img/wrong.png\" height=\"18\" width=\"18\"/>");
                updateObj.attr("disabled", "disabled");
                infoObj.text("班级名不能为空！");
            }
            if ( ( !data.existUsername || curClassName === $("#classesName").val() ) && $("#classesName").val() !== "" ) {
                $("#classesNameWarm").html("<img src=\"static/img/right.png\" height=\"18\" width=\"18\"/>");
                updateObj.attr("disabled", null);
                infoObj.text("");
            }
        });
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
        if ( wrongNum > 0 ) {
            return false;
        }
        $("#id").attr("disabled", null);
    });
});