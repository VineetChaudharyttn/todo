$(document).ready(function () {
    $('#confirmpassword').blur(function () {
        var password = document.getElementById("inputPassword")
            , confirm_password = document.getElementById("confirmpassword");
        if(password.value != confirm_password.value) {
            $('#err').text("Passwords Don't Match");
            $('#confirmpassword').val("");
        } else {
            $('#err').text("");
        }
    })
});

