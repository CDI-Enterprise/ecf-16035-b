<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ page
	import="fr.cdiEnterprise.model.Company, fr.cdiEnterprise.service.Companies"%>
<link rel="stylesheet" type="text/css" href="../css/company.css" />
<link rel="stylesheet" href="../css/general.css" />
<script type="text/javascript" src="../JavaScript/company.js"></script>
<title>Modification - Suppression fiche entreprise</title>
</head>
<body>
	<%@ include file="../WEB-INF/menu.html"%>
	<br />
	<br />
	<br />
	<img src="../ressource/img/logo.png" alt="logo" id="logo">
	<h1 class="title">Modification ou suppression d'une fiche
		entreprise</h1>
	<br />
	<form action="<%=request.getContextPath()%>/Company/ModifSuppr" method="post">
		<div class="fieldset">
			<fieldset id="companies">
				<legend>Liste des fiches entreprises</legend>
				<br /> 
				<label for="companiesSelec">Veuillez sélectionner une entreprise: </label> 
				<select name="companiesSelec" id="companiesSelec">
					<%
						Companies companies = (Companies) request.getAttribute("companies");
						for (Company company : companies) {
					%>
					<option value="<%=company.getCompanyName()%>"><%=company.getCompanyName()%>
					</option>
					<%
						}
					%>
				</select> <br /> <br />
			</fieldset>
		</div>
		<div class="button">
			<input type="submit" name="update" value="Modifier"
				formaction="<%=request.getContextPath()%>/Company/Modifier" /> <input
				type="submit" name="delete" value="Supprimer"
				formaction="<%=request.getContextPath()%>/Company/Supprimer" />

		</div>
	</form>
	<br />


	<footer>
		<a href="<%=request.getContextPath()%>/jsp/accueil.jsp">Retour à
			l'accueil </a>
	</footer>
</body>
</html>