<%@ page contentType="text/html;charset=UTF-8" language="java" %> <!-- esta vista es para probar luego hay que cambiarla, buscar en las plantillas de las tiendas a ver si alguna encaja -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!-- librería jstl -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %><!-- librería form tags -->

<html>
<head>



    <title>Incidencias</title><!-- título de la pestaña del navegador -->
    <style>

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

.error {
    text-align: center;
    color: #D93D2E;
}

.cabecera {	
	background-color: #D18400;
}


.sub-cabecera {
	
	background-color: #CCFFFF;
}

table {
    background-color: white;
    border-collapse: collapse;
}

th, td {
    padding: 10px;
    border: 1px solid black;
}

</style>
</head>
<body class="fondo">

<h1 class="titulo-principal">Lista de Incidencias</h1><!-- título, se puede cambiar el tamaño poniendo h2 o h3 o lo que sea -->

<c:if test="${sessionScope.usuarioLogueado.rol=='RECEPCIONISTA'}"><!-- restricción por roles -->
    <a href="${pageContext.request.contextPath}/incidencias/nueva">Nueva incidencia</a>
</c:if>
<br><!-- espacio en blanco, deja una línea -->

<table border="1"><!-- tabla con borde, tr son las filas y td las columnas,th es la cabecera de cada columna -->
    <tr class="cabecera">
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
        <tr class="sub-cabecera">
            <td>${incidencia.idIncidencia}</td><!-- $expresion languages para llamar y traerse algo como un get -->
            <td>${incidencia.habitacion.numeroHabitacion}</td>
            <td>${incidencia.estadoIncidencia}</td>
            <td>${incidencia.prioridadIncidencia}</td>
            <td>${incidencia.descripcionIncidencia}</td>
            <td>${incidencia.fechaApertura}</td>
            <td>${incidencia.fechaCierre}</td>
            <td><!-- enlaces ver, editar y eliminar, redirigen a las otras vistas pasando por el controller -->
                <a href="${pageContext.request.contextPath}/incidencias/detalle?id=${incidencia.idIncidencia}">Ver</a>
                <c:if test="${sessionScope.usuarioLogueado.rol=='RECEPCIONISTA'}"><!-- restricción por roles -->
                <a href="${pageContext.request.contextPath}/incidencias/editar?id=${incidencia.idIncidencia}">Editar</a>
                <a href="${pageContext.request.contextPath}/incidencias/eliminar?id=${incidencia.idIncidencia}">Eliminar</a>
                 </c:if>
            </td>
        </tr>
    </c:forEach>
</table>

<p class="error">${error}</p><!-- error al borrar habitación, cambia solo el mensaje porque enlaza al error y muestra el texto que sea -->

<form:form action="${pageContext.request.contextPath}/vueltaPrincipal" method="get">

<!-- Al pulsar sobre el boton se vuelve al controlador del menu principal que envia la vista jsp 'Principal' -->
<button type="submit">Volver al menu principal</button>

</form:form>

</body>
</html>

<!-- no se pueden usar los form tags en este porque no funcionan con las tablas relacionadas solo en objetos libres -->