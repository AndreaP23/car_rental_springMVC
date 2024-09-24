$(function () {
        $('#registrationForm').submit(function (e) { 
            e.preventDefault(); 

            $('input').next('span').remove(); 

            $.post({
                url: '${pageContext.request.contextPath}/register', 
                data: $(this).serialize(), 
                success: function (res) {
                    if (res.validated) {
                        alert("Registrazione avvenuta con successo!");
                        window.location.href = '${pageContext.request.contextPath}/login'; 
                    } else {
                        $.each(res.errorMessages, function (key, value) {
                            $('input[name=' + key + ']').after('<span class="error">' + value + '</span>'); 
                        });
                    }
                },
                error: function (xhr, status, error) {
                    alert("Si è verificato un errore: " + error); 
                }
            });
        });
    });