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
</style>

<body>
	<div class="w3-top">
		<div class="w3-bar w3-theme w3-top w3-left-align w3-large"
			style="text-align: center;!important">
			<h2>Terminal Informativo Acadêmico</h2>
		</div>
	</div>

	<!-- Main content: shift it to the right by 250 pixels when the sidebar is visible -->
	<div class="w3-main" style="margin-left: 250px">
		<div class="w3-row w3-padding-64">
			<div class="w3-twothird w3-container">
				<jsp:doBody />
			</div>
		</div>
	</div>

	<footer id="myFooter">
		<div class="w3-container w3-theme-l1">
			<p>Desenvolvido para fins de estudos</p>
		</div>
	</footer>

	<!-- END MAIN -->

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

</body>
</html>
