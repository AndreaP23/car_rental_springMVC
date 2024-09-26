<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">Rental Car</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSuperUser" aria-controls="navbarSuperUser" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSuperUser">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="listuser">Lista Utenti</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="listprenotazioni">Lista Prenotazioni</a>
            </li>
            <li class="nav-item">
                <a class="nav-link btn btn-danger" href="logout">Logout</a>
            </li>
        </ul>
    </div>
</nav>
