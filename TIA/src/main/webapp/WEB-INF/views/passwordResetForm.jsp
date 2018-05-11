<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="cdc"%>

<cdc:nonLogged>
	<form:form action='${spring:mvcUrl("PRC#resetPasswordForm").build()}' method="post" commandName="user">
		<input type="hidden" name="passwordResetKey" value="${passwordResetKey}">
		<h1 class="w3-text-teal">Reset de senha:</h1>
		<table>
			<tr>
				<td><label for="password">Informe a nova senha: </label></td>
				<td><form:input type="password" path="password" id="password" maxlength="12" /></td>
				<td><form:errors path="password" cssClass="tia-alert-message" /></td>
			</tr>
			<tr>
				<td><label for="passwordConfirm">Confirme a nova senha: </label></td>
				<td><form:input type="password" path="passwordConfirm" id="passwordConfirm" maxlength="12" /></td>
				<td><form:errors path="passwordConfirm" cssClass="tia-alert-message" /></td>
			</tr>
		</table>
		<br/>
		<table>
			<tr>
				<td colspan="2"><input type="submit" value="Resetar senha"></td>
				<td/>
			</tr>
			<tr>
				<td colspan="2"><a href="login">Retornar</a></td>
				<td/>
			</tr>
		</table>
	</form:form>
</cdc:nonLogged>
