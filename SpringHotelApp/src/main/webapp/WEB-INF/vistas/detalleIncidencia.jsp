<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Detalle Incidencia</title>
</head>
<body>

<h1>Detalle Incidencia</h1>

<p><strong>ID de la incidencia:</strong> ${incidencia.idIncidencia}</p>

<p><strong>Nº de la habitación:</strong> ${incidencia.habitacion.numeroHabitacion}</p>

<p><strong>Estado de la incidencia:</strong> ${incidencia.estadoIncidencia}</p>

<p><strong>Prioridad de la incidencia:</strong> ${incidencia.prioridadIncidencia}</p>

<p><strong>Descripción de la incidencia:</strong> ${incidencia.descripcionIncidencia}</p>

<p><strong>Fecha apertura:</strong> ${incidencia.fechaApertura}</p>

<p><strong>Fecha cierre:</strong> ${incidencia.fechaCierre}</p>
<br>

<a href="${pageContext.request.contextPath}/incidencias">Volver</a>

</body>
</html>