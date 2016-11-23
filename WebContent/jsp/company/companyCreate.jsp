<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/company.css" />
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/general.css" />

<%@ page import= "fr.cdiEnterprise.model.Company" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 
<title>Fiche entreprise créée</title>
</head>
<body>
<h1 class="title">Fiche entreprise créée</h1>
<br /><br />
	<% Company company = (Company) request.getAttribute("company"); %>
	<%=company %>
	Nom de l'entreprise: <%=company.getCompanyName() %>
	<br /><br />
	Adresse: <%=company.getAdress() %>
	<br /><br /> 
	Ville: <%= company.getCity() %> 
	<br /><br /> 
	Code postal: <%= company.getPostalCode() %> 
	<br /><br /> 
	Département: <%=company.getDepartment().getDepartmentName() %> <%=company.getDepartment().getDepartmentNumber() %>
	<br /><br /> 
	Région: <%=company.getRegion().getRegionName() %>  
	<br /><br />
	Taille de l'entreprise: <%= company.getSize() %> 
	<br/><br/>
	Secteur d'activité: <%=company.getSector() %>
	<br/><br/>
	Langages principalement utilis&eacute; <%=company.getLanguage() %> 
	<br/><br/>
	Principaux projets de l'entreprise: <%=company.getProjets() %>
	<br/><br/>
	Site Web: <%=company.getWebSite() %> 
	<br /><br /> 
	Contact
	<br/><br/>
	Nom du contact: <%=company.getContact().getName() %>
	<br /><br /> 
	Numéro de téléphone: <%=company.getContact().getPhoneNumber() %>
	<br /><br /> 
	Adresse mail: <%=company.getContact().getEmail() %> 

			
<footer>
		<a href= "<%=request.getContextPath()%>/jsp/accueil.jsp">Retour à l'accueil </a>
</footer>

</body>
</html>