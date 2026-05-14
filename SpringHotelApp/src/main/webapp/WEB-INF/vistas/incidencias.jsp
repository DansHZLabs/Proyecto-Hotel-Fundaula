<%@ page contentType="text/html;charset=UTF-8" language="java" %> <!-- esta vista es para probar luego hay que cambiarla, buscar en las plantillas de las tiendas a ver si alguna encaja -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!-- librería jstl -->

<html>
<head>
    <title>Incidencias</title><!-- título de la pestaña del navegador -->
</head>
<body>

<h1>Lista de Incidencias</h1><!-- título, se puede cambiar el tamaño poniendo h2 o h3 o lo que sea -->

<p style="color:red;">${error}</p><!-- error al borrar habitación, cambia solo el mensaje porque enlaza al error y muestra el texto que sea -->

<a href="${pageContext.request.contextPath}/incidencias/nueva">Nueva incidencia</a>
<br><!-- espacio en blanco, deja una línea -->

<table border="1"><!-- tabla con borde, tr son las filas y td las columnas,th es la cabecera de cada columna -->
    <tr>
        <th>ID de la incidencia</th>
        <th>Número de la Habitación</th>
        <th>Estado de la incidencia</th>
        <th>Prioridad de la incidencia</th>
        <th>Descripción de la incidencia</th>
        <th>Fecha apertura</th>
        <th>Fecha cierre</th>
        <th>Acciones</th>
    </tr>
    <c:forEach var="incidencia" items="${incidencias}"><!-- recorre la list y cada objeto de incidencia es una incidencia -->
        <tr>
            <td>${incidencia.idIncidencia}</td><!-- $expresion languages para llamar y traerse algo como un get -->
            <td>${incidencia.habitacion.numeroHabitacion}</td>
            <td>${incidencia.estadoIncidencia}</td>
            <td>${incidencia.prioridadIncidencia}</td>
            <td>${incidencia.descripcionIncidencia}</td>
            <td>${incidencia.fechaApertura}</td>
            <td>${incidencia.fechaCierre}</td>
            <td><!-- enlaces ver, editar y eliminar, redirigen a las otras vistas pasando por el controller -->
                <a href="${pageContext.request.contextPath}/incidencias/${incidencia.idIncidencia}">Ver</a>
                <a href="${pageContext.request.contextPath}/incidencias/editar/${incidencia.idIncidencia}">Editar</a>
                <a href="${pageContext.request.contextPath}/incidencias/eliminar/${incidencia.idIncidencia}">Eliminar</a>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>

<!-- no se pueden usar los form tags en este porque no funcionan con las tablas relacionadas solo en objetos libres -->