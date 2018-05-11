<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="cdc"%>

<cdc:teacher>
	<h1 class="w3-text-teal">Gestão de Matérias</h1>
	
	<form:form  action='${spring:mvcUrl("SSC#registerNewSubject").build()}' method="post" commandName="schoolSubject">
		<h3>Adicionar matérias:</h3>
		<table>
			<tr>
				<td><label for="subject">Matéria: </label></td>
				<td><form:input type="text" path="subject" id="subject" /></td>
				<td><form:errors path="subject" cssClass="tia-alert-message" /></td>
			</tr>
			<tr>
				<td><label for="semester">Semestre: </label></td>
				<td>
					<form:select path="semester">
						<option>1</option>
						<option>2</option>
						<option>3</option>
						<option>4</option>
						<option>5</option>
						<option>6</option>
						<option>7</option>
						<option>8</option>
					</form:select>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="right"><input type="submit" value="Cadastrar"></td>
				<td></td>
			</tr>
		</table>
	</form:form>
	<c:if test="${alertMessage != null }">
		<div class="tia-g-alert-message"><b>${alertMessage}</b></div>
	</c:if>
	<br/>
	<h3>Gerenciar matérias:</h3>
	<table>
		<tr class="tia-table-title" align="center">
			<td width="400px;">Matéria</td>
			<td width="90px;">Semestre</td>
			<td width="90px;">Remover</td>
		</tr>
		<c:forEach items="${subjects}" var="subject" varStatus="status">
			<c:if test="${status.index % 2 != 0}"><tr class="tia-table-even"></c:if>
			<c:if test="${status.index % 2 == 0}"><tr></c:if>
				<td>${subject.subject}</td>
				<td align="center">${subject.semester}</td>
				<td align="center"><a href="removeSubject?id=${subject.id}" onclick="return confirm('Confirma a remoção da matéria?')"><img alt="Remover" width="10px" src="/tia/resources/remove.png"> </a></td>
			</tr>
		</c:forEach>
	</table>
</cdc:teacher>
