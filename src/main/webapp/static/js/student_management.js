$(function () {
   var ajaxUrl = "http://localhost:8080/course_selection_system/admin/studentServlet";

   $.getJSON(ajaxUrl, "action=list&charset=utf-8", function (data) {
      console.log(data);
      var outputTable = "";
      $.each(data, function (index, student) {
         outputTable += "<tr id='tr_" + student.id + "' >" +
             "<td>" + student.id + "</td>" +
             "<td>" + student.stuNum + "</td>" +
             "<td>" + student.username + "</td>" +
             "<td>" + student.password + "</td>" +
             "<td>" + student.college + "</td>" +
             "<td>" + student.grade + "</td>" +
             "<td>" + student.stuClass + "</td>" +
             "<td><a class='update' data-id='" + student.id +"' href='pages/admin/update_student.html?id=" + student.id + "'>修改</a></td>" +
             "<td><button class='delete' data-id='" + student.id +"'>删除</button></td>" +
             "</tr>";
      });
      $("#studentList").append(outputTable);
   });

   $(document).on("click", ".delete", function () {
      if (confirm("确定删除数据？")) {
         var id = $(this).attr("data-id");
         $.getJSON(ajaxUrl, "action=delete&charset=utf-8&id=" + id, function (data) {
            console.log(data);
            if (data.isDeleted) {
               alert("删除成功");
               $("#tr_" + id).remove();
            } else {
               alert("删除失败");
            }
         });
      }
   });
});