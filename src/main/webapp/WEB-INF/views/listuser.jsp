<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

        <div class="container mt-4">
            <h2 class="mb-4">Lista Utenti</h2>

            <!-- Modulo di ricerca -->
            <form action="${pageContext.request.contextPath}/listuser" method="get" class="mb-4">
                <div class="row">
                    <div class="col">
                        <input type="text" name="nome" class="form-control" placeholder="Nome" />
                    </div>
                    <div class="col">
                        <input type="text" name="cognome" class="form-control" placeholder="Cognome" />
                    </div>
                    <div class="col">
                        <input type="text" name="email" class="form-control" placeholder="Email" />
                    </div>
                    <div class="col">
                        <input type="text" name="telefono" class="form-control" placeholder="Telefono" />
                    </div>
                    <div class="col">
                        <button type="submit" class="btn btn-primary">Cerca</button>
                    </div>
                </div>
            </form>

            <!-- Tabella di visualizzazione utenti -->
            <div class="table-responsive">
                <table class="table table-striped table-bordered">
                    <thead class="thead-dark">
                        <tr>
                            <th>ID</th>
                            <th>Nome</th>
                            <th>Cognome</th>
                            <th>Email</th>
                            <th>Telefono</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="user" items="${users}">
                            <tr>
                                <td><c:out value="${user.userId}" /></td>
                                <td><c:out value="${user.nome}" /></td>
                                <td><c:out value="${user.cognome}" /></td>
                                <td><c:out value="${user.email}" /></td>
                                <td><c:out value="${user.telefono}" /></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
