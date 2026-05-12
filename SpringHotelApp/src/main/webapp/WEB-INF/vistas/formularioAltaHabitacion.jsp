<%@ page contentType="text/html;charset=UTF-8" language="java" %>  <!-- se pueden poner br muchas encadenadas y así se deja más hueco -->

<html>
<head>
    <title>Alta Habitación</title><!-- título de la pestaña del navegador -->
</head>
<body>

<h1>Nueva Habitación</h1><!-- título, se puede cambiar el tamaño poniendo h2 o h3 o lo que sea -->

<form action="${pageContext.request.contextPath}/habitaciones/guardar"method="post"><!-- formulario para actualizar manda los datos al controller para guardar-->

    <label>Número:</label><!-- aquí hay que poner lo que es el hueco de cada campo que hay que rellenar -->
    <input type="text"name="numeroHabitacion"><!-- lo asigna -->
    <br>
    <br>

    <label>Tipo:</label>
    <input type="text"name="tipo">
    <br>
	<br>
	
    <label>Precio:</label>
    <input type="text"name="precioPorNoche">
    <br>
	<br>

    <label>Disponibilidad:</label>
    <input type="text"name="disponibilidad">
    <br>
	<br>
	
    <button type="submit">Guardar</button>
</form>
<br>

<a href="${pageContext.request.contextPath}/habitaciones">Volver</a><!-- botón volver para cambiar de vista al listado, copiar y pegar en las demás-->

</body>
</html>