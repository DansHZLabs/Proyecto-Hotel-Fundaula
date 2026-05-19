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

<title>HUESPEDES</title>
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

.tabla {
	border: 2pt solid;
	width: 100%;
}

.cabecera {
	font-weight: bold;
	background-color: #D9A35D;
}

.error {
	text-align: center;
	color: #D93D2E;
}
</style>
</head>


<body class="fondo">

	<h1 class="titulo-principal">HUESPEDES</h1>

	<br>
	<br>
	<br>

	<c:if
		test="${sessionScope.usuarioLogueado.rol.toString() == 'RECEPCIONISTA'}">
		<!-- Al pulsar sobre el boton se envia la accion de Login definida para el formulario -->
		<a href="nuevoHuesped">Nuevo huesped</a>
	</c:if>

	<br>

	<div class="marco">

		<table class="tabla">


			<tr class="cabecera">

				<td>ID del huesped</td>
				<td>Nombre del huesped</td>
				<td>Apellidos del huesped</td>
				<td>Acciones</td>


			</tr>


			<%--Este bucle recorre la lista de grupos musicales que nos ha enviado el Servlet a traves de la variable 'GruposMusicales' --%>
			<c:forEach var="huesped" items="${listaHuespedes}">
				<tr class="datos">
					<td>${huesped.idHuesped}</td>
					<td>${huesped.nombre}</td>
					<td>${huesped.apellidos}</td>
					<td><a href="detalleHuesped?idHuesped=${huesped.idHuesped}">Detalle</a>
						&nbsp;&nbsp;&nbsp;&nbsp; <c:if
							test="${sessionScope.usuarioLogueado.rol.toString() == 'RECEPCIONISTA'}">
							<a href="modificarHuesped?idHuesped=${huesped.idHuesped}">Modificar</a> &nbsp;&nbsp;&nbsp;&nbsp;
					<a href="eliminarHuesped?idHuesped=${huesped.idHuesped}">Eliminar</a>
						</c:if></td>
				</tr>

			</c:forEach>

		</table>



	</div>

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

	<form:form action="vueltaPrincipal" method="post">

		<!-- Al pulsar sobre el boton se vuelve al controlador del menu principal que envia la vista jsp 'Principal' -->
		<button type="submit">Vover al menu principal</button>

	</form:form>




</body>
</html>