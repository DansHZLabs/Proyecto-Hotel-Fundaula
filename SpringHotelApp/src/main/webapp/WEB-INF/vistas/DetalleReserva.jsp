<%@ page contentType="text/html;charset=UTF-8" language="java" %> <!-- esta vista es para probar cambiar luego -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %><!-- librería form tags -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!-- jstl -->

<html>
<head>
    <title>Detalle Reserva</title><!-- título de la pestaña del navegador -->
       <style type="text/css">

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

<div class="contenedor-formulario">
<form:form modelAttribute="reserva">
    <p>
        <strong>Id de la reserva:</strong>
        <form:input cssClass="input-formulario" path="idReserva" disabled="true"/>
    </p>
    <p>
        <strong>Id de la habitación:</strong>
        <form:input cssClass="input-formulario" path="idHabitacion" disabled="true"/>
    </p>
        <p>
        <strong>Fecha de entrada:</strong>
        <form:input cssClass="input-formulario" path="fechaEntrada" disabled="true"/>
    </p>
        <p>
        <strong>Fecha de salida:</strong>
        <form:input cssClass="input-formulario" path="fechaSalida" disabled="true"/>
    </p>
        <p>
        <strong>Id del huésped:</strong>
        <form:input cssClass="input-formulario" path="idHuesped" disabled="true"/>
    </p>
    <p>
        <strong>Tipo de pensión:</strong>
        <form:input cssClass="input-formulario" path="tipoPension" disabled="true"/>
    </p>
    <p>
        <strong>Estado de la reserva:</strong>
        <form:input cssClass="input-formulario" path="estadoReserva" disabled="true"/>
    </p>
        <p>
        <strong>Número de huéspedes:</strong>
        <form:input cssClass="input-formulario" path="numeroHuespedes" disabled="true"/>
    </p>
    <p>
        <strong>Observaciones:</strong>
        <form:input cssClass="input-formulario" path="observaciones" disabled="true"/>
    </p>
</form:form>
</div>

<a href="${pageContext.request.contextPath}/reservas">Volver</a><!-- botón volver para cambiar de vista al listado-->

</body>
</html>