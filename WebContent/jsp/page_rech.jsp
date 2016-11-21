<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import= "fr.cdiEnterprise.service.Regions, fr.cdiEnterprise.service.Companies"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../css/general.css"/>
<link rel="stylesheet" href="../css/recherche.css"/>

<title>Page de recherche</title>
</head>

<%	String utilisateur="Francois"; 
	Regions listeRegion = (Regions) request.getAttribute("listeRegion");
	Companies listeEntreprise = (Companies) request.getAttribute("listeEntreprise");%>
	
	
<body>	
<%@ include file="../WEB-INF/menu.html" %> 
<img src="../ressource/img/logo.png" alt="logo" id="logo">
<h1 class="title"> Recherche </h1>


<div id="blocRech">
	<form class="fieldset" action="RechControl" method="post">
		<fieldset id="recherche" >
		<legend>Recherche</legend>
			<label class="rech">Nom de l'entreprise :</label> <input type="text" name="nom_comp"/>
			<input type="button" name="rechercher" value="Rechercher"/>
			<br/>
		
			<label class="rech">Secteur d'activité :</label> <input type="text" name="secteur_comp"/>
			<br/>
			
			<label class="rech">Région :</label> 
			<select name="region_comp">
			<%for (int i=0; i<listeRegion.size(); i++){%>
				<option value="<%=i%>"> <%=listeRegion.get(i).getRegionName()%> </option>
			<%}%>
			</select>
			<input type="button" name="Enregistrer" value="Enregistrer"/>
			<br/>
			
			<label class="rech">Ville :</label> <input type="text" name="ville_comp"/>
			<input type="button" name="Reset" value="Reinitialiser"/>
		</fieldset>
	</form>
	
	<div class="fieldset">
		<fieldset id="favoris" > <legend>Recherches Enregistrées</legend>
			<div id="listeFavoris">
				<input type="radio" name="radioRech"/> <label class="fav">Ma recherche</label> 
				<br/>
				<input type="radio" name="radioRech"/> <label class="fav">Ma deuxieme recherche</label> 
				<br/>
			</div>
		</fieldset>
		
		<span id="btnFavoris">
			<input type="button" name="voir_rech" value="Voir"/>
			<input type="button" name="suppr_rech" value="Supprimer"/>
		</span>
	</div>
</div>

<div class="listeEntreprises">
	<fieldset id="liste">	
	<legend>Entreprises</legend>
		<table>
			<tr>
				<th>Nom</th>
				<th>Adresse</th>
				<th>Code postal</th>
				<th>Ville</th>
				<th>Secteur</th>
				<th>Site Web</th>
			</tr>
			<% for (int i=0; i<listeEntreprise.size(); i++){ %>
			<tr>
				<td><%=listeEntreprise.get(i).getCompanyName() 	%></td>
				<td><%=listeEntreprise.get(i).getAdress() 		%></td>
				<td><%=listeEntreprise.get(i).getPostalCode() 	%></td>
				<td><%=listeEntreprise.get(i).getCity() 		%></td>
				<td><%=listeEntreprise.get(i).getSector() 		%></td>
				<td><%=listeEntreprise.get(i).getWebSite()	 	%></td>
			</tr>
			<%}%>	
		</table>
	</fieldset>
</div>
	
</body>
</html>