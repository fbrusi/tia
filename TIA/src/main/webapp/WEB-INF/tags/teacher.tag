<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@attribute name="extraScripts" fragment="true" %>

<!DOCTYPE html>
<html>

<title>Terminal Informativo Acadêmico</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-black.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<style>
html, body, h1, h2, h3, h4, h5, h6 {
	font-family: "Roboto", sans-serif;
}

.w3-sidebar {
	z-index: 3;
	width: 250px;
	top: 43px;
	bottom: 0;
	height: inherit;
}

.tia-alert-message {
	color: red;
}

.tia-g-alert-message {
	margin-top: 25px;
	background-color: rgb(246, 239, 5);
	color: red;
	text-align: center;
	padding-top: 5px;
	padding-bottom: 5px;
}

.tia-table-title {
	background-color: gray;
	color: white;
}

.tia-table-even {
	background-color: lightgray;
}
</style>

<body>

	<!-- Navbar -->
	<div class="w3-top">
		<div class="w3-bar w3-theme w3-top w3-left-align w3-large">
			<a class="w3-bar-item w3-button w3-right w3-hide-large w3-hover-white w3-large w3-theme-l1" href="javascript:void(0)" onclick="w3_open()"><i class="fa fa-bars"></i></a> 
				<sec:authorize access="isAuthenticated()">
					<sec:authentication property="principal" var="user"/>
						<div class="w3-bar-item w3-theme-l1">Perfil Professor - Bem vindo(a) ${user.name}</div>
					<a href="/tia/nonlogged/logout"></a>
				</sec:authorize>
			<a href="/tia/nonlogged/logout" class="w3-bar-item w3-button w3-hide-small w3-hover-white">Logoff</a>
		</div>
	</div>

	<!-- Sidebar -->
	<nav class="w3-sidebar w3-bar-block w3-collapse w3-large w3-theme-l5 w3-animate-left" id="mySidebar">
		<a href="javascript:void(0)" onclick="w3_close()" class="w3-right w3-xlarge w3-padding-large w3-hover-black w3-hide-large" title="Close Menu"> <i class="fa fa-remove"></i></a>
		
		<h4 class="w3-bar-item">
			<b>Controle Acadêmico</b>
		</h4>
		<a class="w3-bar-item w3-button w3-hover-black" href="home">Home</a>
		<a class="w3-bar-item w3-button w3-hover-black" href="schoolSubject">Matérias</a>
		<a class="w3-bar-item w3-button w3-hover-black" href="students">Alunos</a>
		<a class="w3-bar-item w3-button w3-hover-black" href="#">Notas</a>
	</nav>

	<!-- Overlay effect when opening sidebar on small screens -->
	<div class="w3-overlay w3-hide-large" onclick="w3_close()" style="cursor: pointer" title="close side menu" id="myOverlay"></div>

	<!-- Main content: shift it to the right by 250 pixels when the sidebar is visible -->
	<div class="w3-main" style="margin-left: 250px">
		<div class="w3-row w3-padding-64">
			<div class="w3-twothird w3-container">
				<jsp:doBody />
			</div>
		</div>

		<footer id="myFooter">
			<div class="w3-container w3-theme-l2 w3-padding-32">
				<h4>Terminal Informativo Acadêmico</h4>
			</div>
			<div class="w3-container w3-theme-l1">
				<p>
					Desenvolvido para fins de estudos
				</p>
			</div>
		</footer>
	</div>

	<script>
		// Get the Sidebar
		var mySidebar = document.getElementById("mySidebar");

		// Get the DIV with overlay effect
		var overlayBg = document.getElementById("myOverlay");

		// Toggle between showing and hiding the sidebar, and add overlay effect
		function w3_open() {
			if (mySidebar.style.display === 'block') {
				mySidebar.style.display = 'none';
				overlayBg.style.display = "none";
			} else {
				mySidebar.style.display = 'block';
				overlayBg.style.display = "block";
			}
		}

		// Close the sidebar with the close button
		function w3_close() {
			mySidebar.style.display = "none";
			overlayBg.style.display = "none";
		}
	</script>
	
	<jsp:invoke fragment="extraScripts" />

</body>
</html>
