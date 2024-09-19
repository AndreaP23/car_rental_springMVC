<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista Veicoli</title>
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
        <h2 class="mb-4">Lista Veicoli</h2>
        <div class="table-responsive">
            <table class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <th>Marca</th>
                        <th>Modello</th>
                        <th>Anno</th>
                        <th>Prenotazione</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="veicolo" items="${veicoli}">
                        <tr>
                            <td><c:out value="${veicolo.marca}" /></td>
                            <td><c:out value="${veicolo.modello}" /></td>
                            <td><c:out value="${veicolo.anno}" /></td>
                            <td>
                                <a href="${pageContext.request.contextPath}/prenotaVeicolo?veicoloId=${veicolo.idVeicolo}" class="btn btn-primary">Prenota</a>
                            </td>
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
