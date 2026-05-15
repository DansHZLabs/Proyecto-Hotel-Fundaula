<%@ page contentType="text/html;charset=UTF-8" language="java" %> <!-- esta vista es para probar cambiar luego -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %><!-- librería form tags -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!-- jstl -->

<html>
<head>
    <title>Detalle Habitación</title><!-- título de la pestaña del navegador -->
</head>
<body>

<form:form modelAttribute="habitacion">
    <p>
        <strong>ID de la habitación:</strong>
        <form:input path="idHabitacion" disabled="true"/>
    </p>
    <p>
        <strong>Nº de la habitación:</strong>
        <form:input path="numeroHabitacion" disabled="true"/>
    </p>
    <p>
        <strong>Tipo de habitación:</strong>
        <form:input path="tipo" disabled="true"/>
    </p>
    <p>
        <strong>Precio de la habitación:</strong>
        <form:input path="precioPorNoche" disabled="true"/>
    </p>
    <p>
        <strong>Orientación:</strong>
        <form:input path="orientacionHabitacion" disabled="true"/>
    </p>
</form:form>

<a href="${pageContext.request.contextPath}/habitaciones">Volver</a><!-- botón volver para cambiar de vista al listado-->

</body>
</html>