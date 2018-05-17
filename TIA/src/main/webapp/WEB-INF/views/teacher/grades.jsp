<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="cdc"%>

<cdc:teacher>

	<h1 class="w3-text-teal">Gestão de Notas</h1>
	
	<form:form action='${spring:mvcUrl("GC#loadGrades").build()}' method="post" commandName="schoolSubject">
		
		<h3>Adicionar notas às matérias:</h3>
		
		<table>
			<tr>
				<td><label for="subject">Matéria: </label></td>
				<td>
					<form:select path="id" onchange="this.form.submit()" id="selectedSubject">
						<form:option value="0">ESCOLHA UMA MATÉRIA</form:option>
						<form:options items="${subjects}" itemLabel="subjectForDisplay" itemValue="id"/>
					</form:select>
				</td>
			</tr>
			
		</table>
		<c:if test="${fn:length(gradeForm.grades) eq 0 and ((schoolSubject.id ne 0) and (not empty schoolSubject.id))}">
			<h3>Não há alunos cadastrados nesta matéria.</h3>
		</c:if>
	</form:form>
	
	<c:if test="${fn:length(gradeForm.grades) ne 0}">
		<form:form action='${spring:mvcUrl("GC#updateGrades").build()}' method="post" commandName="gradeForm">
			<h3>Boletim:</h3>
			<input type="hidden" name="schoolSubject.id" value="${schoolSubject.id}">
			<table>
				<tr class="tia-table-title" align="center">
					<td width="300px;">Aluno</td>
					<c:if test="${schoolSubject.gradeA != 0}"><td width="90px;">A (${schoolSubject.gradeA}%)</td></c:if>
					<c:if test="${schoolSubject.gradeB != 0}"><td width="90px;">B (${schoolSubject.gradeB}%)</td></c:if>
					<c:if test="${schoolSubject.gradeC != 0}"><td width="90px;">C (${schoolSubject.gradeC}%)</td></c:if>
					<c:if test="${schoolSubject.gradeD != 0}"><td width="90px;">D (${schoolSubject.gradeD}%)</td></c:if>
					<c:if test="${schoolSubject.gradePF != 0}"><td width="90px;">PF (${schoolSubject.gradePF}%)</td></c:if>
					<td width="90px;">MÉDIA</td>
				</tr>
				<c:forEach items="${gradeForm.grades}" var="grade" varStatus="status">
					<c:if test="${status.index % 2 != 0}"><tr class="tia-table-even"></c:if>
					<c:if test="${status.index % 2 == 0}"><tr></c:if>
						<td>${grade.student.name}</td>
						<c:if test="${schoolSubject.gradeA != 0}"><td align="center"><input type="text" name="grades[${status.index}].gradeA" value="${grade.gradeAForDisplay}" size="1" maxlength="3" <c:if test="${grade.hasErrorGradeA}">style="background-color: #FF9999;"</c:if>/></td></c:if>
						<c:if test="${schoolSubject.gradeB != 0}"><td align="center"><input type="text" name="grades[${status.index}].gradeB" value="${grade.gradeBForDisplay}" size="1" maxlength="3" <c:if test="${grade.hasErrorGradeB}">style="background-color: #FF9999;"</c:if>/></td></c:if>
						<c:if test="${schoolSubject.gradeC != 0}"><td align="center"><input type="text" name="grades[${status.index}].gradeC" value="${grade.gradeCForDisplay}" size="1" maxlength="3" <c:if test="${grade.hasErrorGradeC}">style="background-color: #FF9999;"</c:if>/></td></c:if>
						<c:if test="${schoolSubject.gradeD != 0}"><td align="center"><input type="text" name="grades[${status.index}].gradeD" value="${grade.gradeDForDisplay}" size="1" maxlength="3" <c:if test="${grade.hasErrorGradeD}">style="background-color: #FF9999;"</c:if>/></td></c:if>
						<c:if test="${schoolSubject.gradePF != 0}"><td align="center"><input type="text" name="grades[${status.index}].gradePF" value="${grade.gradePFForDisplay}" size="1" maxlength="3" <c:if test="${grade.hasErrorGradePF}">style="background-color: #FF9999;"</c:if>/></td></c:if>
						<td align="center">
							<input type="hidden" name="grades[${status.index}].student.id" value="${grade.student.id}">
							<input type="hidden" name="grades[${status.index}].student.name" value="${grade.student.name}">
							${grade.averageForDisplay}
						</td>
					</tr>
				</c:forEach>
			</table>
			<br/>
			<input type="submit" value="Atualizar">
			<br/><br/>
			<c:if test="${not empty errorMessage}">
				<label class="tia-alert-message">${errorMessage}</label>
			</c:if>
		</form:form>
	</c:if>
	
</cdc:teacher>
