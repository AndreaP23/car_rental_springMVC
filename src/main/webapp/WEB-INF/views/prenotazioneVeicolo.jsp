<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Prenotazione Veicolo</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f8f9fa;
            font-family: Arial, sans-serif;
        }
        .container {
            margin-top: 20px;
        }
        .form-group {
            margin-bottom: 1rem;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2 class="mb-4">Prenotazione Veicolo</h2>
        
        <c:if test="${not empty errorMessage}">
            <script>
                alert('${errorMessage}');
            </script>
        </c:if>
        
        <form action="${pageContext.request.contextPath}/salvaPrenotazione" method="post">
            <input type="hidden" name="veicoloId" value="${veicolo.idVeicolo}" />
            <input type="hidden" name="userId" value="${user.userId}" />
            <div class="form-group">
                <label for="dataInizio">Data Inizio</label>
                <input type="date" id="dataInizio" name="dataInizio" class="form-control" required />
            </div>
            <div class="form-group">
                <label for="dataFine">Data Fine</label>
                <input type="date" id="dataFine" name="dataFine" class="form-control" required />
            </div>
            <button type="submit" class="btn btn-primary">Salva Prenotazione</button>
        </form>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
