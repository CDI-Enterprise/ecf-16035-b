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
<script type="text/javascript" src="../JavaScript/recherche.js"></script>
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
	<form class="fieldset" name="FormRecherche" action="/Recherche" method="post">
		<fieldset id="recherche" >
		<legend>Recherche</legend>
			<label class="rech">Nom de l'entreprise :</label> 
			<input type="text" name="nom_comp" id="nom_comp" 
				<% if (RechFav != null && RechFav.getCompRech()!= null) {%>
						value="<%= RechFav.getCompRech() %>"
				<%}%>
			/>
			<input type="submit" name="rechercher" value="Rechercher" formaction="http://localhost:8085/<%=request.getContextPath()%>/Recherche/RechListe"/>
			<br/>
		
			<label class="rech">Secteur d'activité :</label> 
			<input type="text" name="secteur_comp" id="secteur_comp"
				<% if (RechFav != null && RechFav.getSectorRech()!= null) {%>
						value="<%= RechFav.getSectorRech()%>"
				<%}%>
			/>	
			<br/>
		
			
			<label class="rech">Région :</label> 
			<select name="region_comp" disabled="disabled">
			
			<%if (listeRegion != null) {
				for (int i=0; i<listeRegion.size(); i++){%>
					<option value="<%=listeRegion.get(i).getRegionName()%>"> <%=listeRegion.get(i).getRegionName()%> </option>
				<%}
			}%>
			</select>
			<input type="submit" name="enregistrer" value="Enregistrer" onclick="return verifSaisie();" formaction="http://localhost:8085/<%=request.getContextPath()%>/Recherche/EnregistrerRech"/>
			<br/>
			
			<label class="rech">Ville :</label> 
			<input type="text" name="ville_comp" id="ville_comp"
			<% if (RechFav != null && RechFav.getCityRech()!= null) {%>
						value="<%= RechFav.getCityRech() %>"
				<%}%>
			/>
			<br/>
			
			<label class="rech" style="font-size:12px">Nom d'enregistrement de recherche : </label> 
			<input type="text" name="nom_rech" id="nom_rech"
				<% if (RechFav != null && RechFav.getNomRech()!= null) {%>
						value="<%= RechFav.getNomRech()%>"
				<%}%>
			/>
			
			<input type="submit" name="reset" value="Reinitialiser" formaction="http://localhost:8085/<%=request.getContextPath()%>/Recherche/Affichage" formmethod="get"/>
			
		</fieldset>
	</form>
	

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
			<input type="submit" name="voir_rech" value="Voir" formaction="http://localhost:8085/<%=request.getContextPath()%>/Recherche/RechFavAfficher"/>
			<input type="submit" name="suppr_rech" value="Supprimer" formaction="http://localhost:8085/<%=request.getContextPath()%>/Recherche/RechFavSuppr"/>
			<input type="reset" name="resetFav" value="Reinitialiser">
		</span>
	</form>
</div>

<div class="listeEntreprises">
	<fieldset id="liste">	
	<legend>Entreprises</legend>
		<table id="tableauRech">
			<tr>
				<th>Nom			</th>
				<th>Adresse		</th>
				<th>Code postal	</th>
				<th>Ville		</th>
				<th>Région		</th>
				<th>Secteur		</th>
				<th>Langage		</th>
				<th>Projets		</th>
				<th>Site Web	</th>
			</tr>
			<% if (listeEntreprise != null) {
				for (int i=0; i<listeEntreprise.size(); i++){ %>
				<tr>
					<td><%=listeEntreprise.get(i).getCompanyName() 	%>				</td>
					<td><%=listeEntreprise.get(i).getAdress() 		%>				</td>
					<td><%=listeEntreprise.get(i).getPostalCode() 	%>				</td>
					<td><%=listeEntreprise.get(i).getCity() 		%>				</td>
					<td><%=listeEntreprise.get(i).getRegion().getRegionName()%>		</td>
					<td><%=listeEntreprise.get(i).getSector() 		%>				</td>
					<td><%=listeEntreprise.get(i).getLanguage().getLanguageName()%>	</td>
					<td><%=listeEntreprise.get(i).getProjets()	 	%>				</td>
					<td><%=listeEntreprise.get(i).getWebSite()	 	%>				</td>
				</tr>
				<%}
			}%>	
		</table>
	</fieldset>
</div>
	
</body>
</html>