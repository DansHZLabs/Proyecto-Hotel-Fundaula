<!-- IA: se investiga como hacer un html y css basico (no se copia y pega) sobre todo en apartados de centrado de divs y bloques. La estructura la creo yo. -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!doctype html>
<html lang="en">
<head>

<!-- Etiquetas para caracteres y diseno responsivo -->

<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Libreria para utilizar los simbolos de la casa/hotel -->

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<!-- CSS Embebido-->

<style>
.fondo {
	background-color: #E8B0E8;
	font-family: Arial, sans-serif;
	display: flex;
	flex-direction: column;
	align-items: center;
}

.titulo-principal {
	font-size: 4rem;
	font-weight: bold;
	color: #333333;
	margin: 50px 0;
	text-align: center;
}

.contenedor-formulario {
	margin-top: 100px;
	max-width: 340px;
}

.titulo-formulario {
	margin-bottom: 40px;
	font-size: 30px;
	font-weight: bold;
	text-align: center;
	text-decoration: underline;
}

.formulario-login {
	margin-bottom: 15px;
	width: 100%;
	padding: 10px;
	box-sizing: border-box;
}

.error {
	text-align: center;
	color: #D93D2E;
}

.boton-login {
	display: block;
	margin: 55px auto;
	width: 50%;
	cursor: pointer;
	padding: 7px;
}
</style>

<title>Inicio de sesión</title>
</head>

<body class="fondo">

	<h3 class="titulo-principal">
		<i class="fa fa-home"></i>HOTEL FUNDAULA<i class="fa fa-home"></i>
	</h3>


	<div class="contenedor-formulario">

		<!-- Etiqueta Spring MVC para almacenar datos del formulario en el model de la request. En este caso obtiene la plantilla vacia de credenciales que hemos creado en el 'LoginController' -->
		<form:form modelAttribute="credencialesLogin" autocomplete="off"
			method="post" action="login">


			<h3 class="titulo-formulario">Acceso de usuarios</h3>

			<form:input cssClass="formulario-login" path="username"
				placeholder="Usuario" />
			<form:password cssClass="formulario-login" path="password"
				placeholder="Password" />

			<div class="error">
				<!-- recupera de la request el mensaje de error que hemos almacenado (mediante el objeto 'Model' creado en el LoginController). Son errores tanto de credenciales vacias como erroneas segun lo buscado por el DAO en la tabla usuarios de la BBDD hoteldb -->
				<c:out value="${errorLogin}"></c:out>
			</div>

			<!-- Al pulsar sobre el boton se envia la accion de Login definida para el formulario -->
			<button type="submit" class="boton-login">Login...</button>


		</form:form>
	</div>

</body>
</html>
