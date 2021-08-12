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
             "<td><a class='update' data-id='" + student.id +"' href='pages/admin/update_course.html?id=" + student.id + "'>修改</a></td>" +
             "<td><button class='delete' data-id='" + student.id +"'>删除</button></td>" +
             "</tr>";
      });
      $("#studentList").append(outputTable);
   });
});