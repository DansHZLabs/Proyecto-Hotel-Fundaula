<%@ page contentType="text/html;charset=UTF-8" language="java" %> <!-- se pueden poner br muchas encadenadas y así se deja más hueco -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!-- librería jstl -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><!-- librería jstl para formatear fechas -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %><!-- librería form tags -->



<html>
<head>
    <title>Formulario Incidencia</title><!-- título de la pestaña del navegador -->
    <style type="text/css">

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
	margin-top: 5px;       
	margin-bottom: 18px;   
	padding: 8px;          
	box-sizing: border-box;
}

.enlace-volver {

	display: block;
	text-align: center;
	margin-top: 20px;

}

.boton-formulario {
    display: block;
    margin: 55px auto;
    width: 50%;
    cursor: pointer;
    padding: 7px;
}

</style>
</head>
<body class="fondo">
<h2 class="titulo-principal">Formulario Incidencia</h2>

<h1><!-- para cambiar entre guardar y editar --><!-- aquí no se llega según el rol porque ya se ocultó la opción en la lista-->
    <c:choose>
        <c:when test="${incidencia.idIncidencia != 0}">Editar Incidencia</c:when>
        <c:otherwise>Nueva Incidencia</c:otherwise>
    </c:choose>
</h1>

<div class="contenedor-formulario">
<form:form action="${pageContext.request.contextPath}/incidencias/guardar" method="post" modelAttribute="incidencia"><!-- formulario para actualizar manda los datos al controller -->

    <form:hidden path="idIncidencia"/><!-- guarda el id para actualizar pero no lo enseña y así sabe cuál es -->

    <label>Número de la habitación:</label><!-- aquí hay que poner lo que es el hueco de cada campo que hay que rellenar -->
		<select name="habitacion.idHabitacion" class="input-formulario">
   			 <c:forEach var="habitacion" items="${habitaciones}">
       			 <option value="${habitacion.idHabitacion}"<c:if test="${incidencia.habitacion != null&& habitacion.idHabitacion == incidencia.habitacion.idHabitacion}">selected</c:if>>${habitacion.numeroHabitacion}</option>
    		 </c:forEach>
		</select>
   

    <label>Estado de la incidencia:</label>
    <form:select class="input-formulario" path="estadoIncidencia">
    <form:option value="ABIERTA">abierta</form:option>
    <form:option value="EN_CURSO">en curso</form:option>
    <form:option value="CERRADA">cerrada</form:option>
</form:select>
    

    <label>Prioridad de la incidencia:</label>
    <form:select class="input-formulario" path="prioridadIncidencia">
    <form:option value="BAJA">baja</form:option>
    <form:option value="MEDIA">media</form:option>
    <form:option value="ALTA">alta</form:option>
</form:select>
    

    <label>Descripción de la incidencia:</label>
   <form:input class="input-formulario" path="descripcionIncidencia"/>
    

    <label>Fecha apertura:</label>
	<form:input path="fechaApertura" class="input-formulario" type="date"/>
	

    <label>Fecha cierre:</label>
	<form:input path="fechaCierre" class="input-formulario" type="date"/>
	<br>

    <button type="submit" class="boton-formulario"><!-- para que salga un botón o el otro -->
	    <c:choose>
       		 <c:when test="${incidencia.idIncidencia != 0}">Actualizar</c:when>
	        <c:otherwise>Guardar</c:otherwise>
	    </c:choose>
	</button>
</form:form>

<p style="color:red">${error}</p><!-- el error si no se completan los campos -->

<a href="${pageContext.request.contextPath}/incidencias" class="enlace-volver">Volver</a><!-- botón volver para cambiar de vista al listado-->

</div>

</body>
</html>

<!-- se usan solo algunas form tags porque se rompe el choose y pone editar y guardar y no funciona -->