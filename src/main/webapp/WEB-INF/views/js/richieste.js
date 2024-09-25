$(function () {
        $('#registrationForm').submit(function (e) { 
            e.preventDefault(); 

            $('input').next('span').remove(); 

            $.post({
                url: '/register', 
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
		
		$('#prenotazioneForm').submit(function (e) { 
		        e.preventDefault(); 
		        $('#errorMessage').hide(); 

		        // Log dei dati inviati
		        console.log("Dati inviati al server:", $(this).serialize());

		        $.post({
		            url: '/salvaPrenotazione', 
		            data: $(this).serialize(), 
		            success: function (res) {
		                console.log("Risposta del server:", res); 
		                if (res.validated) {
		                    alert("Prenotazione avvenuta con successo!");
		                    window.location.href = '${pageContext.request.contextPath}/listveicoli'; 
		                } else {
		                    console.log("Errori di validazione:", res.errorMessages); 
		                    let errorMessages = Object.values(res.errorMessages).join('<br/>');
		                    $('#errorMessage').html(errorMessages).show(); 
		                }
		            },
		            error: function (xhr, status, error) {
		                console.error("Errore nella chiamata AJAX:", xhr, status, error); 
		                $('#errorMessage').html("Si è verificato un errore: " + error).show(); 
		            }
		        });
		    });
		});