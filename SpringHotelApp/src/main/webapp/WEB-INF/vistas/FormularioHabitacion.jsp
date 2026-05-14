<%@ page contentType="text/html;charset=UTF-8" language="java" %> <!-- se pueden poner br muchas encadenadas y así se deja más hueco -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!-- librería jstl -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %><!-- librería form tags -->

<html>
<head>
    <title>Formulario Habitación</title><!-- título de la pestaña del navegador -->
</head>
<body>

<h1><!-- para cambiar entre guardar y editar -->
    <c:choose>
        <c:when test="${habitacion.idHabitacion != 0}">
            Editar Habitación
        </c:when>
        <c:otherwise>
            Nueva Habitación
        </c:otherwise>
    </c:choose>
</h1>

<form action="${pageContext.request.contextPath}/habitaciones/guardar"method="post"><!-- formulario para actualizar manda los datos al controller -->

    <input type="hidden"name="idHabitacion"value="${habitacion.idHabitacion}"><!-- guarda el id para actualizar pero no lo enseña y así sabe cuál es -->

    <label>Número:</label><!-- aquí hay que poner lo que es el hueco de cada campo que hay que rellenar -->
    <input type="text"name="numeroHabitacion"value="${habitacion.numeroHabitacion}">
    <br>

    <label>Tipo:</label>
    <input type="text"name="tipo"value="${habitacion.tipo}">
    <br>

    <label>Precio:</label>
    <input type="text"name="precioPorNoche"value="${habitacion.precioPorNoche}">
    <br>

    <label>Disponibilidad:</label>
    <select path="disponibilidad">
        <option value="disponible">disponible</option>
        <option value="ocupada">ocupada</option>
        <option value="limpieza">limpieza</option>
        <option value="mantenimiento">mantenimiento</option>
    </select>
    <br>

    <label>Orientación:</label>
    <select path="orientacionHabitacion">
        <option value="interior">Interior</option>
        <option value="exterior">Exterior</option>
    </select>
    <br>

    <button type="submit">
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
<br>

<a href="${pageContext.request.contextPath}/habitaciones">Volver</a>

</body>
</html>

<!-- no se pueden usar form tags porque se rompe el choose y pone editar y guardar y no funciona -->