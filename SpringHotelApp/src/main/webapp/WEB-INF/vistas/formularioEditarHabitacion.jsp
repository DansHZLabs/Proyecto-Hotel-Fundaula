<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Editar Habitación</title><!-- título de la pestaña del navegador -->
</head>
<body>

<h1>Editar Habitación</h1><!-- título, se puede cambiar el tamaño poniendo h2 o h3 o lo que sea -->

<form action="${pageContext.request.contextPath}/habitaciones/actualizar"method="post"><!-- formulario para actualizar manda los datos al controller -->

    <input type="hidden"name="idHabitacion"value="${habitacion.idHabitacion}"><!-- guarda el id para actualizar pero no lo enseña y así sabe cuál es -->

    <label>Número:</label><!-- aquí hay que poner lo que es el hueco de cada campo que hay que rellenar -->
    <input type="text"name="numeroHabitacion"value="${habitacion.numeroHabitacion}"><!-- coge el valor actual -->
    <br>
    <br>

    <label>Tipo:</label>
    <input type="text"name="tipo"value="${habitacion.tipo}">
    <br>
    <br>

    <label>Precio:</label>
    <input type="text"name="precioPorNoche"value="${habitacion.precioPorNoche}">
    <br>
    <br>

    <label>Disponibilidad:</label>
    <input type="text"name="disponibilidad"value="${habitacion.disponibilidad}">
    <br>
    <br>

    <button type="submit">Actualizar</button><!-- botón actualizar para hacer update de los datos -->

</form>
<br>

<a href="${pageContext.request.contextPath}/habitaciones">Volver</a><!-- botón volver para cambiar de vista al listado-->

</body>
</html>