<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registrazione</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body>
    <div class="container">
        <h2>Registrazione</h2>
        <form action="${pageContext.request.contextPath}/register" method="post">
            <div class="form-group">
                <label for="nome">Nome:</label>
                <input type="text" class="form-control" id="nome" name="nome" required />
            </div>
            <div class="form-group">
                <label for="cognome">Cognome:</label>
                <input type="text" class="form-control" id="cognome" name="cognome" required />
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" class="form-control" id="email" name="email" required />
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" class="form-control" id="password" name="password" required />
            </div>
            <div class="form-group">
                <label for="telefono">Telefono:</label>
                <input type="text" class="form-control" id="telefono" name="telefono" />
            </div>
            <div class="form-group">
                <label for="dataNascita">Data di Nascita:</label>
                <input type="date" class="form-control" id="dataNascita" name="dataNascita" />
            </div>
            <button type="submit" class="btn btn-primary">Registrati</button>
        </form>
    </div>
    
    <script>
        window.onload = function() {
            var successMessage = '${successMessage}';
            if (successMessage) {
                alert(successMessage);
                
                setTimeout(function() {
                    window.location.href = 'login';
                }, 2000);
            }
        };
    </script>
</body>
</html>
