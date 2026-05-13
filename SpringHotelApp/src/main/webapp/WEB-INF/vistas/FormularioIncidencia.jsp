<%@ page contentType="text/html;charset=UTF-8" language="java" %> <!-- se pueden poner br muchas encadenadas y así se deja más hueco -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!-- librería jstl -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><!-- librería jstl para formatear fechas -->

<html>
<head>
    <title>Formulario Incidencia</title><!-- título de la pestaña del navegador -->
</head>
<body>

<h1><!-- para cambiar entre guardar y editar -->
    <c:choose>
        <c:when test="${incidencia.idIncidencia != 0}">Editar Incidencia</c:when>
        <c:otherwise>Nueva Incidencia</c:otherwise>
    </c:choose>
</h1>

<form action="${pageContext.request.contextPath}/incidencias/guardar" method="post"><!-- formulario para actualizar manda los datos al controller -->

    <input type="hidden" name="idIncidencia" value="${incidencia.idIncidencia}"><!-- guarda el id para actualizar pero no lo enseña y así sabe cuál es -->

    <label>Número de la habitación:</label><!-- aquí hay que poner lo que es el hueco de cada campo que hay que rellenar -->
		<select name="habitacion.idHabitacion">
   			 <c:forEach var="habitacion" items="${habitaciones}">
       			 <option value="${habitacion.idHabitacion}"<c:if test="${incidencia.habitacion != null&& habitacion.idHabitacion == incidencia.habitacion.idHabitacion}">selected</c:if>>${habitacion.numeroHabitacion}</option>
    		 </c:forEach>
		</select>
    <br>

    <label>Estado de la incidencia:</label>
    <select name="estadoIncidencia">
   		 <option value="abierta" <c:if test="${incidencia.estadoIncidencia=='abierta'}">selected</c:if>>abierta</option>
   		 <option value="en_curso" <c:if test="${incidencia.estadoIncidencia=='en_curso'}">selected</c:if>>en curso</option>
    		<option value="cerrada" <c:if test="${incidencia.estadoIncidencia=='cerrada'}">selected</c:if>>cerrada</option>
    </select>
    <br>

    <label>Prioridad de la incidencia:</label>
    <select name="prioridadIncidencia">
    	<option value="baja" <c:if test="${incidencia.prioridadIncidencia=='baja'}">selected</c:if>>baja</option>
   		 <option value="media" <c:if test="${incidencia.prioridadIncidencia=='media'}">selected</c:if>>media</option>
   		 <option value="alta" <c:if test="${incidencia.prioridadIncidencia=='alta'}">selected</c:if>>alta</option>
	</select>
    <br>

    <label>Descripción de la incidencia:</label>
    <input type="text" name="descripcionIncidencia"
    value="${incidencia.descripcionIncidencia}">
    <br>

    <fmt:formatDate value="${incidencia.fechaApertura}" pattern="yyyy-MM-dd" var="fechaAperturaFormateada"/>
    <label>Fecha apertura:</label>
    <input type="date" name="fechaApertura" value="${fechaAperturaFormateada}"><!-- hay que coger la fecha porque sino puede dar null -->
    <br>

    <fmt:formatDate value="${incidencia.fechaCierre}" pattern="yyyy-MM-dd" var="fechaCierreFormateada"/>
    <label>Fecha cierre:</label>
    <input type="date" name="fechaCierre" value="${fechaCierreFormateada}"><!-- hay que coger la fecha porque sino puede dar null -->
    <br>

    <button type="submit"><!-- para que salga un botón o el otro -->
	    <c:choose>
       		 <c:when test="${incidencia.idIncidencia != 0}">Actualizar</c:when>
	        <c:otherwise>Guardar</c:otherwise>
	    </c:choose>
	</button>
</form>
<br>

<a href="${pageContext.request.contextPath}/incidencias">Volver</a><!-- botón volver para cambiar de vista al listado-->

</body>
</html>