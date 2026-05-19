<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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


<title>Datos del usuario</title>

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

.credenciales {
	border: 5px solid #801515;
	color: #333333;
	font-weight: bold;
	padding: 5px;
	text-align: center;	
}

.enlaces-navegacion {
	border: 2px solid #5D93D9;
	color: #333333;
	font-weight: bold;
	padding: 7px;
	text-align: center;	
	display: inline-block;
	

}

</style>
</head>


<body class="fondo">

	<h1 class="titulo-principal">
		<i class="fa fa-home"></i>HOTEL FUNDAULA<i class="fa fa-home"></i>
	</h1>

	<br><br>	

	<div class="credenciales">
	<div class="titulo-credenciales">
	
		<!-- se obtienen los datos obtenidos del formulario gracias al sesion almacenado en la Request  -->
	
		<h2>Bienvenido ${usuarioLogueado.username}</h2>
		
		<h3>Tiene usted permisos de: 
		
		<!-- Condicional que segun el rol pinta las letras de color verde (recepcionista ya que tiene mas
		permisos) o rojo (supervisor con menos permisos) -->
		
		<c:if test="${usuarioLogueado.rol  == 'SUPERVISOR'}">		
		<span style="color: #801515">${usuarioLogueado.rol}</span><!-- IA: Investigo con 'Gemini' como poner el color evitando que la palabra salte de linea, me sugiere un span --> 
		</c:if>
		
		<c:if test="${usuarioLogueado.rol  == 'RECEPCIONISTA'}">		
		<span style="color: #709E68">${usuarioLogueado.rol}</span> 
		</c:if>
		
		</h3>
		</div>
	</div>
	
	
	<br><br>
	
	
	<!-- Enlaces de navegacion general a cada funcionalidad de la app -->
	<div>
		<a class="enlaces-navegacion" href="habitaciones">Habitaciones</a> 
		<a class="enlaces-navegacion" href="huespedes">Huespedes</a>
		<a class="enlaces-navegacion" href="incidencias">Incidencias</a>
		<a class="enlaces-navegacion" href="reservas">Reservas</a>
				
	</div>
	

	<br><br><br><br>
	

	<form:form action="cerrarSesion" method="post">
	
		<!-- Al pulsar sobre el boton se envia la accion de Login definida para el formulario -->
		<button type="submit">Cerrar sesion</button>

	</form:form>
	

</body>
</html>
