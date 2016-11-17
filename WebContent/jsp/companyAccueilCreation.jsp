<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- <style> -->
<link rel="stylesheet" href="../css/company.css" />
<link rel="stylesheet" href="../css/general.css" />
<!-- @import url(../css/company.css); -->
<!-- </style> -->
<script type="text/javascript" src="../JavaScript/company.js">

</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ page import= "fr.cdiEnterprise.dao.DataBaseCompany, fr.cdiEnterprise.service.Departments,fr.cdiEnterprise.service.Languages,
 fr.cdiEnterprise.service.Regions, fr.cdiEnterprise.model.Department, fr.cdiEnterprise.model.Region, fr.cdiEnterprise.model.Language" %>
<title>Entreprise - CDI Enterprise</title>
</head>
<body>
	
	<%@ include file="../WEB-INF/menu.html" %>
	<h1 class="title">Cr�ation d'une fiche entreprise</h1>

	<div>
		<form id= "companyForm" name= "companyForm" action="http://localhost:8085/ecf-16035-b/Company/Creation/ok" 
		onsubmit= "return validate();" onreset="reset();" method="post">
			<div class="fieldset">
			<fieldset id="company" >
				<legend>Entreprise</legend>
				<br/>
				<label for="companyName">Nom de l'entreprise: </label> 
				<input type="text" id="companyName" name="companyName" class="obligatoire" tabindex="1" onblur="supprimer(companyName);" >
				<br /><br /> 
				<label for="companyAdress">Adresse (rue et num�ro): </label>
				<input type="text" name="companyAdress" tabindex="2"> 
				<br /><br /> 
				<label for="companyCity">Ville: </label> 
				<input type="text" name="companyCity" id="companyCity" class="obligatoire" tabindex="2" > 
				<br /><br /> 
				<label for="companyPostalCode">Code postal: </label> 
				<input type="text" name="companyPostalCode" id="companyPostalCode" class="obligatoire" tabindex="3" > 
				<br /><br /> 
				<label for="companyDepartment">D�partement: </label> 
				<select	name="companyDepartment" id="departments" required>
					<!-- 	onClick="document.formulaire.codepostal.value=document.formulaire.departements.value;" -->
					<% Departments departments = (Departments) request.getAttribute("departments"); 
						for (Department department : departments){%> 
					<option value = "<%=department.getDepartmentName()%>"><%=department.getDepartmentName()%> </option>
					<%} %> 
				</select> 
				<br /><br /> 
				<label for="companyRegion">R�gion: </label> 
				<select	name="companyRegion" id="regions" required>
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
				<label for="companySector">Secteur d'activit�</label>
				<input type="text" name="companySector" id="companySector" class="obligatoire" tabindex="4" >
				<br/><br/>
				<label for="companyLanguages">Langages principalement utilis&eacute;s</label>
				<select name ="companyLanguages" size="3"> 
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
				<input type="text" name="companyWebSite" class="obligatoire" tabindex="6"> 
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
				<label for="contactPhone">Num�ro de t�l�phone: </label>
				<input type="" name="companyAdress" tabindex="8"> 
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