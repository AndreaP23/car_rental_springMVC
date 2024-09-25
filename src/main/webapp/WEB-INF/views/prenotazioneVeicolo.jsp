<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Prenota Veicolo</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<c:url value='/resources/prenotazione.js' />"></script> 
</head>
<body>
   <div class="container">
    <div id="responseMessage" class="mt-3"></div>
    <h2 class="mt-4">Prenota Veicolo</h2>
    
    <div id="errorMessage" class="alert alert-danger" style="display: none;"></div> 

	
    <form:form id="prenotazioneForm" method="POST" action="${pageContext.request.contextPath}/salvaPrenotazione" modelAttribute="prenotazioneDTO">
        <div class="form-body">
            <form:hidden path="userId" value="${user.userId}"/>
            <form:hidden path="veicoloId" value="${veicolo.idVeicolo}"/>

            <div class="form-group">
                <label for="dataInizio">Data Inizio:</label>
                <form:input id="dataInizio" path="dataInizio" type="date" class="form-control" name="startDate"/>
            </div>
            
            <div class="form-group">
                <label for="dataFine">Data Fine:</label>
                <form:input id="dataFine" path="dataFine" type="date" class="form-control"/>
            </div>

            <button type="submit" class="btn btn-primary">Prenota</button>
        </div>
    </form:form>
</div>

<!--   <script>
$(function () {
    $('#prenotazioneForm').submit(function (e) { 
        e.preventDefault(); 
        $('#errorMessage').hide();

        // Log dei dati inviati
        console.log("Dati inviati al server:", $(this).serialize());

        $.post({
            url: '${pageContext.request.contextPath}/salvaPrenotazione', 
            data: $(this).serialize(), 
            success: function (res) {
                console.log("Risposta del server:", res); // Log della risposta del server
                if (res.validated) {
                    alert("Prenotazione avvenuta con successo!");
                    window.location.href = '${pageContext.request.contextPath}/listveicoli'; 
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
</script>-->

</body>
</html>