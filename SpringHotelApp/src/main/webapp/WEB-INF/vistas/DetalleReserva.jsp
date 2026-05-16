<%@ page contentType="text/html;charset=UTF-8" language="java" %> <!-- esta vista es para probar cambiar luego -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %><!-- librería form tags -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!-- jstl -->

<html>
<head>
    <title>Detalle Reserva</title><!-- título de la pestaña del navegador -->
</head>
<body>

<form:form modelAttribute="reserva">
    <p>
        <strong>Id de la reserva:</strong>
        <form:input path="idReserva" disabled="true"/>
    </p>
    <p>
        <strong>Id de la habitación:</strong>
        <form:input path="idHabitacion" disabled="true"/>
    </p>
        <p>
        <strong>Fecha de entrada:</strong>
        <form:input path="fechaEntrada" disabled="true"/>
    </p>
        <p>
        <strong>Fecha de salida:</strong>
        <form:input path="fechaSalida" disabled="true"/>
    </p>
        <p>
        <strong>Id del huésped:</strong>
        <form:input path="idHuesped" disabled="true"/>
    </p>
    <p>
        <strong>Tipo de pensión:</strong>
        <form:input path="tipoPension" disabled="true"/>
    </p>
    <p>
        <strong>Estado de la reserva:</strong>
        <form:input path="estadoReserva" disabled="true"/>
    </p>
        <p>
        <strong>Número de huéspedes:</strong>
        <form:input path="numeroHuespedes" disabled="true"/>
    </p>
    <p>
        <strong>Observaciones:</strong>
        <form:input path="observaciones" disabled="true"/>
    </p>
</form:form>

<a href="${pageContext.request.contextPath}/reservas">Volver</a><!-- botón volver para cambiar de vista al listado-->

</body>
</html>