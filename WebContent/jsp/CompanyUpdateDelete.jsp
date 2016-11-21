<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ page import= "fr.cdiEnterprise.model.Company, fr.cdiEnterprise.service.Companies" %>
<link rel="stylesheet" type="text/css" href="../css/company.css" />
<link rel="stylesheet" href="../css/general.css" />
<title>Modification - Suppression fiche entreprise</title>
</head>
<body>
<%@ include file="../WEB-INF/menu.html" %>
	<br/><br/><br/>
<img src="../ressource/img/logo.png" alt="logo" id="logo">
<h1 class="title">Modification ou suppression d'une fiche entreprise</h1>
<br />
	
	<form id= "selectCompany" name= "selectCompany" action="<%=request.getContextPath()%>/Company/ModifSuppr" 
		onsubmit= "return validate();" onreset="reset();" method="post">
		<div class = "fieldset">
		<fieldset id="company" >
				<legend>Liste des fiches entreprises</legend>
				<br/>
		<label for="companies">Veuillez sélectionner une entreprise: </label> 
<!-- 				<select	name="companies" id="companies" > -->
<%-- 					<% Companies companies = (Companies) request.getAttribute("companies");  --%>
<%--  						for (Company company : companies){%>  --%>
<%-- 					<option value = "<%=company.getCompanyName()%>"><%=company.getCompanyName()%> </option> --%>
<%-- 					<%} %>  --%>
<!-- 				</select>  -->
	</fieldset>
		</div>
	</form> 
	<br/>
	<div>
		<form id= "companyForm" name= "companyForm" action="" 
		onsubmit= "return validate();" onreset="reset();" method="post">
			<div class="fieldset">
			<fieldset id="company" >
				<legend>Entreprise</legend>
				<br/>
				<label for="companyName">Nom de l'entreprise: </label> 
				<input type="text" id="companyName" name="companyName" tabindex="1" onchange="changeElement(companyName);" >
				<br /><br /> 
				<label for="companyAdress">Adresse (rue et numéro): </label>
				<input type="text" name="companyAdress" tabindex="2"> 
				<br /><br /> 
				<label for="companyCity">Ville: </label> 
				<input type="text" name="companyCity" id="companyCity" tabindex="2" onchange="changeElement(companyCity)" > 
				<br /><br /> 
				<label for="companyPostalCode">Code postal: </label> 
				<input type="text" name="companyPostalCode" id="companyPostalCode" tabindex="3" onchange="changeElement(companyPostalCode);" > 
				<br /><br /> 
				<label for="companyDepartment">Département: </label> 
				<input type = "text" name="companyDepartment" id="departments" > 
				<br /><br /> 
				<label for="companyRegion">Région: </label> 
				<input type="text"	name="companyRegion" id="regions" > 
				<br /><br />
				<label for="companySize">Taille de l'entreprise</label>
				<input type ="text" name="companySize" id = "companySize"/>
				<br/><br/>
				<label for="companySector">Secteur d'activité</label>
				<input type="text" name="companySector" id="companySector" tabindex="4" onchange="changeElement(companySector);" >
				<br/><br/>
				<label for="companyLanguages">Langages principalement utilis&eacute;s</label>
				<input type="text" name ="companyLanguages" id="languages" >  
				<br/><br/>
				<label for="companyProjects">Principaux projets de l'entreprise</label>
				<textarea type="text" name="companyProjects" tabindex="5" ></textarea> 
				<br/><br/>
				<label for="companyWebSite">Site Web: </label> 
				<input type="text" name="companyWebSite" id="companyWebSite" tabindex="6" onchange="changeElement(companyWebSite);"> 
				<br /><br /> 
			</fieldset>
			</div>
			<br />
			<div class="fieldset">
			<fieldset>
				<legend>Contact Entreprise</legend>
					<br />
				<!-- TODO formulaire pour contact -->
				<label for="contactName">Nom du contact: </label> 
				<input type="text" name="contactName" tabindex="7">
				<br /><br /> 
				<label for="contactPhone">Numéro de téléphone: </label>
				<input type="text" name="companyAdress" tabindex="8"> 
				<br /><br /> 
				<label for="contactMail">Adresse mail: </label> 
				<input type="email" name="contactMail" tabindex="9"> 
			</fieldset></div>
				<br />
			<div class="button">
		<input type="submit" name="valid" value="OK" />
		<input type="reset" name="cancel" value="Annuler">
</div>
		</form>
			
<footer>
		<a href= "<%=request.getContextPath()%>/jsp/accueil.jsp">Retour à l'accueil </a>
</footer>
</body>
</html>