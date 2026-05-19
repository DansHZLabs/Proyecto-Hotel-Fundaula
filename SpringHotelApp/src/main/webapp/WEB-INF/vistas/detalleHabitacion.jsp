<%@ page contentType="text/html;charset=UTF-8" language="java" %> <!-- esta vista es para probar cambiar luego -->

<html>
<head>
    <title>Detalle Habitación</title><!-- título de la pestaña del navegador -->
</head>
<body>

<h1>Detalle Habitación</h1><!-- título, se puede cambiar el tamaño poniendo h2 o h3 o lo que sea -->

<p>ID:${habitacion.idHabitacion}</p><!-- $expresion languages para llamar y traerse algo como un get -->
<p>Número:${habitacion.numeroHabitacion}</p>
<p>Tipo:${habitacion.tipo}</p>
<p>Precio:${habitacion.precioPorNoche}</p>

<a href="${pageContext.request.contextPath}/habitaciones">Volver</a><!-- botón volver para cambiar de vista al listado-->

</body>
</html>