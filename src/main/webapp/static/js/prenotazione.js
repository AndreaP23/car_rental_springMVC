$(function () {
    $('#prenotazioneForm').submit(function (e) { 
        e.preventDefault(); 
        $('#errorMessage').hide();

        // Log dei dati inviati
        console.log("Dati inviati al server:", $(this).serialize());
		var url = $(this).attr('action')

        $.post({
            url: url , 
            data: $(this).serialize(), 
            success: function (res) {
                console.log("Risposta del server:", res); // Log della risposta del server
                if (res.validated) {
                    alert("Prenotazione avvenuta con successo!");
                    window.location.href = '/rentalcar_spring/listveicoli'; 
                } else {
                    console.log("Errori di validazione:", res.errorMessages); // Log degli errori
                    let errorMessages = Object.values(res.errorMessages).join('<br/>');
                    $('#errorMessage').html(errorMessages).show(); // Mostra i messaggi di errore
                }
            },
            error: function (xhr, status, error) {
                console.error("Errore nella chiamata AJAX:", xhr, status, error); // Log dell'errore
                $('#errorMessage').html("Si è verificato un errore: " + error).show(); // Mostra l'errore generale
            }
        });
    });
});