<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>



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


.tabla {
	border: 2pt solid;
	width: 50%;
}

.cabecera {
	font-weight: bold;
	background-color: #D9A35D;
}

</style>

</head>

<body class="fondo">

<h1>HOTEL FUNDAULA</h1>

<br><br><br>

<h2>HUESPEDES</h2>

<h3>Detalles huespedes</h3>

<br>

<a href="huespedes">Volver</a>

<br><br>

<table class="tabla">


<tr class="cabecera">

<td>ID del huesped</td>
<td>Nombre del huesped</td>
<td>Apellidos del huesped</td>
<td>Direccion del huesped</td>
<td>Telefono del huesped</td>
<td>Email del huesped</td>



</tr>		
				<tr>
					<td>${detalleHuesped.idHuesped}</td>
					<td>${detalleHuesped.nombre}</td>
					<td>${detalleHuesped.apellidos}</td>
					<td>${detalleHuesped.direccion}</td>
					<td>${detalleHuesped.telefono}</td>
					<td>${detalleHuesped.email}</td>
					

</tr>
						
			

</table>




</body>
</html>