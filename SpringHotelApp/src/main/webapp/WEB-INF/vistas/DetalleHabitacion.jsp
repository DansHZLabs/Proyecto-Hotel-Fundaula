<%@ page contentType="text/html;charset=UTF-8" language="java" %> <!-- esta vista es para probar cambiar luego -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %><!-- librería form tags -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!-- jstl -->

<html>
<head>
    <title>Detalle Habitación</title><!-- título de la pestaña del navegador -->
    
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

.campo {
    margin-bottom: 20px;
}

.input-formulario {
    width: 100%;
    padding: 10px;
    box-sizing: border-box;
}


</style>

</head>
<body class="fondo">

<h2 class="titulo-principal">Detalle Habitacion</h2>

<div class="contenedor-formulario">
<form:form modelAttribute="habitacion">
    <p>
        <strong>ID de la habitación:</strong>
        <form:input cssClass="input-formulario" path="idHabitacion" disabled="true"/>
    </p>
    <p>
        <strong>Nº de la habitación:</strong>
        <form:input cssClass="input-formulario" path="numeroHabitacion" disabled="true"/>
    </p>
    <p>
        <strong>Tipo de habitación:</strong>
        <form:input cssClass="input-formulario" path="tipo" disabled="true"/>
    </p>
    <p>
        <strong>Precio de la habitación:</strong>
        <form:input cssClass="input-formulario" path="precioPorNoche" disabled="true"/>
    </p>
    <p>
        <strong>Orientación:</strong>
        <form:input cssClass="input-formulario" path="orientacionHabitacion" disabled="true"/>
    </p>
</form:form>
</div>

<a href="${pageContext.request.contextPath}/habitaciones">Volver</a><!-- botón volver para cambiar de vista al listado-->

</body>
</html>

