<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import= "fr.cdiEnterprise.service.Regions, fr.cdiEnterprise.service.Companies, fr.cdiEnterprise.service.RecherchesFav, fr.cdiEnterprise.model.Recherche"%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="http://localhost:8085/ecf-16035-b/css/general.css"/>
<link rel="stylesheet" href="http://localhost:8085/ecf-16035-b/css/recherche.css"/>
<title>Page de recherche</title>
</head>

<%	String idUser = (String) request.getAttribute("idUser");
	Regions listeRegion = (Regions) request.getAttribute("listeRegion");
	Companies listeEntreprise = (Companies) request.getAttribute("listeEntreprises");
	RecherchesFav listeRechFav = (RecherchesFav) request.getAttribute("listeRechFav");
	Recherche RechFav = (Recherche) request.getAttribute("RechFavSelect"); %>
	
	
<body>	
<%@ include file="../WEB-INF/menu.html" %> 
<img src="http://localhost:8085/ecf-16035-b/ressource/img/logo.png" alt="logo" id="logo">
<p>Bonjour, <%=idUser%></p>
<h1 class="title"> Recherche </h1> 


<div id="blocRech">
	<form class="fieldset" name="FormRecherche" action="RechControl" method="post">
		<fieldset id="recherche" >
		<legend>Recherche</legend>
			<label class="rech">Nom de l'entreprise :</label> 
			<input type="text" name="nom_comp" 
				<% if (RechFav != null && RechFav.getCompRech()!= null) {%>
						value="<%= RechFav.getCompRech() %>"
				<%}%>
			/>
			<input type="submit" name="rechercher" value="Rechercher" formaction="RechControl/RechListe"/>
			<br/>
		
			<label class="rech">Secteur d'activité :</label> 
			<input type="text" name="secteur_comp"
				<% if (RechFav != null && RechFav.getSectorRech()!= null) {%>
						value="<%= RechFav.getSectorRech()%>"
				<%}%>
			/>	
			<br/>
		
			
			<label class="rech">Région :</label> 
			<select name="region_comp">
			
			<%if (listeRegion != null) {
				for (int i=0; i<listeRegion.size(); i++){%>
					<option value="<%=listeRegion.get(i).getRegionName()%>"> <%=listeRegion.get(i).getRegionName()%> </option>
				<%}
			}%>
			</select>
			<input type="submit" name="Enregistrer" value="Enregistrer" formaction="RechControl/EnregistrerRech"/>
			<br/>
			
			<label class="rech">Ville :</label> 
			<input type="text" name="ville_comp"
			<% if (RechFav != null && RechFav.getCityRech()!= null) {%>
						value="<%= RechFav.getCityRech() %>"
				<%}%>
			/>
			<br/>
			
			<label class="rech" style="font-size:12px">Nom d'enregistrement de recherche : </label> 
			<input type="text" name="nom_rech"/>
			
			<input type="reset" name="Reset" value="Reinitialiser"/>
			
		</fieldset>
	</form>
	
<!-- 	<div class="fieldset"> -->
	<form class="fieldset" name="FormFavoris" action="RechControl" method="post">
		<fieldset id="favoris" > <legend>Recherches Enregistrées</legend>
			<div id="listeFavoris">
			<%if (listeRechFav != null) {
				for (int i=0; i<listeRechFav.size(); i++) { %>
				<input type="radio" name="radioRech" value="<%=listeRechFav.get(i).getNomRech() %>" /> <%=listeRechFav.get(i).getNomRech()%> <br/>
				<%}	
			} else { %>
				<label class="fav">Pas de recherches favorites</label>
			
			<%} %>	
			</div>
		</fieldset>
		
		<span id="btnFavoris">
			<input type="submit" name="voir_rech" value="Voir" formaction="RechControl/RechFavAfficher"/>
			<input type="submit" name="suppr_rech" value="Supprimer" formaction="RechControl/SupprRechFav"/>
			<input type="reset" value="Reinitialiser">
		</span>
<!-- 	</div> -->
	</form>
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
				<th>Région</th>
				<th>Departement</th>
				<th>Secteur</th>
				<th>Projets</th>
				<th>Site Web</th>
			</tr>
			<% if (listeEntreprise != null) {
				for (int i=0; i<listeEntreprise.size(); i++){ %>
				<tr>
					<td><%=listeEntreprise.get(i).getCompanyName() 	%></td>
					<td><%=listeEntreprise.get(i).getAdress() 		%></td>
					<td><%=listeEntreprise.get(i).getPostalCode() 	%></td>
					<td><%=listeEntreprise.get(i).getCity() 		%></td>
					<td><%=listeEntreprise.get(i).getRegion()		%></td>
					<td><%=listeEntreprise.get(i).getDepartment()	%></td>
					<td><%=listeEntreprise.get(i).getSector() 		%></td>
					<td><%=listeEntreprise.get(i).getProjets()	 	%></td>
					<td><%=listeEntreprise.get(i).getWebSite()	 	%></td>
				</tr>
				<%}
			}%>	
		</table>
	</fieldset>
</div>
	
</body>
</html>