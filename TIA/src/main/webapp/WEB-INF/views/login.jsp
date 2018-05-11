<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="cdc"%>

<cdc:nonLogged>
	<form:form servletRelativeAction="/nonlogged/login">
		<c:if test="${alertMessage != null }">
			<div class="tia-g-alert-message"><b>${alertMessage}</b></div>
		</c:if>	

		<h1 class="w3-text-teal">Acessar sistema TIA</h1>
		<table>
			<tr>
				<td><label for="username">Login: </label></td>
				<td><input type="text" name="username" id="username" ></td>
				<td><form:errors path="username" />  </td>
			</tr>
			<tr>
				<td><label for="password">Senha: </label></td>
				<td><input type="password" name="password" id="password" maxlength="12"></td>
				<td><form:errors path="password" />  </td>
			</tr>
			<tr>
				<td colspan="2" align="right"><input type="submit" value="Acessar"></td>
				<td></td>
			</tr>
		</table>
		<br/>
		<table>							
			<tr>
				<td colspan="2"><a href="passwordReset">Esqueceu sua senha?</a></td>
			</tr>
			<tr>
				<td colspan="2"><a href="firstAccess">Primeiro acesso?</a></td>
			</tr>
		</table>				
	</form:form>
</cdc:nonLogged>
