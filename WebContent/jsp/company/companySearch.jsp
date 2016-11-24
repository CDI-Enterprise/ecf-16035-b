<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<html>
<head>
<link rel="stylesheet" type="text/css" href="../css/company.css" />
<link rel="stylesheet" href="../css/general.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ page
	import="fr.cdiEnterprise.service.Companies, fr.cdiEnterprise.model.Company"%>
<title>Résultat de la recherche</title>
</head>
<body>
	<%@ include file="../../WEB-INF/menu.html"%>
	<br />
	<br />
	<br />
	<img src="../ressource/img/logo.png" alt="logo" id="logo">
	<h1 class="title">Résultat de la recherche</h1>

	<%
		Company company = (Company) request.getAttribute("company");
	%>


	<%
		if (company != null) {
	%>
	<div id="list">
		<TABLE class="companiesList">
			<TR>
				<TH>Nom de l'entreprise</TH>
				<TH>Adresse</TH>
				<TH>Ville</TH>
				<TH>Code postal</TH>
				<TH>Département</TH>
				<TH>Région</TH>
				<TH>Langage</TH>
				<TH>Secteur d'activité</TH>
				<TH>Taille</TH>
				<TH>Site Web</TH>
			</TR>
			<TR>
				<TD><%=company.getCompanyName()%></TD>
				<TD><%=company.getAdress()%></TD>
				<TD><%=company.getCity()%></TD>
				<TD><%=company.getPostalCode()%></TD>
				<TD><%=company.getDepartment().getDepartmentName()%></TD>
				<TD><%=company.getRegion().getRegionName()%></TD>
				<TD><%=company.getLanguage().getLanguageName()%></TD>
				<TD><%=company.getSector()%></TD>
				<TD><%=company.getSize()%></TD>
				<TD><%=company.getWebSite()%></TD>
			</TR>
		</TABLE>
			</div>
		<%
			} else {
		%>
		<div class="info">
			<p>Aucune fiche entreprise correspond à cette recherche n'a été
				trouvée</p>
		</div>
		<%
			}
		%>


	<footer>
		<a href="<%=request.getContextPath()%>/jsp/accueil.jsp">Retour à
			l'accueil </a>
	</footer>

</body>
</html>
