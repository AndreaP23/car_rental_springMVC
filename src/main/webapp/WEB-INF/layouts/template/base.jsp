<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%> 
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
     <title><tiles:getAsString name="titolo" /></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link href="<c:url value='/static/css/style.css' />" rel="stylesheet">
</head>
<body>
    <div>
        <tiles:insertAttribute name="navbar" />
    </div>

    <div class="container mt-4">
        <tiles:insertAttribute name="content" />
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<c:url value='/static/js/prenotazione.js' />"></script> 
</body>
</html>
