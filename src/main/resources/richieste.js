$(function () {
    $('#submitButton').click(function (e) {
 
        e.preventDefault();
 
        $('input').next('span').remove();
 
        $.post({
            url: '/register',
            data: $('#registrationForm').serialize(),
            success: function (res) {
                if (res.validated) {
                    alert("Registration Successful");
                } else {
                    $.each(res.errorMessages, function (key, value) {
                        $('input[name=' + key + ']').after('<span class="error">' + value + '</span>');
                    });
                }
            }
        })
    });
 
});
