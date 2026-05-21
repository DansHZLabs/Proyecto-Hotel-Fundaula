<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- librería jstl -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %><!-- librería form tags -->

<html>
<head>
    <title>Reservas</title> <!-- título de la pestaña del navegador -->
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

.cabecera {	
	background-color: #D18400;
}


.sub-cabecera {
	
	background-color: #CCFFFF;
}

.error {
    text-align: center;
    color: #D93D2E;
}

table {
    background-color: white;
    border-collapse: collapse;
}

th, td {
    padding: 10px;
    border: 1px solid black;
}

</style>
</head>
<body class="fondo">

<h1 class="titulo-principal">Lista de Reservas</h1><!-- título, se puede cambiar el tamaño poniendo h2 o h3 o lo que sea -->



<c:if test="${sessionScope.usuarioLogueado.rol=='RECEPCIONISTA'}"><!-- restricción por roles -->
    <a href="${pageContext.request.contextPath}/reservas/nuevo">Nueva reserva</a>
</c:if>
<br><!-- espacio en blanco, deja una línea -->

<table border="1">
    <tr class="cabecera">
        <th>Id de la reserva</th>
        <th>Id de la habitación</th>
        <th>Fecha de entrada</th>
        <th>Fecha de salida</th>
        <th>Id del huésped</th>
        <th>Tipo de pensión</th>
        <th>Estado de la reserva</th>
        <th>Número de huéspedes</th>
        <th>Observaciones</th>
        <th>Acciones</th>
    </tr>
    <c:forEach var="reserva" items="${reservas}">
        <tr class="sub-cabecera">
            <td>${reserva.idReserva}</td>
            <td>${reserva.habitacion.idHabitacion}</td>
            <td>${reserva.fechaEntrada}</td>
            <td>${reserva.fechaSalida}</td>
            <td>${reserva.huesped.idHuesped}</td>
			<td>${reserva.tipoPension}</td>
			<td>${reserva.estadoReserva}</td>
			<td>${reserva.numeroHuespedes}</td>
			<td>${reserva.observaciones}</td>
            <td>
                <a href="${pageContext.request.contextPath}/reservas/detalle?idReserva=${reserva.idReserva}">Ver</a>
                <c:if test="${sessionScope.usuarioLogueado.rol=='RECEPCIONISTA'}"><!-- restricción por roles -->
                <a href="${pageContext.request.contextPath}/reservas/editar?idReserva=${reserva.idReserva}">Editar</a>
                <a href="${pageContext.request.contextPath}/reservas/eliminar?idReserva=${reserva.idReserva}">Eliminar</a>
                 </c:if>
            </td>
        </tr>
    </c:forEach>
</table>

<p class="error">${error}</p> <!-- error al borrar habitación, cambia solo el mensaje porque enlaza al error y muestra el texto que sea -->

<form:form action="${pageContext.request.contextPath}/vueltaPrincipal" method="get">

<!-- Al pulsar sobre el boton se vuelve al controlador del menu principal que envia la vista jsp 'Principal' -->
<button type="submit">Volver al menu principal</button>

</form:form>

</body>
</html>
<!-- no se pueden usar los form tags en este porque no funcionan con las tablas relacionadas solo en objetos libres -->