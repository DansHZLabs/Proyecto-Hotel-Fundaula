<%@ page contentType="text/html;charset=UTF-8" language="java" %> <!-- esta vista es para probar luego hay que cambiarla -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- librería jstl -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %><!-- librería form tags -->

<html>
<head>


<!-- Etiquetas para caracteres y diseno responsivo -->

<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
    <title>Habitaciones</title> <!-- título de la pestaña del navegador -->
    <style type="text/css">

.fondo {
    background-color: #E8B0E8;
    font-family: Arial, sans-serif;
    display: flex;
    flex-direction: column;
    align-items: center;
}


.cabecera {	
	background-color: #D18400;
}


.sub-cabecera {
	
	background-color: #CCFFFF;
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

<h1 class="titulo-principal">Lista de Habitaciones</h1><!-- título, se puede cambiar el tamaño poniendo h2 o h3 o lo que sea -->

<c:if test="${sessionScope.usuarioLogueado.rol=='RECEPCIONISTA'}"><!-- restricción por roles -->
    <a href="${pageContext.request.contextPath}/habitaciones/nueva">Nueva habitación</a>
</c:if>
<br><!-- espacio en blanco, deja una línea -->

<table border="1"><!-- tabla con borde, tr son las filas y td las columnas,th es la cabecera de cada columna -->
    <tr class="cabecera">
        <th>Id de la habitacion</th>
        <th>Número de la habitación</th>
        <th>Tipo de la habitación</th>
        <th>Precio por noche</th>
        <th>Disponibilidad de la habitación</th>
        <th>Orientación</th>
        <th>Acciones</th>
    </tr>
    <c:forEach var="habitacion" items="${habitaciones}"><!-- recorre la list y cada objeto de habitación es una habitación -->
        <tr class="sub-cabecera">
            <td>${habitacion.idHabitacion}</td><!-- $expresion languages para llamar y traerse algo como un get -->
            <td>${habitacion.numeroHabitacion}</td>
            <td>${habitacion.tipo}</td>
            <td>${habitacion.precioPorNoche}</td>
			<td>${habitacion.disponibilidad}</td>
			<td>${habitacion.orientacionHabitacion}</td>
            <td><!-- enlaces ver, editar y eliminar, redirigen a las otras vistas pasando por el controller -->
                <a href="${pageContext.request.contextPath}/habitaciones/detalle?id=${habitacion.idHabitacion}">Ver</a>
         		<c:if test="${sessionScope.usuarioLogueado.rol=='RECEPCIONISTA'}"><!-- restricción por roles -->
                <a href="${pageContext.request.contextPath}/habitaciones/editar?id=${habitacion.idHabitacion}">Editar</a>
                <a href="${pageContext.request.contextPath}/habitaciones/eliminar?id=${habitacion.idHabitacion}">Eliminar</a>
      			</c:if>
            </td>
        </tr>
    </c:forEach>
</table>

<p class="error">${error}</p> <!-- error al borrar habitación, cambia solo el mensaje porque enlaza al error y muestra el texto que sea -->

<form:form action="vueltaPrincipal" method="post">

<!-- Al pulsar sobre el boton se vuelve al controlador del menu principal que envia la vista jsp 'Principal' -->
<button type="submit">Vover al menu principal</button>

</form:form>

</body>
</html>
<!-- no se pueden usar los form tags en este porque no funcionan con las tablas relacionadas solo en objetos libres -->