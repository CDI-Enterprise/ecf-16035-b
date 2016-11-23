<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<html>
<head>
<link rel="stylesheet" type="text/css" href="../css/company.css" />
<link rel="stylesheet" href="../css/general.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="../JavaScript/company.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ page import= "fr.cdiEnterprise.dao.DataBaseCompany, fr.cdiEnterprise.model.Company" %> 
<title>Modifier une fiche entreprise </title>
</head>
<body>  
<%@ include file="../../WEB-INF/menu.html" %>
	<br/><br/><br/>
	<img src="../ressource/img/logo.png" alt="logo" id="logo">
	<h1 class="title">Modification d'une fiche entreprise</h1>
<% Company company = (Company) request.getAttribute("company"); %> 
		<form id= "companyForm" name= "companyForm" action="<%=request.getContextPath()%>/Company/FicheModifiee"  onreset="reset();" method="post">
			<div class="fieldset">
			<fieldset id="company" >
				<legend>Entreprise</legend>
				<br/>
				<label for="companyName">Nom de l'entreprise: </label> 
				<input type="text" id="companyName" name="companyName" value ="<%=company.getCompanyName() %>" readonly="readonly"/>
				<br /><br /> 
				<label for="companyAdress">Adresse (rue et numéro): </label>
				<input type="text" name="companyAdress" value= "<%=company.getAdress()%>" /> 
				<br /><br /> 
				<label for="companyCity">Ville: </label> 
				<input type="text" name="companyCity" id="companyCity" value="<%=company.getCity() %>" disabled="disabled" /> 
				<br /><br /> 
				<label for="companyPostalCode">Code postal: </label> 
				<input type="text" name="companyPostalCode" id="companyPostalCode" value="<%=company.getPostalCode()%>" disabled="disabled"  /> 
				<br /><br /> 
				<label for="companyDepartment">Département: </label> 
				<input type = "text" name="companyDepartment" id="departments" value="<%=company.getDepartment().getDepartmentName() %>" disabled="disabled" /> 
				<br /><br /> 
				<label for="companyRegion">Région: </label> 
				<input type="text"	name="companyRegion" id="regions" value="<%=company.getRegion().getRegionName() %>" disabled="disabled" /> 
				<br /><br />
				<label for="companySize">Taille de l'entreprise</label>
				<input type ="text" name="companySize" id = "companySize" value="<%=company.getSize() %>" disabled="disabled"/>
				<br/><br/>
				<label for="companySector">Secteur d'activité</label>
				<input type="text" name="companySector" id="companySector" value="<%=company.getSector() %>" disabled="disabled" >
				<br/><br/>
				<label for="companyLanguages">Langages principalement utilis&eacute;s</label>
				<input type="text" name ="companyLanguages" id="languages" value="<%=company.getLanguage() %>" disabled="disabled">  
				<br/><br/>
				<label for="companyProjects">Principaux projets de l'entreprise</label>
				<textarea  name="companyProjects" value="<%=company.getProjets() %>" disabled="disabled" ></textarea> 
				<br/><br/>
				<label for="companyWebSite">Site Web: </label> 
				<input type="text" name="companyWebSite" id="companyWebSite" value="<%=company.getWebSite() %>" disabled="disabled"> 
				<br /><br /> 
			</fieldset>
			</div>
			<br />
				<br />
			<div class="button">
		<input type="submit" name="validate" value="OK" />
		<input type="reset" name="cancel" value= "Annuler"/>
</div>
		</form>
</body>
</html>