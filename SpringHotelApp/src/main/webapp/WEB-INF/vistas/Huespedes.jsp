<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>

<!-- Etiquetas para caracteres y diseno responsivo -->

<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>Lista de Huespedes</title>

<style>

.titulo-principal {
	font-size: 3rem;
	font-weight: bold;
	color: #333333;
	margin: 40px 0;
	text-align: center;
}

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

table {
    background-color: white;
    border-collapse: collapse;
}

th, td {
    padding: 10px;
    border: 1px solid black;
}

.error {
	text-align: center;
	color: #D93D2E;
}
</style>
</head>


<body class="fondo">

	<h1 class="titulo-principal">Lista de Huespedes</h1>

	<c:if
		test="${sessionScope.usuarioLogueado.rol.toString() == 'RECEPCIONISTA'}">		
		<a href="nuevoHuesped">Nuevo huesped</a> <!-- Al pulsar sobre el boton se envia la accion de Login definida para el formulario -->
	</c:if>

	<br>
	
		<table border="1">
		
			<tr class="cabecera">
				<th>ID del huesped</th>
				<th>Nombre del huesped</th>
				<th>Apellidos del huesped</th>
				<th>Acciones</th>
			</tr>
						
			<c:forEach var="huesped" items="${listaHuespedes}">
				<tr class="sub-cabecera">
					<td>${huesped.idHuesped}</td>
					<td>${huesped.nombre}</td>
					<td>${huesped.apellidos}</td>
					<td><a href="detalleHuesped?idHuesped=${huesped.idHuesped}">Ver</a>
					 <c:if test="${sessionScope.usuarioLogueado.rol.toString() == 'RECEPCIONISTA'}">
							<a href="modificarHuesped?idHuesped=${huesped.idHuesped}">Editar</a> 
							<a href="eliminarHuesped?idHuesped=${huesped.idHuesped}">Eliminar</a>
						</c:if></td>
				</tr>

			</c:forEach>

		</table>





	<div class="error">
		<!-- recupera de la request el mensaje de error que hemos almacenado -->
		<c:out value="${errorObtenerHuespedes}"></c:out>
		<c:out value="${errorEliminarHuesped}"></c:out>
		<c:out value="${errorActualizarHuesped}"></c:out>
	</div>

	<br>
	<br>
	<br>



	<br>
	<br>

	<form:form action="vueltaPrincipal" method="get">

		<!-- Al pulsar sobre el boton se vuelve al controlador del menu principal que envia la vista jsp 'Principal' -->
		<button type="submit">Vover al menu principal</button>

	</form:form>




</body>
</html>