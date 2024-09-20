<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Prenota Veicolo</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h2 class="mt-4">Prenota Veicolo</h2>
        <form:form method="POST" action="${pageContext.request.contextPath}/salvaPrenotazione" modelAttribute="prenotazioneDTO">

            <div class="form-body">

                <form:hidden path="userId" value="${userId}"/>
                <form:hidden path="veicoloId" value="${veicolo.idVeicolo}"/>

                <div class="form-group">
                    <label for="dataInizio">Data Inizio:</label>
                    <form:input id="dataInizio" path="dataInizio" type="date" class="form-control"/>
                </div>

                <div class="form-group">
                    <label for="dataFine">Data Fine:</label>
                    <form:input id="dataFine" path="dataFine" type="date" class="form-control"/>
                </div>

                <button type="submit" class="btn btn-primary">Prenota</button>
            </div>
        </form:form>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
