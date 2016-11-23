<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<html>
<head>
<link rel="stylesheet" type="text/css" href="../css/company.css" />
<link rel="stylesheet" href="../css/general.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ page import= "fr.cdiEnterprise.service.Companies, fr.cdiEnterprise.model.Company" %> 
<title>Affichage des fiches entreprises</title>
</head>
<body>
<%@ include file="../../WEB-INF/menu.html" %>
	<br/><br/><br/>
	<img src="../ressource/img/logo.png" alt="logo" id="logo">
	<h1 class="title"> Recherche et affichage des listes entreprises</h1>

<%	Companies companies= (Companies) request.getAttribute("companies");%>
	
<%	if(!companies.isEmpty()){
%>
 
<div id="list">
<TABLE class= "companiesList">
			<TR>
			<TH>Nom de l'entreprise</TH>
			<TH>Adresse</TH>
			<TH>Ville</TH>
			<TH>Code postal</TH>
			<TH>Département</TH>
			<TH>Région</TH>
			<TH>Secteur d'activité</TH>
			<TH>Taille</TH>
			<TH>Site Web</TH>
			</TR>
			 
			<% for (Company company : companies){%>
			<TR>
			<TD><%= company.getCompanyName()%></TD>
			<TD><%= company.getAdress()%></TD>
			<TD><%= company.getCity()%></TD>
			<TD><%= company.getPostalCode()%></TD>
			<TD><%= company.getDepartment().getDepartmentName()%></TD>
			<TD><%= company.getRegion().getRegionName()%></TD>
			<TD><%= company.getSector()%></TD>
			<TD><%= company.getSize()%></TD>
			<TD><%= company.getWebSite()%></TD>
			</TR>
			<% } %>
			</TABLE>
	<% }else{ %>
<p>Aucune fiche entreprise n'a été trouvée</p>
			<%} %>
</div>



</body>
</html>