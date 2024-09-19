<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista Prenotazioni</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f8f9fa;
            font-family: Arial, sans-serif;
        }
        .container {
            margin-top: 20px;
        }
        .table thead th {
            background-color: #343a40;
            color: #fff;
        }
        .table td, .table th {
            vertical-align: middle;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2 class="mb-4">Lista Prenotazioni</h2>
        <div class="table-responsive">
            <table class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <th>ID Prenotazione</th>
                        <th>Data Prenotazione</th>
                        <th>Data Inizio</th>
                        <th>Data Fine</th>
                        <th>Veicolo ID</th>
                        <th>User ID</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="prenotazione" items="${prenotazioni}">
                        <tr>
                            <td><c:out value="${prenotazione.idPrenotazione}" /></td>
                            <td><c:out value="${prenotazione.dataPrenotazione}" /></td>
                            <td><c:out value="${prenotazione.dataInizio}" /></td>
                            <td><c:out value="${prenotazione.dataFine}" /></td>
                            <td><c:out value="${prenotazione.veicoloId}" /></td>
                            <td><c:out value="${prenotazione.userId}" /></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
