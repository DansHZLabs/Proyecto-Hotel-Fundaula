<%@ page contentType="text/html;charset=UTF-8" language="java" %> <!-- se pueden poner br muchas encadenadas y así se deja más hueco -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!-- librería jstl -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><!-- librería jstl para formatear fechas -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %><!-- librería form tags -->

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

<form:form action="${pageContext.request.contextPath}/incidencias/guardar" method="post" modelAttribute="incidencia"><!-- formulario para actualizar manda los datos al controller -->

    <form:hidden path="idIncidencia"/><!-- guarda el id para actualizar pero no lo enseña y así sabe cuál es -->

    <label>Número de la habitación:</label><!-- aquí hay que poner lo que es el hueco de cada campo que hay que rellenar -->
		<select name="habitacion.idHabitacion">
   			 <c:forEach var="habitacion" items="${habitaciones}">
       			 <option value="${habitacion.idHabitacion}"<c:if test="${incidencia.habitacion != null&& habitacion.idHabitacion == incidencia.habitacion.idHabitacion}">selected</c:if>>${habitacion.numeroHabitacion}</option>
    		 </c:forEach>
		</select>
    <br>

    <label>Estado de la incidencia:</label>
    <form:select path="estadoIncidencia">

    <form:option value="abierta">abierta</form:option>

    <form:option value="en_curso">en curso</form:option>

    <form:option value="cerrada">cerrada</form:option>

</form:select>
    <br>

    <label>Prioridad de la incidencia:</label>
    <form:select path="prioridadIncidencia">

    <form:option value="baja">baja</form:option>

    <form:option value="media">media</form:option>

    <form:option value="alta">alta</form:option>

</form:select>
    <br>

    <label>Descripción de la incidencia:</label>
   <form:input path="descripcionIncidencia"/>
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
</form:form>
<br>

<a href="${pageContext.request.contextPath}/incidencias">Volver</a><!-- botón volver para cambiar de vista al listado-->

</body>
</html>

<!-- se usan solo algunas form tags porque se rompe el choose y pone editar y guardar y no funciona -->