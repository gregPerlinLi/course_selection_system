$(function () {
    var ajaxUrlForClassManage = "http://localhost:8080/course_selection_system/admin/collegeGradeClassServlet";
    var ajaxUrlForClassImform = "http://localhost:8080/course_selection_system/userServlet";
    var addObj = $("#add");

    $.getJSON(ajaxUrlForClassImform, "action=ajaxSearchCollege&charset=utf-8", function (data) {
        console.log(data);
        var outputString = "";
        $.each(data, function (index, college) {
            outputString += "<option>" + college.collegeName + "</option>";
        });
        $("#college").append(outputString);
    });
    $.getJSON(ajaxUrlForClassImform, "action=ajaxSearchGrade&charset=utf-8", function (data) {
        console.log(data);
        var outputString = "";
        $.each(data, function (index, grade) {
            outputString += "<option>" + grade.gradeName + "</option>";
        });
        $("#grade").append(outputString);
    });

    $("#classesName").blur( function () {
        var className = this.value;
        var infoObj = $("#classesNameInfo");

        $.getJSON(ajaxUrlForClassManage, "action=ajaxExistClassName&className=" + className, function (data) {
            console.log(data);
            if ( data.existClassName ) {
                $("#classesNameWarm").html("<img src=\"static/img/wrong.png\" height=\"18\" width=\"18\"/>");
                addObj.attr("disabled", "disabled");
                infoObj.text("班级名" + $("#classesName").val() + "已存在！");
            }
            if ( $("#classesName").val() === "" ) {
                $("#classesNameWarm").html("<img src=\"static/img/wrong.png\" height=\"18\" width=\"18\"/>");
                addObj.attr("disabled", "disabled");
                infoObj.text("班级名不能为空！");
            }
            if ( !data.existClassName && $("#classesName").val() !== "" ) {
                $("#classesNameWarm").html("<img src=\"static/img/right.png\" height=\"18\" width=\"18\"/>");
                addObj.attr("disabled", null);
                infoObj.text("");
            }
        });
    });


    $("#add").click(function () {
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
    });
});