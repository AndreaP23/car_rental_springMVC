<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 

        <div class="container mt-5">
            <h2 class="mt-4">Prenota Veicolo</h2>

            <div id="errorMessage" class="alert alert-danger" style="display: none;"></div> 

            <form:form id="prenotazioneForm" method="POST" action="${pageContext.request.contextPath}/salvaPrenotazione" modelAttribute="prenotazioneDTO">
                <form:hidden path="userId" value="${user.userId}"/>
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
            </form:form>
        </div>
