<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>TIA</title>
</head>
<body>
	${alertMessage}
	<form action="#">
		<div>
			<label for="login">Login: </label>
			<input type="text" name="login" id="login">
		</div>
		<div>
			<label for="password">Senha: </label>
			<input type="password" name="password" id="password">
		</div>
		<div>
			<input type="submit" value="Acessar">
		</div>
	</form>
	<div>
		<a href="#">Esqueceu sua senha?</a>
	</div>
	<div>
		<a href="firstAccess">Primeiro acesso?</a>
	</div>
	</body>
</html>