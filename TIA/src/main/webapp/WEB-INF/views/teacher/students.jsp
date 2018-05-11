<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="cdc"%>

<cdc:teacher>
	<h1 class="w3-text-teal">Gestão de Alunos</h1>
	
	<form:form  action='${spring:mvcUrl("SC#loadView").build()}' method="post" commandName="schoolSubject">
		<h3>Adicionar alunos às matérias:</h3>
		<table>
			<tr>
				<td><label for="subject">Matéria: </label></td>
				<td>
					<form:select path="id" onchange="this.form.submit()">
						<option value="0">ESCOLHA UMA MATÉRIA</option>
						<c:forEach items="${subjects}" var="subject">
							<option value="${subject.id}">(${subject.semester}º semestre) - ${subject.subject}</option>
						</c:forEach>
					</form:select>
				</td>
			</tr>
			<tr>
				<td><label for="student">Aluno: </label></td>
				<td>
					<form:select path="studentId">
						<c:forEach items="${students}" var="student">
							<option value="${student.id}">${student.name}</option>
						</c:forEach>
					</form:select>
				</td>
			</tr>
			<%--
			<tr>
				<td colspan="2" align="right"><input type="submit" value="Adicionar"></td>
			</tr>
			 --%>
		</table>
	</form:form>
	<c:if test="${alertMessage != null }">
		<div class="tia-g-alert-message"><b>${alertMessage}</b></div>
	</c:if>
	
</cdc:teacher>
