<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<tiles:insertDefinition name="baseLayout">
    <tiles:putAttribute name="titolo" value="Schermata Customer" />
    <tiles:putAttribute name="content">
        <div class="container">
            <h1>Benvenuto nella Schermata Customer</h1>
            <p>Puoi utilizzare la navbar per navigare tra le varie opzioni.</p>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>
