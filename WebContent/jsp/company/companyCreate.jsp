<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/company.css" />
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/general.css" />

<%@ page import= "fr.cdiEnterprise.model.Company" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Fiche entreprise cr��e</title>
</head>
<body>
<h1 class="title">Fiche entreprise cr��e</h1>
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
	D�partement: <%=company.getDepartment().getDepartmentName() %> <%=company.getDepartment().getDepartmentNumber() %>
	<br /><br /> 
	R�gion: <%=company.getRegion().getRegionName() %>  
	<br /><br />
	Taille de l'entreprise: <%= company.getSize() %> 
	<br/><br/>
	Secteur d'activit�: <%=company.getSector() %>
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
	Num�ro de t�l�phone: <%=company.getContact().getPhoneNumber() %>
	<br /><br /> 
	Adresse mail: <%=company.getContact().getEmail() %> 

			
<footer>
		<a href= "<%=request.getContextPath()%>/jsp/Accueil.jsp">Retour � l'accueil </a>
</footer>

</body>
</html>