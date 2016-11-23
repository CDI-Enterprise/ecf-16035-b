<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<html>
<head>
<link rel="stylesheet" type="text/css" href="../css/company.css" />
<link rel="stylesheet" href="../css/general.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ page import= "fr.cdiEnterprise.service.Companies, fr.cdiEnterprise.model.Company" %> 
<title>Affichage et recherche fiche entreprises</title>
</head>
<body>
<%@ include file="../../WEB-INF/menu.html" %>
	<br/><br/><br/>
	<img src="../ressource/img/logo.png" alt="logo" id="logo">
	<h1 class="title"> Recherche ou affichage des fiches entreprises</h1>

<form action="<%=request.getContextPath()%>/Company/Affichage" method="post">
		<div class="fieldset">
		Veuillez choisir l'action à réaliser:
		<p>Pour afficher la liste complète des fiches entreprises</p>
		<input type="submit" name="list" value="Afficher" formaction="<%=request.getContextPath()%>/Company/Affichage" />
		<br/>
		<p>Pour effectuer une recherche par nom d'entreprise</p><br/>
		<label for="companyName">Nom de l'entreprise: </label> 
		<input type="text" id="companyName" name="companyName" tabindex="1" onchange="changeElement(companyName);" >
		<br />
		<input type="submit" name="search" value="Rechercher" formaction="<%=request.getContextPath()%>/Company/Rechercher" />
		</div>
	</form>
	<br /> 


	<footer>
		<a href="<%=request.getContextPath()%>/jsp/accueil.jsp">Retour à
			l'accueil </a>
	</footer>
</body>
</html>