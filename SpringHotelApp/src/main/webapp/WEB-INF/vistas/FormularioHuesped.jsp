<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
<!doctype html>
<html>
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

.titulo-principal {
    font-size: 3rem;
    font-weight: bold;
    color: #333333;
    margin: 40px 0;
    text-align: center;
}

.contenedor-formulario {
    margin-top: 40px;
    max-width: 400px;
    width: 100%;
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

<h2 class="titulo-principal">Formulario Huesped</h2>

<c:if test="${tipoFormulario == 'nuevo'}">
<h1>Nuevo Huesped</h1>

<form:form modelAttribute="plantillaHuesped" autocomplete="off"
			method="post" action="guardar" cssClass="contenedor-formulario">
			
			Nombre del huesped:
			
			<form:input cssClass="formulario-huespedes" path="nombre"/>
			
			Apellidos del huesped:
			
			<form:input cssClass="formulario-huespedes" path="apellidos"/>
			
			Direccion del huesped:
			
			<form:input cssClass="formulario-huespedes" path="direccion"/>
			
			Telefono del huesped:
			
			<form:input cssClass="formulario-huespedes" path="telefono"/>
			
			Email del huesped:
			
			<form:input cssClass="formulario-huespedes" path="email"/>

			<div class="error">
				<!-- recupera de la request el mensaje de error que hemos almacenado (mediante el objeto 'Model' creado en el LoginController). Son errores tanto de credenciales vacias como erroneas segun lo buscado por el DAO en la tabla usuarios de la BBDD hoteldb -->
				<c:out value="${errorActualizarHuesped}"></c:out>
			</div>
	
			<button type="submit" class="boton-almacenarHuesped">Guardar</button>

</form:form>
</c:if>



<c:if test="${tipoFormulario == 'modificado'}">
<h1>Editar Huesped</h1>
<form:form modelAttribute="plantillaHuesped" autocomplete="off"
			method="post" action="actualizar" cssClass="contenedor-formulario">
			
			<form:hidden path="idHuesped"/>
			
			Nombre del huesped:
			
			<form:input cssClass="formulario-huespedes" path="nombre"/>
			
			Apellidos del huesped:
			
			<form:input cssClass="formulario-huespedes" path="apellidos"/>
			
			Direccion del huesped:
			
			<form:input cssClass="formulario-huespedes" path="direccion"/>
			
			Telefono del huesped:
			
			<form:input cssClass="formulario-huespedes" path="telefono"/>
			
			Email del huesped:
			
			<form:input cssClass="formulario-huespedes" path="email"/>

			<div class="error">
				<!-- recupera de la request el mensaje de error que hemos almacenado (mediante el objeto 'Model' creado en el LoginController). Son errores tanto de credenciales vacias como erroneas segun lo buscado por el DAO en la tabla usuarios de la BBDD hoteldb -->
				<c:out value="${errorActualizarHuesped}"></c:out>
			</div>
	
			<button type="submit" class="boton-almacenarHuesped">Actualizar</button>


</form:form>
</c:if>


<a href="${pageContext.request.contextPath}/huespedes">Volver</a>

</body>
</html>