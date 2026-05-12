<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Formulario Habitación</title>
</head>
<body>

<h1>Formulario Habitación</h1>

<form action="${pageContext.request.contextPath}/habitaciones/guardar" method="post">

    <input type="hidden" 
           name="idHabitacion" 
           value="${habitacion.idHabitacion}">

    <label>Número:</label>
    <input type="text" 
           name="numeroHabitacion" 
           value="${habitacion.numeroHabitacion}">

    <br><br>

    <label>Tipo:</label>
    <input type="text" 
           name="tipo" 
           value="${habitacion.tipo}">

    <br><br>

    <label>Precio:</label>
    <input type="text" 
           name="precioPorNoche" 
           value="${habitacion.precioPorNoche}">

    <br><br>

    <label>Disponibilidad:</label>
    <input type="text" 
           name="disponibilidad" 
           value="${habitacion.disponibilidad}">

    <br><br>

    <button type="submit">
        Guardar
    </button>

</form>

<br>

<a href="${pageContext.request.contextPath}/habitaciones">
    Volver
</a>

</body>
</html>