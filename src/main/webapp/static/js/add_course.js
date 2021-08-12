$(function () {
    var ajaxUrl = "http://localhost:8080/course_selection_system/admin/courseServlet";
    var addObj = $("#add");

    var date = new Date();
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var day = date.getDate();
    var hour = date.getHours();
    var minute = date.getMinutes();
    var second = date.getSeconds();
    $("#startDate").val(year + "-" + month + "-" + day);
    $("#startTime").val(hour + ":" + minute + ":" + second);

    $("#courseName").blur(function () {
       var courseName = this.value;
       var infoObj = $("#courseNameInfo");

       $.getJSON(ajaxUrl, "action=existCourseName&charset=utf-8&courseName=" +courseName, function (data) {
           console.log(data);
           if ( data.existCourseName ) {
               $("#courseNameWarm").html("<img src=\"static/img/wrong.png\" height=\"18\" width=\"18\"/>");
               addObj.attr("disabled", "disabled");
               infoObj.text("课程" + $("#courseName").val() + "已存在！");
           }
           if ( $("#courseName").val() === "" ) {
               $("#courseNameWarm").html("<img src=\"static/img/wrong.png\" height=\"18\" width=\"18\"/>");
               addObj.attr("disabled", "disabled");
               infoObj.text("课程名不能为空！");
           }
           if ( !data.existCourseName && $("#courseName").val() !== "" ) {
               $("#courseNameWarm").html("<img src=\"static/img/right.png\" height=\"18\" width=\"18\"/>");
               addObj.attr("disabled", null);
               infoObj.text("");
           }
       });

    });

    $("#startDate").blur(function () {
        var date = $("#startDate").val();
        var infoObj = $("#startDateInfo");
        var regDate = /^[0-9]{4}-[0-1]?[0-9]{1}-[0-3]?[0-9]{1}$/;

        if (regDate.test(date)) {
            $("#startDateWarm").html("<img src=\"static/img/right.png\" height=\"18\" width=\"18\"/>");
            addObj.attr("disabled", null);
            infoObj.text("");
        } else {
            $("#startDateWarm").html("<img src=\"static/img/wrong.png\" height=\"18\" width=\"18\"/>");
            addObj.attr("disabled", "disabled");
            infoObj.text("日期格式错误!");
        }
    });

    $("#startTime").blur(function () {
        var time = $("#startTime").val();
        var infoObj = $("#startTimeInfo");
        var regTime = /^([0-2][0-9]):([0-5][0-9]):([0-5][0-9])$/;
        var result = false;

        if (regTime.test(time)) {
            //通过正则表达式分组或去不同的分组
            if ((parseInt(RegExp.$1) < 24) && (parseInt(RegExp.$2) < 60) && (parseInt(RegExp.$3) < 60)) {
                result = true;
            }
        }
        if (result) {
            $("#startTimeWarm").html("<img src=\"static/img/right.png\" height=\"18\" width=\"18\"/>");
            addObj.attr("disabled", null);
            infoObj.text("");
        } else {
            $("#startTimeWarm").html("<img src=\"static/img/wrong.png\" height=\"18\" width=\"18\"/>");
            addObj.attr("disabled", "disabled");
            infoObj.text("时间格式错误!");
        }
    });

});