<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="cdc"%>

<cdc:nonLogged>
	<form:form action='${spring:mvcUrl("SUC#requestFirstAccess").build()}' method="post" commandName="user">
		<h1 class="w3-text-teal">Primeiro acesso:</h1>
		<table>
			<tr>
				<td><label for="name">Nome: </label></td>
				<td><form:input type="text" path="name" id="name" maxlength="50" /></td>
				<td><form:errors path="name" cssClass="tia-alert-message" /></td>
			</tr>
			<tr>
				<td><label for="username">E-mail: </label></td>
				<td><form:input type="text" path="username" id="username" maxlength="50" /></td>
				<td><form:errors path="username" cssClass="tia-alert-message" /></td>
			</tr>
			<tr>
				<td><label for="password">Senha: </label></td>
				<td><form:input type="password" path="password" id="password" maxlength="12" /></td>
				<td><form:errors path="password" cssClass="tia-alert-message" /></td>
			</tr>
			<tr>
				<td><label for="passwordConfirm">Confirme a senha: </label></td>
				<td><form:input type="password" path="passwordConfirm" id="passwordConfirm" maxlength="12" /></td>
				<td><form:errors path="passwordConfirm" cssClass="tia-alert-message" /></td>
			</tr>
			<tr>
				<td><label for="role.name">Perfil: </label></td>
				<td>
					<select name="role.name">
						<c:forEach items="${profiles}" var="profile">
							<option value="${profile}">${profile.profile}</option>
						</c:forEach>
					</select>
				</td>
				<td><form:errors path="role.name" /></td>
			</tr>
		</table>
		<br/>
		<table>
			<tr>
				<td colspan="2"><input type="submit" value="Solicitar cadastro"></td>
				<td/>
			</tr>
			<tr>
				<td colspan="2"><a href="login">Retornar</a></td>
				<td/>
			</tr>
		</table>
	</form:form>
</cdc:nonLogged>	
