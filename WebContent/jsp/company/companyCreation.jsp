<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<html>
<head>
<link rel="stylesheet" type="text/css" href="../css/company.css" />
<link rel="stylesheet" href="../css/general.css" />
<script type="text/javascript" src="../JavaScript/company.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ page import= "fr.cdiEnterprise.dao.DataBaseCompany, fr.cdiEnterprise.service.Departments,fr.cdiEnterprise.service.Languages,
  fr.cdiEnterprise.service.Regions, fr.cdiEnterprise.model.Department, fr.cdiEnterprise.model.Region, fr.cdiEnterprise.model.Language" %> 
<title>Entreprise - CDI Enterprise</title>
</head>
<body>
	<%@ include file="../../WEB-INF/menu.html" %>
	<br/><br/><br/>
	<img src="../ressource/img/logo.png" alt="logo" id="logo">
	<h1 class="title">Création d'une fiche entreprise</h1>
	<div>
		<form id= "companyForm" name= "companyForm" action="<%=request.getContextPath()%>/Company/CompanyCreate" 
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
				<select	name="companyDepartment" id="departments" >
					<% Departments departments = (Departments) request.getAttribute("departments"); 
 						for (Department department : departments){%> 
					<option value = "<%=department.getDepartmentName()%>"><%=department.getDepartmentName()%> </option>
					<%} %> 
				</select> 
				<br /><br /> 
				<label for="companyRegion">Région: </label> 
				<select	name="companyRegion" id="regions" >
				<% Regions regions = (Regions) request.getAttribute("regions"); 
						for (Region region : regions){%>
					<option value = "<%=region.getRegionName()%>"><%=region.getRegionName()%> </option>
					<%} %> 
				</select> 
				<br /><br />
				<label for="companySize">Taille de l'entreprise</label>
				<input class="btnRadio" type="radio" name="companySize" value="micro-entreprise" checked /> MicroEntreprise (&lsaquo;10)
				<input class="btnRadio" type="radio" name="companySize" value="PME" /> PME (&lsaquo;250) 
				<input class="btnRadio" type="radio" name="companySize" value="ETI" /> ETI (&rsaquo;250 et &lsaquo;5000) 
				<input class="btnRadio" type="radio" name="companySize" value="grandeEntreprise" /> Grande entreprise 
				<br/><br/>
				<label for="companySector">Secteur d'activité</label>
				<input type="text" name="companySector" id="companySector" tabindex="4" onchange="changeElement(companySector);" >
				<br/><br/>
				<label for="companyLanguages">Langages principalement utilis&eacute;s</label>
				<select name ="companyLanguages" id="languages" size="3"> 
					<% Languages languages = (Languages) request.getAttribute("languages"); 
						for (Language language : languages){%> 
					<option value = "<%=language.getLanguageName()%>"><%=language.getLanguageName()%> </option>
					<%} %> 
					</select> 
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


	</div>
</body>
</html>