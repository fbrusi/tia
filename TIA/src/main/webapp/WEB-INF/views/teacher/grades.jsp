<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="cdc"%>

<cdc:teacher>

		<h1 class="w3-text-teal">Gestão de Notas</h1>
		
		<form:form action='${spring:mvcUrl("GC#loadView").build()}' method="post" commandName="schoolSubject">
			
			<h3>Adicionar notas às matérias:</h3>
			
			<table>
				<tr>
					<td><label for="subject">Matéria: </label></td>
					<td>
						<form:select path="id" onchange="this.form.submit()" id="selectedSubject">
							<option value="0">ESCOLHA UMA MATÉRIA</option>
							<form:options items="${subjects}" itemLabel="subjectForDisplay" itemValue="id"/>
						</form:select>
					</td>
				</tr>
			</table>
			
			<c:if test="${fn:length(schoolSubject.students) ne 0}">
				<h3>Boletim:</h3>
				<table>
					<tr class="tia-table-title" align="center">
						<td width="300px;">Aluno</td>
						<c:if test="${schoolSubject.gradeA != 0}"><td width="90px;">A</td></c:if>
						<c:if test="${schoolSubject.gradeB != 0}"><td width="90px;">B</td></c:if>
						<c:if test="${schoolSubject.gradeC != 0}"><td width="90px;">C</td></c:if>
						<c:if test="${schoolSubject.gradeD != 0}"><td width="90px;">D</td></c:if>
						<c:if test="${schoolSubject.gradePF != 0}"><td width="90px;">PF</td></c:if>
						<td width="90px;">MÉDIA</td>
					</tr>
					<c:forEach items="${schoolSubject.students}" var="student" varStatus="status">
						<c:if test="${status.index % 2 != 0}"><tr class="tia-table-even"></c:if>
						<c:if test="${status.index % 2 == 0}"><tr></c:if>
							<td>${student.name}</td>
							<c:if test="${schoolSubject.gradeA != 0}"><td align="center"><input type="text" size="1" maxlength="2"></td></c:if>
							<c:if test="${schoolSubject.gradeB != 0}"><td align="center"><input type="text" size="1" maxlength="2"></td></c:if>
							<c:if test="${schoolSubject.gradeC != 0}"><td align="center"><input type="text" size="1" maxlength="2"></td></c:if>
							<c:if test="${schoolSubject.gradeD != 0}"><td align="center"><input type="text" size="1" maxlength="2"></td></c:if>
							<c:if test="${schoolSubject.gradePF != 0}"><td align="center"><input type="text" size="1" maxlength="2"></td></c:if>
							<c:if test="${schoolSubject.gradePF != 0}"><td align="center">-</td></c:if>
						</tr>
					</c:forEach>
				</table>	
			</c:if>
		</form:form>

</cdc:teacher>
