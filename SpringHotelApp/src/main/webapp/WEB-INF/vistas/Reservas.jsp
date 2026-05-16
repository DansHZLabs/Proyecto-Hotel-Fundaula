<%@ page contentType="text/html;charset=UTF-8" language="java" %> <!-- esta vista es para probar luego hay que cambiarla -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- librería jstl -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %><!-- librería form tags -->
<!-- es igual todo que habitación, solo hay que cambiar nombres -->
<html>
<head>
    <title>Reservas</title> <!-- título de la pestaña del navegador -->
</head>
<body>

<h1>Lista de Reservas</h1><!-- título, se puede cambiar el tamaño poniendo h2 o h3 o lo que sea -->

<p style="color:red;"> ${error}</p> <!-- error al borrar habitación, cambia solo el mensaje porque enlaza al error y muestra el texto que sea -->

<c:if test="${sessionScope.rol=='RECEPCIONISTA'}"><!-- restricción por roles -->
    <a href="${pageContext.request.contextPath}/reservas/nueva">Nueva reserva</a>
</c:if>
<br><!-- espacio en blanco, deja una línea -->

<table border="1"><!-- tabla con borde, tr son las filas y td las columnas,th es la cabecera de cada columna -->
    <tr>
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
    <c:forEach var="reserva" items="${reservas}"><!-- recorre la list y cada objeto de reserva es una reserva -->
        <tr>
            <td>${reserva.idReserva}</td><!-- $expresion languages para llamar y traerse algo como un get -->
            <td>${reserva.idHabitacion}</td>
            <td>${reserva.fechaEntrada}</td>
            <td>${reserva.fechaSalida}</td>
            <td>${reserva.idHuesped}</td>
			<td>${reserva.tipoPension}</td>
			<td>${reserva.estadoReserva}</td>
			<td>${reserva.numeroHuespedes}</td>
			<td>${reserva.observaciones}</td>
            <td><!-- enlaces ver, editar y eliminar, redirigen a las otras vistas pasando por el controller -->
                <a href="${pageContext.request.contextPath}/reservas/detalle?id=${reserva.idReserva}">Ver</a>
                <c:if test="${sessionScope.rol=='RECEPCIONISTA'}"><!-- restricción por roles -->
                <a href="${pageContext.request.contextPath}/reservas/editar?id=${reserva.idReserva}">Editar</a>
                <a href="${pageContext.request.contextPath}/reservas/eliminar?id=${reserva.idReserva}">Eliminar</a>
                 </c:if>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
<!-- no se pueden usar los form tags en este porque no funcionan con las tablas relacionadas solo en objetos libres -->