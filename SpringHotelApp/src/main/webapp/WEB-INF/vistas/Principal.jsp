<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="./resources/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<!-- CSS -->
<link rel="stylesheet"
	href="./resources/newportal.css">

<title>Datos del usuario</title>
</head>

<body class="fondo">

	<div class="container">
		<div class="signup-form-container">

			<div class="form-header">
				<h3 class="registration">
					<i class="fa fa-user"></i>HOTEL FUNDAULA
				</h3>
				<br>
				<br>
			</div>
			<p></p>
			<br>
			<br>
			<div class="form-row">
				<div class="col-10">
					<div class="form-body">
						<div class="form-group">
							<div>
							<h2>Bienvenido, ${usuarioLogueado.username}</h2>
								<form role="form" id="datos-usuario" autocomplete="off"
									class="credentials">
									<!-- se obtienen los datos obtenidos del formulario gracias al sesion almacenado en la Request  -->
									<div class="input-group">Tiene usted permisos de: ${usuarioLogueado.rol}</div>
									
								</form>
							</div>

						</div>
					</div>
				</div>

				<div class="col-1"></div>
			</div>

		</div>
	</div>

</body>
</html>
