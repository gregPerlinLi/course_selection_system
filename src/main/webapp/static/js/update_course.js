$(function () {
    var ajaxUrl = "http://localhost:8080/course_selection_system/admin/courseServlet";
    var updateObj = $("#update");

    var date = new Date();
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var day = date.getDate();
    var hour = date.getHours();
    var minute = date.getMinutes();
    var second = date.getSeconds();
    $("#startDate").val(year + "-" + month + "-" + day);
    $("#startTime").val(hour + ":" + minute + ":" + second);

    var curId = null;
    var curCourseName = null;
    var curStartDate = null;
    var curStartTime = null;
    var curMaxStu = null;

    $.getJSON(ajaxUrl, "action=getCourse&charset=utf-8&id=" + getQueryString("id"), function (data) {
        $("#id").val(data.id);
        $("#courseName").val(data.courseName);
        $("#startDate").val(data.startDate);
        $("#startTime").val(timeConvert(data.startTime));
        $("#maxStu").val(data.maxStu);
        curId = data.id;
        curCourseName = data.courseName;
        curStartDate = data.startDate;
        curStartTime = timeConvert(data.startTime);
        curMaxStu = data.maxStu;
    });

    function timeConvert(time) {
        var list = time.split(" 上午")
        if( list.length !== 2 ) {
            console.log("下午")
            list = time.split(" 下午")
            if(list.length === 2){
                //获取小时
                var hh = list[0].toString().substring(0,2);
                //把小时加上12，即是对应的下午小时时间
                if ( hh !== "12" ) {
                    var newhh = parseInt(hh) + 12;
                    //获取分和秒
                    var mmss = list[0].substring(2,9)
                    //再把前面切割出来的日期以及小时以及分秒拼接出来，
                    //即是24小时制
                    return newhh.toString()+mmss
                } else {
                    return list[0];
                }
            }
        }else {
            //这是上午的情况，直接把上午的字体给去掉即可
            console.log("上午")
            var hh = list[0].toString().substring(0,2);
            if ( hh !== "12" ) {
                return list[0]
            } else {
                var newhh = "00";
                //获取分和秒
                var mmss = list[0].substring(2,9)
                //再把前面切割出来的日期以及小时以及分秒拼接出来，
                //即是24小时制
                return newhh.toString()+mmss
            }
        }
    }

    function getQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]);
        return null;
    }

    $("#courseName").blur(function () {
        var courseName = this.value;
        var infoObj = $("#courseNameInfo");

        $.getJSON(ajaxUrl, "action=existCourseName&charset=utf-8&courseName=" + courseName, function (data) {
            console.log(data);
            if ( data.existCourseName && $("#courseName").val() !== curCourseName ) {
                $("#courseNameWarm").html("<img src=\"static/img/wrong.png\" height=\"18\" width=\"18\"/>");
                updateObj.attr("disabled", "disabled");
                infoObj.text("课程" + $("#courseName").val() + "已存在！");
            }
            if ( $("#courseName").val() === "" ) {
                $("#courseNameWarm").html("<img src=\"static/img/wrong.png\" height=\"18\" width=\"18\"/>");
                updateObj.attr("disabled", "disabled");
                infoObj.text("课程名不能为空！");
            }
            if ( ( !data.existCourseName || $("#courseName").val() === curCourseName ) && $("#courseName").val() !== "" ) {
                $("#courseNameWarm").html("<img src=\"static/img/right.png\" height=\"18\" width=\"18\"/>");
                updateObj.attr("disabled", null);
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
            updateObj.attr("disabled", null);
            infoObj.text("");
        } else {
            $("#startDateWarm").html("<img src=\"static/img/wrong.png\" height=\"18\" width=\"18\"/>");
            updateObj.attr("disabled", "disabled");
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
            updateObj.attr("disabled", null);
            infoObj.text("");
        } else {
            $("#startTimeWarm").html("<img src=\"static/img/wrong.png\" height=\"18\" width=\"18\"/>");
            updateObj.attr("disabled", "disabled");
            infoObj.text("时间格式错误!");
        }
    });

    updateObj.click(function () {
        $("#id").attr("disabled", null);
    })

});