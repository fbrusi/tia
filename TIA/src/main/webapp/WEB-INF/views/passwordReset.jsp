<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="cdc"%>

<cdc:nonLogged>
	<form:form action='${spring:mvcUrl("PRC#requestResetKey").build()}' method="post" commandName="user">
		<h1 class="w3-text-teal">Reset de senha:</h1>
		<table>
			<tr>
				<td><label for="username">Informe o e-mail: </label></td>
				<td><form:input type="text" path="username" id="username" /></td>
				<td><form:errors path="username" cssClass="tia-alert-message" /></td>
			</tr>
		</table>
		<br/>
		<table>
			<tr>
				<td colspan="2"><input type="submit" value="Solicitar link de desbloqueio"></td>
				<td/>
			</tr>
			<tr>
				<td colspan="2"><a href="login">Retornar</a></td>
				<td/>
			</tr>
		</table>
	</form:form>
</cdc:nonLogged>