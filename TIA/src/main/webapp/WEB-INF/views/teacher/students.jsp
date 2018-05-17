<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="cdc"%>

<cdc:teacher>

	<jsp:attribute name="extraScripts">
		<script>
			function addStudent() {
				
				document.getElementById('inputSelectedSubject').value = document.getElementById('selectedSubject').value;
				document.getElementById('inputSelectedStudent').value = document.getElementById('selectedStudent').value;
				
				document.getElementById('addStudentForm').submit();
			}
			
			function removeStudent(selectedStudent) {
				
				if(confirm('Confirma a remoção do estudante?')) {
					
					document.getElementById('selectedSubjectToRemoveStudent').value = document.getElementById('selectedSubject').value;
					document.getElementById('selectedStudentToRemove').value = selectedStudent;
					
					document.getElementById('removeStudentForm').submit();
				}
			}
		</script>
	</jsp:attribute>

	<jsp:body>
		<h1 class="w3-text-teal">Gestão de Alunos</h1>
		
		<form:form action='${spring:mvcUrl("SC#loadView").build()}' method="post" commandName="schoolSubject">
			<h3>Adicionar alunos às matérias:</h3>
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
				<c:if test="${students != null}">
					<tr>
						<td><label for="student">Aluno: </label></td>
						<td>
							<form:select path="studentId" id="selectedStudent">
								<form:options items="${students}" itemValue="id" itemLabel="name"/>
							</form:select>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="right">
							<c:choose>
								<c:when test="${fn:length(students) eq 0}">
									<input type="button" value="Adicionar" disabled="disabled"">
								</c:when>
								<c:otherwise>
									<input type="button" value="Adicionar" onclick="addStudent()">
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
				</c:if>
			</table>
			
			<c:if test="${fn:length(registeredStudents) eq 0 and ((schoolSubject.id ne 0) and (not empty schoolSubject.id))}">
				<h3>Não há alunos cadastrados nesta matéria.</h3>
			</c:if>

			<c:if test="${fn:length(registeredStudents) ne 0}">
				<h3>Alunos cadastrados:</h3>
				<table>
					<tr class="tia-table-title" align="center">
						<td width="300px;">Aluno</td>
						<td width="300px;">E-mail</td>
						<td width="90px;">Remover</td>
					</tr>
					<c:forEach items="${registeredStudents}" var="registeredStudent" varStatus="status">
						<c:if test="${status.index % 2 != 0}"><tr class="tia-table-even"></c:if>
						<c:if test="${status.index % 2 == 0}"><tr></c:if>
							<td>${registeredStudent.name}</td>
							<td>${registeredStudent.username}</td>
							<td align="center"><a href="javascript:removeStudent('${registeredStudent.id}')"><img alt="Remover" width="10px" src="/tia/resources/remove.png"></a></td>
						</tr>
					</c:forEach>
				</table>	
			</c:if>
		</form:form>
		
		<form:form action='${spring:mvcUrl("SC#addStudent").build()}' method="post" commandName="schoolSubject" id="addStudentForm">
			<form:hidden path="id" id="inputSelectedSubject"/>
			<form:hidden path="studentId" id="inputSelectedStudent"/>
		</form:form>
		
		<form:form action='${spring:mvcUrl("SC#removeStudent").build()}' method="post" commandName="schoolSubject" id="removeStudentForm">
			<form:hidden path="id" id="selectedSubjectToRemoveStudent"/>
			<form:hidden path="studentId" id="selectedStudentToRemove"/>
		</form:form>
	</jsp:body>
</cdc:teacher>
