<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="./resources/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<!-- CSS -->
<link rel="stylesheet" href="./resources/newportal.css">

<title>Inicio de sesión</title>
</head>

<body class="fondo">

	<div class="container">
		<div class="signup-form-container">

			<div class="form-header">
				<h3 class="registration">
					<i class="fa fa-user"></i>HOTEL FUNDAULA
				</h3>
				<h2>&nbsp;</h2>
			</div>

			<div class="form-row">
				<div class="col-3"></div>
				<div class="col-6">
					<div class="form-body">
						<div class="form-group">
							<div>
								<form role="form" id="login-usuarios" autocomplete="off"
									class="credentials" method="post" action="login">
									<input type="hidden" name="accion" value="login">
									Acceso de usuarios
									<div class="input-group">
 										 <input name="usuario" type="text" class="form-control fontAwesome"
										placeholder="&#xf007; Usuario">
									</div>
									<p></p>
									<div class="input-group">
 										<input name="password" type="password" class="form-control fontAwesome"
										placeholder="&#xf007; Password">
										<span class="input-group-btn">
   											 <button type="submit" class="btn btn-info">Login...</button>
										</span>
									</div>
								</form>
							</div>

						</div>
					</div>
				</div>

			</div>


			<div class="form-row">
				<div class="col error">
					<c:out value="${requestScope.error}"></c:out>
				</div>
			</div>

		</div>
	</div>

</body>
</html>
