<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../css/general.css"/>
<link rel="stylesheet" href="../css/recherche.css"/>

<title>Page de recherche</title>
</head>

<body>


<img src="../ressource/img/star-trek-100-Transparent.png" alt="image star trek" id="logo">
<h1 class="title"> Recherche </h1>


<div id="blocRech">
<form class="fieldset" action="RechControl" method="post">
	<div >
	<fieldset id="recherche" >
	<legend>Recherche</legend>
		<label class="rech">Nom de l'entreprise :</label> <input type="text" name="nom_comp"/>
		<input type="button" name="rechercher" value="Rechercher"/>
		<br/>
	
		<label class="rech">Secteur d'activité :</label> <input type="text" name="secteur_comp"/>
		<br/>
		
		<label class="rech">Région :</label> <input type="text" name="region_comp"/>
		<input type="button" name="Enregistrer" value="Enregistrer"/>
		<br/>
		
		<label class="rech">Ville :</label> <input type="text" name="ville_comp"/>
		<input type="button" name="Reset" value="Reinitialiser"/>
	</fieldset>
	</div>
</form>



<div class="fieldset">
<fieldset id="favoris" >
<legend>Recherches Enregistrées</legend>
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

</body>
</html>