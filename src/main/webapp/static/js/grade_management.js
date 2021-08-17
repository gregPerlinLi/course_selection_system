$(function () {
    var ajaxUrl = "http://localhost:8080/course_selection_system/admin/collegeGradeClassServlet";

    $.getJSON(ajaxUrl, "action=list&charset=utf-8&type=grade", function (data) {
        console.log(data);
        var outputTable = "";
        $.each(data, function (index, grade) {
            outputTable += "<tr id='tr_" + grade.id + "' >" +
                "<td>" + grade.id + "</td>" +
                "<td>" + grade.gradeName + "</td>" +
                "<td><button class='update' data_id='" + grade.id +"' >修改</button></td>" +
                "<td><button class='delete' data_id='" + grade.id +"'>删除</button></td>" +
                "</tr>";
        });
        $("#gradeList").append(outputTable);
    });

    $("#addCollege").click(function () {
        var newCollege = prompt("请输入你要新增的年级名");
        if ( newCollege == null || newCollege === "") {
            alert("请输入一个非空值！");
            return null;
        }
        $.getJSON(ajaxUrl, "action=add&charset=utf-8&type=grade&gradeName=" + newCollege, function (data) {
            if ( data.isAdded ){
                alert("年级添加成功！");
                window.location.reload();
            } else {
                alert("年级添加失败！");
            }
        });
    })

    $(document).on("click", ".update", function () {
        var newCollegeName = prompt("请输入新的年级名");
        if ( newCollegeName == null || newCollegeName === "") {
            alert("请输入一个非空值！");
            return null;
        }
        var id = $(this).attr("data_id");
        $.getJSON(ajaxUrl, "action=update&charset=utf-8&type=grade&id=" + id + "&gradeName=" + newCollegeName, function (data) {
            console.log(data);
            if ( data.isUpdated ) {
                alert("年级修改成功");
                window.location.reload();
            } else {
                alert("已存在相同的名字");
            }
        })
    });

    $(document).on("click", ".delete", function () {
        if (confirm("确定删除数据？")) {
            var id = $(this).attr("data_id");
            $.getJSON(ajaxUrl, "action=delete&charset=utf-8&type=grade&id=" + id, function (data) {
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
