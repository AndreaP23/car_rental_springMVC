<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
