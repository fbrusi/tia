<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>TIA</title>
</head>
<body>
	<form:form action='${spring:mvcUrl("LC#requestFirstAccess").build()}' method="post" commandName="user">
		<div>
			<label for="name">Nome: </label>
			<form:input type="text" path="name" id="name" />
			<form:errors path="name" />
		</div>
		<div>
			<label for="email">E-mail: </label>
			<form:input type="text" path="email" id="email" />
			<form:errors path="email" />
		</div>
		<div>
			<label for="password">Senha: </label>
			<form:input type="password" path="password" id="password" />
			<form:errors path="password" />
		</div>
		<div>
			<label for="passwordConfirm">Confirme a senha: </label>
			<form:input type="password" path="passwordConfirm" id="passwordConfirm" />
			<form:errors path="passwordConfirm" />
		</div>
		<div>
			<label for="profile">Perfil: </label>
			<select name=profile>
				<c:forEach items="${profiles}" var="profile">
					<option value="${profile}">${profile.profile}</option>
				</c:forEach>
			</select>
			<form:errors path="profile" />
		</div>
		<div>
			<input type="submit" value="Solicitar cadastro">
		</div>
		<div>
			<a href="login">Retornar</a>
		</div>
	</form:form>
</body>
</html>
