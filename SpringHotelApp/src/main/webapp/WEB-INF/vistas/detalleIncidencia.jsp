<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %><!-- librería form tags -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!-- jstl -->

<html>
<head>
    <title>Detalle Incidencia</title><!-- título de la pestaña del navegador -->
</head>
<body>

<form:form modelAttribute="incidencia">
    <p>
        <strong>ID de la incidencia:</strong>
        <form:input path="idIncidencia" disabled="true"/>
    </p>
    <p>
        <strong>Nº de la habitación:</strong>
        <form:input path="habitacion.numeroHabitacion" disabled="true"/>
    </p>
    <p>
        <strong>Estado de la incidencia:</strong>
        <form:input path="estadoIncidencia" disabled="true"/>
    </p>
    <p>
        <strong>Prioridad de la incidencia:</strong>
        <form:input path="prioridadIncidencia" disabled="true"/>
    </p>
    <p>
        <strong>Descripción de la incidencia:</strong>
        <form:input path="descripcionIncidencia" disabled="true"/>
    </p>
    <p>
        <strong>Fecha apertura:</strong>
        <form:input path="fechaApertura" disabled="true"/>
    </p>
    <p>
        <strong>Fecha cierre:</strong>
        <form:input path="fechaCierre" disabled="true"/>
    </p>
</form:form>

<a href="${pageContext.request.contextPath}/incidencias">Volver</a>

</body>
</html>