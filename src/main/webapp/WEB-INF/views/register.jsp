<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registrazione</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script type="text/javascript" src="<c:url value='/resources/richieste.js' />"></script>
    <script type="text/javascript">
    var contextPath = '${pageContext.request.contextPath}';
</script>
    
</head>
<body>
    <div class="container">
        <div id="responseMessage" class="mt-3"></div>
        <h2>Registrazione</h2>
        <form id="registrationForm" method="POST" action="${pageContext.request.contextPath}/register">

            <div class="form-group">
                <label for="nome">Nome</label>
                <input type="text" name="nome" class="form-control" required>
                <span class="error text-danger"></span>
            </div>
            <div class="form-group">
                <label for="cognome">Cognome</label>
                <input type="text" name="cognome" class="form-control" required>
                <span class="error text-danger"></span>
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" name="email" class="form-control" required>
                <span class="error text-danger"></span>
            </div>
            <div class="form-group">
                <label for="telefono">Telefono</label>
                <input type="text" name="telefono" class="form-control" required>
                <span class="error text-danger"></span>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" name="password" class="form-control" required>
                <span class="error text-danger"></span>
            </div>
            <div class="form-group">
                <label for="dataNascita">Data di Nascita</label>
                <input type="date" name="dataNascita" class="form-control" required>
                <span class="error text-danger"></span>
            </div>
            <button type="submit" id="submitButton" class="btn btn-primary">Registrati</button>
        </form>
    </div>
    
    <!--   <script>
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
                    console.error("Errore dettagliato:", xhr);
                    alert("Si è verificato un errore: " + error + "\nStatus: " + status);
                }
            });
        });
    });


    </script>
    -->
    
    

</body>
</html>
