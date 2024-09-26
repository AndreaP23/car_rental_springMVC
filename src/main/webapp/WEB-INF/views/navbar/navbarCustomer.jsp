<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">Rental Car</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCustomer" aria-controls="navbarCustomer" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarCustomer">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="listveicoli">Lista Veicoli</a>
            </li>
            <li class="nav-item">
                <a class="nav-link btn btn-danger" href="logout">Logout</a>
            </li>
        </ul>
    </div>
</nav>
