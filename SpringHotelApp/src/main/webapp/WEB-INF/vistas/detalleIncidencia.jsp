<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %><!-- librería form tags -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!-- jstl -->

<html>
<head>
    <title>Detalle Incidencia</title><!-- título de la pestaña del navegador -->
    
       <style>

    .fondo {
        background-color: #E8B0E8;
        font-family: Arial, sans-serif;
        display: flex;
        flex-direction: column;
        align-items: center;
    }

    .titulo-principal {
        font-size: 3rem;
        font-weight: bold;
        color: #333333;
        margin: 40px 0;
        text-align: center;
    }

    .contenedor-formulario {
        margin-top: 40px;
        max-width: 400px;
        width: 100%;
    }

    .input-formulario {
        width: 100%;
        padding: 10px;
        box-sizing: border-box;
    }

    </style>
</head>
<body class="fondo">

<h2 class="titulo-principal">Detalle Incidencia</h2>

<div class="contenedor-formulario">

<form:form modelAttribute="incidencia">
    <p>
        <strong>ID de la incidencia:</strong>
        <form:input cssClass="input-formulario" path="idIncidencia" disabled="true"/>
    </p>
    <p>
        <strong>Nº de la habitación:</strong>
        <form:input cssClass="input-formulario" path="habitacion.numeroHabitacion" disabled="true"/>
    </p>
    <p>
        <strong>Estado de la incidencia:</strong>
        <form:input cssClass="input-formulario" path="estadoIncidencia" disabled="true"/>
    </p>
    <p>
        <strong>Prioridad de la incidencia:</strong>
        <form:input cssClass="input-formulario" path="prioridadIncidencia" disabled="true"/>
    </p>
    <p>
        <strong>Descripción de la incidencia:</strong>
        <form:input cssClass="input-formulario" path="descripcionIncidencia" disabled="true"/>
    </p>
    <p>
        <strong>Fecha apertura:</strong>
        <form:input cssClass="input-formulario" path="fechaApertura" disabled="true"/>
    </p>
    <p>
        <strong>Fecha cierre:</strong>
        <form:input cssClass="input-formulario" path="fechaCierre" disabled="true"/>
    </p>
</form:form>
</div>

<a href="${pageContext.request.contextPath}/incidencias">Volver</a>

</body>
</html>