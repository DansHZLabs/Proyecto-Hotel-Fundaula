<%@ page contentType="text/html;charset=UTF-8" language="java" %> <!-- esta vista es para probar cambiar luego -->

<html>
<head>
    <title>Detalle Habitación</title><!-- título de la pestaña del navegador -->
</head>
<body>

<h1>Detalle Habitación</h1><!-- título, se puede cambiar el tamaño poniendo h2 o h3 o lo que sea -->

<p><strong>ID de la habitación:</strong> ${habitacion.idHabitacion}</p>

<p><strong>Nº de la habitación:</strong> ${habitacion.numeroHabitacion}</p>

<p><strong>Tipo de habitación:</strong> ${habitacion.tipo}</p>

<p><strong>Precio de la habitación:</strong> ${habitacion.precioPorNoche}</p>

<p><strong>Orientación:</strong> ${habitacion.orientacionHabitacion}</p>

<a href="${pageContext.request.contextPath}/habitaciones">Volver</a><!-- botón volver para cambiar de vista al listado-->

</body>
</html>