<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!doctype html>
<html lang="en">
<head>

<!-- Etiquetas para caracteres y diseno responsivo -->

<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">



<title>Datos del usuario</title>

<style>

.fondo {
	background-color: #E8B0E8;
	font-family: Arial, sans-serif;
	display: flex;
	flex-direction: column;
	align-items: center;
}

</style>
</head>


<body class= "fondo">


	<h3 class="registration">HOTEL FUNDAULA</h3>
	<br><br><br><br>

	<h2>Bienvenido, ${usuarioLogueado.username}</h2>

	<!-- se obtienen los datos obtenidos del formulario gracias al sesion almacenado en la Request  -->
	Tiene usted permisos de: ${usuarioLogueado.rol}
	
	<nav>
	
	<ul>
		<li><a href="habitaciones">Habitaciones</a></li>
		<li><a href="huespedes">Huespedes</a></li>
		<li><a href="reservas">Reservas</a></li>
		<li><a href="incidencias">Incidencias</a></li>
	
	
	
	</ul>
	
	</nav>

	<br><br><br><br>
	
	<form:form action="cerrarSesion" method="post">

		<!-- Al pulsar sobre el boton se envia la accion de Login definida para el formulario -->
		<button type="submit">Cerrar sesion</button>

	</form:form>
	
	

</body>
</html>
