<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="cdc"%>

<cdc:teacher>
	<h1 class="w3-text-teal">Gestão de Alunos</h1>
	
	<form:form action='${spring:mvcUrl("SC#loadView").build()}' method="post" commandName="schoolSubject">
		<h3>Adicionar alunos às matérias:</h3>
		<table>
			<tr>
				<td><label for="subject">Matéria: </label></td>
				<td>
					<form:select path="id" onchange="this.form.submit()">
						<option value="0">ESCOLHA UMA MATÉRIA</option>
						<form:options items="${subjects}" itemLabel="subjectForDisplay" itemValue="id"/>
					</form:select>
				</td>
			</tr>
			<c:if test="${students != null}">
				<tr>
					<td><label for="student">Aluno: </label></td>
					<td>
						<form:select path="studentId">
							<form:options items="${students}" itemValue="id" itemLabel="name"/>
						</form:select>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="right"><input type="submit" value="Adicionar"></td>
				</tr>
			</c:if>
		</table>
		<c:if test="${alertMessage != null }">
			<div class="tia-g-alert-message"><b>${alertMessage}</b></div>
		</c:if>
		<br/>
		<c:choose>
			<c:when test="${fn:length(registeredStudents) eq 0}">
				<h3>Não há alunos cadastrados nesta matéria.</h3>
			</c:when>
			<c:otherwise>
				<h3>Alunos cadastrados:</h3>
				<table>
					<tr class="tia-table-title" align="center">
						<td width="300px;">Aluno</td>
						<td width="90px;">Remover</td>
					</tr>
					<c:forEach items="${registeredStudents}" var="registeredStudent" varStatus="status">
						<c:if test="${status.index % 2 != 0}"><tr class="tia-table-even"></c:if>
						<c:if test="${status.index % 2 == 0}"><tr></c:if>
							<td>${registeredStudent.name}</td>
							<td align="center"><a href="removeStudent?id=${registeredStudent.id}" onclick="return confirm('Confirma a remoção do aluno desta matéria?')"><img alt="Remover" width="10px" src="/tia/resources/remove.png"> </a></td>
						</tr>
					</c:forEach>
				</table>	
			</c:otherwise>
		</c:choose>
	</form:form>
	<form:form >
	
	</form:form>
</cdc:teacher>
