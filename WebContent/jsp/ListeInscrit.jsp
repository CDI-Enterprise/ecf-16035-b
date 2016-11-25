<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="../css/Inscription.css" />
<%@ page import="fr.cdiEnterprise.service.Inscriptions" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Liste des inscrits</title>
</head>
<%@include file="../WEB-INF/menu.html"%>
<body>

</br>
</br>	
<p><label>Liste des inscrits</label></p>

<form action="../listeInscrits">
  
<% Inscriptions liste = (Inscriptions) request.getAttribute("inscriptions");%>
<table border="3" style="width: 70%">
  
    <tr>
      <td>Reference</td>
      <td>Statut</td>
      <td>Nom</td>
      <td>Prenom</td>
      <td>Email</td>
      <td>MotDePasse</td>
      <td>Confirmation</td>
    </tr>
    <%for(int i = 0; i < liste.size(); i++){%>
    <tr>
      <td><%= liste.get(i).getReference() %></td>
      <td><%= liste.get(i).getStatut() %></td>
      <td><%= liste.get(i).getNom() %></td>
      <td><%= liste.get(i).getPrenom() %></td>
      <td><%= liste.get(i).getEmail() %></td>
      <td><%= liste.get(i).getMotDePasse() %></td>
      <td><%= liste.get(i).getConfirmation() %></td>
    </tr>
    <%}%>
 
</table>
</form>

<p id="L"><a
        href="http://localhost:8085/ecf-16035-b/index.html">vers la page d'accueil</a></p>

</body>
</html>