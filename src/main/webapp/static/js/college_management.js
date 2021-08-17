$(function () {
    var ajaxUrl = "http://localhost:8080/course_selection_system/admin/collegeGradeClassServlet";

    $.getJSON(ajaxUrl, "action=list&charset=utf-8&type=college", function (data) {
        console.log(data);
        var outputTable = "";
        $.each(data, function (index, coollege) {
            outputTable += "<tr id='tr_" + coollege.id + "' >" +
                "<td>" + coollege.id + "</td>" +
                "<td>" + coollege.collegeName + "</td>" +
                "<td><button class='update' data_id='" + coollege.id +"' >修改</button></td>" +
                "<td><button class='delete' data_id='" + coollege.id +"'>删除</button></td>" +
                "</tr>";
        });
        $("#collegeList").append(outputTable);
    });

    $("#addCollege").click(function () {
        var newCollege = prompt("请输入你要新增的学院名");
        if ( newCollege == null || newCollege === "") {
            alert("请输入一个非空值！");
            return null;
        }
        $.getJSON(ajaxUrl, "action=add&charset=utf-8&type=college&collegeName=" + newCollege, function (data) {
            if ( data.isAdded ){
                alert("学院添加成功！");
                window.location.reload();
            } else {
                alert("学院添加失败！");
            }
        });
    })

    $(document).on("click", ".update", function () {
       var newCollegeName = prompt("请输入新的学院名");
       if ( newCollegeName == null || newCollegeName === "") {
           alert("请输入一个非空值！");
           return null;
       }
       var id = $(this).attr("data_id");
       $.getJSON(ajaxUrl, "action=update&charset=utf-8&type=college&id=" + id + "&collegeName=" + newCollegeName, function (data) {
           console.log(data);
           if ( data.isUpdated ) {
               alert("学院修改成功");
               window.location.reload();
           } else {
               alert("已存在相同的名字");
           }
       })
    });

    $(document).on("click", ".delete", function () {
        if (confirm("确定删除数据？")) {
            var id = $(this).attr("data_id");
            $.getJSON(ajaxUrl, "action=delete&charset=utf-8&type=college&id=" + id, function (data) {
                console.log(data);
                if ( data.isDeleted ) {
                    alert("删除成功");
                    $("#tr_" + id).remove();
                } else {
                    alert("删除失败");
                }
            });
        }
    });
});
