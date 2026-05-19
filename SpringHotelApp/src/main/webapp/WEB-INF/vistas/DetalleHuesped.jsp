<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %><!-- librería form tags -->



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

.input-formulario {
    width: 100%;
    padding: 10px;
    box-sizing: border-box;
}

</style>

</head>

<body class="fondo">


<h2 class="titulo-principal">Detalle Huesped</h2>

<div class="contenedor-formulario">
<form:form modelAttribute="detalleHuesped">
    <p>
        <strong>ID del huesped:</strong>
        <form:input cssClass="input-formulario" path="idHuesped" disabled="true"/>
    </p>
    <p>
        <strong>Nombre del huesped:</strong>
        <form:input cssClass="input-formulario" path="nombre" disabled="true"/>
    </p>
    <p>
        <strong>Apellidos del huesped:</strong>
        <form:input cssClass="input-formulario" path="apellidos" disabled="true"/>
    </p>
    <p>
        <strong>Direccion del huesped:</strong>
        <form:input cssClass="input-formulario" path="direccion" disabled="true"/>
    </p>
    <p>
        <strong>Telefono del huesped:</strong>
        <form:input cssClass="input-formulario" path="telefono" disabled="true"/>
    </p>    
    <p>
        <strong>Email del huesped:</strong>
        <form:input cssClass="input-formulario" path="idHuesped" disabled="true"/>
    </p>
</form:form>
</div>


<a href="huespedes">Volver</a>

</body>
</html>