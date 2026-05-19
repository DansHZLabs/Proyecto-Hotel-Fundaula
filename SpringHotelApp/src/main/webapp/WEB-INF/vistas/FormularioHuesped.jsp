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



<title>Formulario Huespedes</title>

<style>

.fondo {
	background-color: #E8B0E8;
	font-family: Arial, sans-serif;
	display: flex;
	flex-direction: column;
	align-items: center;
}


form {
	width: 400px;
	font-weight: bold;
	color: #333;
	margin: 0 auto;
}


form, form text {
	display: block; 
}


.formulario-huespedes {
	width: 100%;           
	margin-top: 5px;       
	margin-bottom: 18px;   
	padding: 8px;          
	box-sizing: border-box;
}

.error {
	text-align: center;
	color: #D93D2E;
}

.boton-almacenarHuesped {
	display: block;
	margin: 55px auto;
	width: 50%;
	cursor: pointer;
	padding: 7px;
}


</style>

</head>

<body class= "fondo">

<h1>HOTEL FUNDAULA</h1>
	<br><br><br><br>

<h2>HUESPEDES</h2>

<br>

<a href="huespedes">Volver</a>

<br><br>

<c:if test="${tipoFormulario == 'nuevo'}">
<form:form modelAttribute="plantillaHuesped" autocomplete="off"
			method="get" action="guardarHuesped">
			
			Nombre del Huesped
			
			<form:input cssClass="formulario-huespedes" path="nombre"/>
			
			Apellidos del Huesped
			
			<form:input cssClass="formulario-huespedes" path="apellidos"/>
			
			Direccion del Huesped
			
			<form:input cssClass="formulario-huespedes" path="direccion"/>
			
			Telefono del Huesped
			
			<form:input cssClass="formulario-huespedes" path="telefono"/>
			
			Email del Huesped
			
			<form:input cssClass="formulario-huespedes" path="email"/>

			<div class="error">
				<!-- recupera de la request el mensaje de error que hemos almacenado (mediante el objeto 'Model' creado en el LoginController). Son errores tanto de credenciales vacias como erroneas segun lo buscado por el DAO en la tabla usuarios de la BBDD hoteldb -->
				<c:out value="${errorActualizarHuesped}"></c:out>
			</div>
	
			<button type="submit" class="boton-almacenarHuesped">Guardar</button>

</form:form>
</c:if>



<c:if test="${tipoFormulario == 'modificado'}">
<form:form modelAttribute="plantillaHuesped" autocomplete="off"
			method="get" action="actualizarHuesped">
			
			<form:hidden path="idHuesped"/>
			
			Nombre del Huesped
			
			<form:input cssClass="formulario-huespedes" path="nombre"/>
			
			Apellidos del Huesped
			
			<form:input cssClass="formulario-huespedes" path="apellidos"/>
			
			Direccion del Huesped
			
			<form:input cssClass="formulario-huespedes" path="direccion"/>
			
			Telefono del Huesped
			
			<form:input cssClass="formulario-huespedes" path="telefono"/>
			
			Email del Huesped
			
			<form:input cssClass="formulario-huespedes" path="email"/>

			<div class="error">
				<!-- recupera de la request el mensaje de error que hemos almacenado (mediante el objeto 'Model' creado en el LoginController). Son errores tanto de credenciales vacias como erroneas segun lo buscado por el DAO en la tabla usuarios de la BBDD hoteldb -->
				<c:out value="${errorActualizarHuesped}"></c:out>
			</div>
	
			<button type="submit" class="boton-almacenarHuesped">Guardar</button>


</form:form>
</c:if>




</body>
</html>