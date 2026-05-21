<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!-- librería jstl -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><!-- librería jstl para formatear fechas -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %><!-- librería form tags -->
    
    
<!DOCTYPE html>

<html>
<head>

<meta charset="ISO-8859-1">

<title>Insert title here</title>
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



.formulario-reservas {
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

<body class="fondo">

<h2 class="titulo-principal">Formulario Reserva</h2>

<c:if test="${tipoFormulario == 'nuevo'}">
<h1>Nueva Reserva</h1>
<form:form modelAttribute="plantillaReserva" autocomplete="off"
			method="post" action="guardar" cssClass="contenedor-formulario">
			
			<form:hidden path="idReserva"/>
			
			Id de la habitación:
			
			<select name="habitacion.idHabitacion" class="formulario-reservas">
   			 <c:forEach var="habitacion" items="${habitaciones}">
       			 <option value="${habitacion.idHabitacion}"<c:if test="${plantillaReserva.habitacion != null&& habitacion.idHabitacion == plantillaReserva.habitacion.idHabitacion}">selected</c:if>>${habitacion.numeroHabitacion}</option>
    		 </c:forEach>
		</select>
			
			
			Fecha de entrada:
			
			<form:input cssClass="formulario-reservas" path="fechaEntrada" type="date"/>
			
			Fecha de salida:
			
			<form:input cssClass="formulario-reservas" path="fechaSalida" type="date"/>
			
			Id del huésped:
			
			<select name="huesped.idHuesped" class="formulario-reservas">
   			 <c:forEach var="huesped" items="${huespedes}">
       			 <option value="${huesped.idHuesped}"<c:if test="${plantillaReserva.huesped != null&& huesped.idHuesped == plantillaReserva.huesped.idHuesped}">selected</c:if>>${huesped.idHuesped}</option>
    		 </c:forEach>
			</select>
			
			Tipo de pensión:
			
			<form:select cssClass="formulario-reservas" path="tipoPension">
			<form:option value="ALOJAMIENTO">alojamiento</form:option>
    		<form:option value="MEDIA">media</form:option>
    		<form:option value="COMPLETA">completa</form:option>
			</form:select>
			
			Estado de la reserva:
			
			<form:select cssClass="formulario-reservas" path="estadoReserva">
			<form:option value="PENDIENTE">pendiente</form:option>
    		<form:option value="CANCELADA">cancelada</form:option>
    		<form:option value="CONFIRMADA">confirmada</form:option>			
			</form:select>
			
			Número de huéspedes:
			
			<form:input cssClass="formulario-reservas" path="numeroHuespedes"/>
			
			Observaciones:
			
			<form:input cssClass="formulario-reservas" path="observaciones"/>

			<div class="error">
				<!-- recupera de la request el mensaje de error que hemos almacenado (mediante el objeto 'Model' creado en el LoginController). Son errores tanto de credenciales vacias como erroneas segun lo buscado por el DAO en la tabla usuarios de la BBDD hoteldb -->
				<c:out value="${error}"></c:out>
			</div>
	
			<button type="submit" class="boton-almacenarHuesped">Guardar</button>


</form:form>
</c:if>


<c:if test="${tipoFormulario == 'modificado'}">
<h1>Editar Reserva</h1>
<form:form modelAttribute="plantillaReserva" autocomplete="off"
			method="post" action="actualizar" cssClass="contenedor-formulario">
			
			<form:hidden path="idReserva"/>
			
			Id de la habitación:
			
			<select name="habitacion.idHabitacion" class="formulario-reservas">
   			 <c:forEach var="habitacion" items="${habitaciones}">
       			 <option value="${habitacion.idHabitacion}"<c:if test="${plantillaReserva.habitacion != null&& habitacion.idHabitacion == plantillaReserva.habitacion.idHabitacion}">selected</c:if>>${habitacion.numeroHabitacion}</option>
    		 </c:forEach>
			</select>
			
			Fecha de entrada:
			
			<form:input cssClass="formulario-reservas" path="fechaEntrada" type="date"/>
			
			Fecha de salida:
			
			<form:input cssClass="formulario-reservas" path="fechaSalida" type="date"/>
			
			Id del huésped:
			
			<select name="huesped.idHuesped" class="formulario-reservas">
   			 <c:forEach var="huesped" items="${huespedes}">
       			 <option value="${huesped.idHuesped}"<c:if test="${plantillaReserva.huesped != null&& huesped.idHuesped == plantillaReserva.huesped.idHuesped}">selected</c:if>>${huesped.idHuesped}</option>
    		 </c:forEach>
			</select>
			
			Tipo de pensión:
			
			<form:select cssClass="formulario-reservas" path="tipoPension">
			<form:option value="ALOJAMIENTO">alojamiento</form:option>
    		<form:option value="MEDIA">media</form:option>
    		<form:option value="COMPLETA">completa</form:option>
			</form:select>
			
			Estado de la reserva:
			
			<form:select cssClass="formulario-reservas" path="estadoReserva">
			<form:option value="PENDIENTE">pendiente</form:option>
    		<form:option value="CANCELADA">cancelada</form:option>
    		<form:option value="CONFIRMADA">confirmada</form:option>			
			</form:select>
			
			Número de huéspedes:
			
			<form:input cssClass="formulario-reservas" path="numeroHuespedes"/>
			
			Observaciones:
			
			<form:input cssClass="formulario-reservas" path="observaciones"/>

			<div class="error">
				<!-- recupera de la request el mensaje de error que hemos almacenado (mediante el objeto 'Model' creado en el LoginController). Son errores tanto de credenciales vacias como erroneas segun lo buscado por el DAO en la tabla usuarios de la BBDD hoteldb -->
				<c:out value="${error}"></c:out>
			</div>
	
			<button type="submit" class="boton-almacenarHuesped">Actualizar</button>


</form:form>
</c:if>


<a href="${pageContext.request.contextPath}/reservas">Volver</a>





</body>
</html>