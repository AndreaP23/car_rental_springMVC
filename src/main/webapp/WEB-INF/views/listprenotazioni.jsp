<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<tiles:insertDefinition name="baseLayout">
    <tiles:putAttribute name="titolo" value="Schermata SuperUser" />
    <tiles:putAttribute name="content">
        <div class="container">
            <h2 class="mb-4">Lista Prenotazioni</h2>

            <!-- Modulo di ricerca -->
            <form action="${pageContext.request.contextPath}/listprenotazioni" method="get" class="mb-4">
                <div class="row">
                    <div class="col">
                        <input type="text" name="userId" class="form-control" placeholder="User ID" value="${param.userId}" />
                    </div>
                    <div class="col">
                        <input type="text" name="veicoloId" class="form-control" placeholder="Veicolo ID" value="${param.veicoloId}" />
                    </div>
                    <div class="col">
                        <input type="date" name="dataInizio" class="form-control" value="${param.dataInizio}" />
                    </div>
                    <div class="col">
                        <input type="date" name="dataFine" class="form-control" value="${param.dataFine}" />
                    </div>
                    <div class="col">
                        <button type="submit" class="btn btn-primary">Cerca</button>
                    </div>
                </div>
            </form>

            <!-- Tabella delle prenotazioni -->
            <div class="table-responsive">
                <table class="table table-striped table-bordered">
                    <thead class="thead-dark">
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
            <a href="${pageContext.request.contextPath}/schermataSuperUser" class="btn btn-secondary">Torna Indietro</a>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
