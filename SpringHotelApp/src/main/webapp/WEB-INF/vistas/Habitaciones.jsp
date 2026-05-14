<%@ page contentType="text/html;charset=UTF-8" language="java" %> <!-- esta vista es para probar luego hay que cambiarla -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- librería jstl -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %><!-- librería form tags -->

<html>
<head>
    <title>Habitaciones</title> <!-- título de la pestaña del navegador -->
</head>
<body>

<h1>Lista de Habitaciones</h1><!-- título, se puede cambiar el tamaño poniendo h2 o h3 o lo que sea -->

<p style="color:red;"> ${error}</p> <!-- error al borrar habitación, cambia solo el mensaje porque enlaza al error y muestra el texto que sea -->

<a href="${pageContext.request.contextPath}/habitaciones/nueva">Nueva habitación</a><!-- obtiene la ruta y saca la url -->
<br><!-- espacio en blanco, deja una línea -->

<table border="1"><!-- tabla con borde, tr son las filas y td las columnas,th es la cabecera de cada columna -->
    <tr>
        <th>Id de la habitacion</th>
        <th>Número de la habitación</th>
        <th>Tipo de la habitación</th>
        <th>Precio por noche</th>
        <th>Disponibilidad de la habitación</th>
        <th>Orientación</th>
        <th>Acciones</th>
    </tr>
    <c:forEach var="habitacion" items="${habitaciones}"><!-- recorre la list y cada objeto de habitación es una habitación -->
        <tr>
            <td>${habitacion.idHabitacion}</td><!-- $expresion languages para llamar y traerse algo como un get -->
            <td>${habitacion.numeroHabitacion}</td>
            <td>${habitacion.tipo}</td>
            <td>${habitacion.precioPorNoche}</td>
			<td>${habitacion.disponibilidad}</td>
			<td>${habitacion.orientacionHabitacion}</td>
            <td><!-- enlaces ver, editar y eliminar, redirigen a las otras vistas pasando por el controller -->
                <a href="${pageContext.request.contextPath}/habitaciones/${habitacion.idHabitacion}">Ver</a>
                <a href="${pageContext.request.contextPath}/habitaciones/editar/${habitacion.idHabitacion}">Editar</a>
                <a href="${pageContext.request.contextPath}/habitaciones/eliminar/${habitacion.idHabitacion}">Eliminar</a>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
<!-- no se pueden usar los form tags en este porque no funcionan con las tablas relacionadas solo en objetos libres -->