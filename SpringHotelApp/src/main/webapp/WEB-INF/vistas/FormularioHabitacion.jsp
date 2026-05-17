<%@ page contentType="text/html;charset=UTF-8" language="java" %> <!-- se pueden poner br muchas encadenadas y así se deja más hueco -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!-- librería jstl -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %><!-- librería form tags -->

<p style="color:red">${error}</p><!-- el error si no se completan los campos -->

<html>
<head>
    <title>Formulario Habitación</title><!-- título de la pestaña del navegador -->
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

.boton-formulario {
    display: block;
    margin: 55px auto;
    width: 50%;
    cursor: pointer;
    padding: 7px;
}

</style>
</head>
<body class="fondo">
<h2 class="titulo-principal">Formulario Habitación</h2>

<h1><!-- para cambiar entre guardar y editar --><!-- aquí no se llega según el rol porque ya se ocultó la opción en la lista-->
    <c:choose>
        <c:when test="${habitacion.idHabitacion != 0}">
            Editar Habitación
        </c:when>
        <c:otherwise>
            Nueva Habitación
        </c:otherwise>
    </c:choose>
</h1>

<div class="contenedor-formulario">
<form action="${pageContext.request.contextPath}/habitaciones/guardar" method="post"><!-- formulario para actualizar manda los datos al controller -->

    <input type="hidden" name="idHabitacion" value="${habitacion.idHabitacion}"><!-- guarda el id para actualizar pero no lo enseña y así sabe cuál es -->

    <label>Número:</label><!-- aquí hay que poner lo que es el hueco de cada campo que hay que rellenar -->
    <input type="text" name="numeroHabitacion" class="input-formulario" value="${habitacion.numeroHabitacion}">
    <br>

    <label>Tipo:</label>
    <select name="tipo" class="input-formulario">
        <option value="INDIVIDUAL">Individual</option>
        <option value="DOBLE">Doble</option>
        <option value="SUITE">Suite</option>
    </select>
    <br>

    <label>Precio:</label>
    <input type="text" name="precioPorNoche" class="input-formulario" value="${habitacion.precioPorNoche}">
    <br>

    <label>Disponibilidad:</label>
    <select name="disponibilidad" class="input-formulario">
        <option value="DISPONIBLE">disponible</option>
        <option value="OCUPADA">ocupada</option>
        <option value="LIMPIEZA">limpieza</option>
        <option value="MANTENIMIENTO">mantenimiento</option>
    </select>
    <br>

    <label>Orientación:</label>
    <select name="orientacionHabitacion" class="input-formulario">
        <option value="INTERIOR">Interior</option>
        <option value="EXTERIOR">Exterior</option>
    </select>
    <br>

    <button type="submit" class="boton-formulario">
        <c:choose>
            <c:when test="${habitacion.idHabitacion != 0}">
                Actualizar
            </c:when>
            <c:otherwise>
                Guardar
            </c:otherwise>
        </c:choose>
    </button>
</form>
</div>
<br>

<a href="${pageContext.request.contextPath}/habitaciones">Volver</a>

</body>
</html>

<!-- no se pueden usar form tags porque se rompe el choose y pone editar y guardar y no funciona -->