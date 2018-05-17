<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="cdc"%>

<cdc:student>
	<h1 class="w3-text-teal">Boletim - ${semester}º Semestre</h1>

	<c:choose>
		<c:when test="${fn:length(grades) eq 0}">
			<h3>Não há matérias cadastradas para este semestre.</h3>
		</c:when>
		<c:otherwise>
			<table>
				<tr class="tia-table-title" align="center">
					<td width="300px;">Matéria</td>
					<td width="90px;">A</td>
					<td width="90px;">B</td>
					<td width="90px;">C</td>
					<td width="90px;">D</td>
					<td width="90px;">PF</td>
					<td width="90px;">MÉDIA</td>
				</tr>
				<c:forEach items="${grades}" var="grade" varStatus="status">
					<c:if test="${status.index % 2 != 0}"><tr class="tia-table-even"></c:if>
					<c:if test="${status.index % 2 == 0}"><tr></c:if>
						<td>${grade.schoolSubject.subject}</td>
						<td align="center">${grade.gradeAForDisplay}</td>
						<td align="center">${grade.gradeBForDisplay}</td>
						<td align="center">${grade.gradeCForDisplay}</td>
						<td align="center">${grade.gradeDForDisplay}</td>
						<td align="center">${grade.gradePFForDisplay}</td>
						<td align="center">${grade.averageForDisplay}</td>
					</tr>
				</c:forEach>
			</table>
			
			<br/>
			<h3>Fórmulas:</h3>
			
			<table>
				<tr class="tia-table-title" align="center">
					<td width="300px;">Matéria</td>
					<td width="300px;">Professor</td>
					<td width="90px;">Peso A (%)</td>
					<td width="90px;">Peso B (%)</td>
					<td width="90px;">Peso C (%)</td>
					<td width="90px;">Peso D (%)</td>
					<td width="90px;">Peso PF (%)</td>
				</tr>
				<c:forEach items="${grades}" var="grade" varStatus="status">
					<c:if test="${status.index % 2 != 0}"><tr class="tia-table-even"></c:if>
					<c:if test="${status.index % 2 == 0}"><tr></c:if>
						<td>${grade.schoolSubject.subject}</td>
						<td>${grade.schoolSubject.teacher.name}</td>
						<td align="center">${grade.schoolSubject.gradeA}</td>
						<td align="center">${grade.schoolSubject.gradeB}</td>
						<td align="center">${grade.schoolSubject.gradeC}</td>
						<td align="center">${grade.schoolSubject.gradeD}</td>
						<td align="center">${grade.schoolSubject.gradePF}</td>
					</tr>
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
</cdc:student>
