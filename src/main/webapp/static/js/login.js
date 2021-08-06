$(function () {
    $("#password").change(function () {
        var password = $("#password").val();
        var encryptedPasswordObj = $("#encryptedPassword");
        encryptedPasswordObj.val($.md5(password));
        console.log(encryptedPasswordObj.val());
    });
})