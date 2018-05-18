<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="cdc"%>

<cdc:teacher>
	<h1 class="w3-text-teal">Gest�o de Mat�rias</h1>
	
	<form:form  action='${spring:mvcUrl("SSC#registerNewSubject").build()}' method="post" commandName="schoolSubject">
		<h3>Adicionar mat�rias:</h3>
		<table>
			<tr>
				<td><label for="subject">Mat�ria: </label></td>
				<td><form:input type="text" path="subject" id="subject" maxlength="50" /></td>
				<td><form:errors path="subject" cssClass="tia-alert-message" /></td>
			</tr>
			<tr>
				<td><label for="semester">Semestre: </label></td>
				<td>
					<form:select path="semester">
						<form:option value="1">1�</form:option>
						<form:option value="2">2�</form:option>
						<form:option value="3">3�</form:option>
						<form:option value="4">4�</form:option>
						<form:option value="5">5�</form:option>
						<form:option value="6">6�</form:option>
						<form:option value="7">7�</form:option>
						<form:option value="8">8�</form:option>
					</form:select>
				</td>
			</tr>
			<tr>
				<td><label for="gradeA">Peso nota A: </label></td>
				<td><form:input type="number" path="gradeA" id="gradeA" /> %</td>
				<td><form:errors path="gradeA" cssClass="tia-alert-message" /></td>
			</tr>
			<tr>
				<td><label for="gradeB">Peso nota B: </label></td>
				<td><form:input type="number" path="gradeB" id="gradeB" /> %</td>
				<td><form:errors path="gradeB" cssClass="tia-alert-message" /></td>
			</tr>
			<tr>
				<td><label for="gradeC">Peso nota C: </label></td>
				<td><form:input type="number" path="gradeC" id="gradeC" /> %</td>
				<td><form:errors path="gradeC" cssClass="tia-alert-message" /></td>
			</tr>
			<tr>
				<td><label for="gradeD">Peso nota D: </label></td>
				<td><form:input type="number" path="gradeD" id="gradeD" /> %</td>
				<td><form:errors path="gradeD" cssClass="tia-alert-message" /></td>
			</tr>
			<tr>
				<td><label for="gradePF">Peso nota PF: </label></td>
				<td><form:input type="number" path="gradePF" id="gradePF" /> %</td>
				<td><form:errors path="gradePF" cssClass="tia-alert-message" /></td>
			</tr>
			<tr>
				<td colspan="2" align="right"><input type="submit" value="Cadastrar"></td>
				<td></td>
			</tr>
		</table>
	</form:form>
	
	<h3>Gerenciar mat�rias:</h3>
	<table>
		<tr class="tia-table-title" align="center">
			<td width="300px;">Mat�ria</td>
			<td width="90px;">Semestre</td>
			<td width="90px;">Peso A (%)</td>
			<td width="90px;">Peso B (%)</td>
			<td width="90px;">Peso C (%)</td>
			<td width="90px;">Peso D (%)</td>
			<td width="90px;">Peso PF (%)</td>
			<td width="90px;">Remover</td>
		</tr>
		<c:forEach items="${subjects}" var="subject" varStatus="status">
			<c:if test="${status.index % 2 != 0}"><tr class="tia-table-even"></c:if>
			<c:if test="${status.index % 2 == 0}"><tr></c:if>
				<td>${subject.subject}</td>
				<td align="center">${subject.semester}</td>
				<td align="center">${subject.gradeA}</td>
				<td align="center">${subject.gradeB}</td>
				<td align="center">${subject.gradeC}</td>
				<td align="center">${subject.gradeD}</td>
				<td align="center">${subject.gradePF}</td>
				<td align="center">
					<a href="removeSubject?id=${subject.id}" onclick="return confirm('Confirma a remo��o da mat�ria?')">
						<img alt="Remover" width="10px" src="/tia/resources/remove.png">
					</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<br/>
	<c:if test="${alertMessage != null }">
		<div class="tia-g-alert-message"><b>${alertMessage}</b></div>
	</c:if>
</cdc:teacher>
