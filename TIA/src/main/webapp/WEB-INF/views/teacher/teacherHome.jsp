<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="cdc"%>

<cdc:teacher>
	<h1 class="w3-text-teal">Portal do Professor</h1>
	<p>Utilize o menu lateral para gerenciar as atividades acad�micas.</p>
	
	<br/>
	<h3>Reinicializar semestre letivo</h3>
	<p>Para reiniciar o semestre letivo, removendo todas as mat�rias e notas, <a href="resetSemester" onclick="return confirm('Aten��o! Esta a��o exclui todas as mat�rias, boletins e alunos associados. Confirma a opera��o?')">clique aqui.</a></p>
	
	<br/>
	<c:if test="${alertMessage != null }">
		<div class="tia-g-alert-message"><b>${alertMessage}</b></div>
	</c:if>
</cdc:teacher>
