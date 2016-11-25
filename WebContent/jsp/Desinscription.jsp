<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="../css/Inscription.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Desinscription</title>
<%@include file="../WEB-INF/menu.html"%>
<script type="text/javascript">
function verifier() {      // confère demo proposition de solution javaScript
	
	var nomvideEXP = /./
	var prenomvideEXP = /./
	
	if (!nomvideEXP.test(document.all.nom.value)) {
		alert("Merci de vérifier que le champ nom n'est pas vide");
		document.all.nom.focus();
		return false;
	}
	if (!prenomvideEXP.test(document.all.prenom.value)) {
		alert("Merci de vérifier que le champ prenom n'est pas vide");
		document.all.prenom.focus();
		return false;
	}
	
}
</script>
</head>
<body>
	<div class ="b">
		<p><h1>Bienvenue</h1></p>
		<p><h4>Pour vous désinscrire, merci de remplir les champs ci-dessous</h4></p>
	</div>
	
	<form method= "post" onsubmit="return verifier()" action="../desinscription">
		
		<fieldset>
	    	<legend><h3>Desinscription</h3></legend>
	    	
	    	<div>
		    	<p>Statut actuel <input type="radio"
			    name="radio" value = "Stagiaire"> Stagiaire <input type="radio" name="radio" value = "Formateur"> Formateur <input
			    type="radio" name="radio" value = "Ancien Stagiaire"> Ancien stagiaire</p>
	    	</div>
	    
	    	<div>
			    <p><label>
			    <input type="text" class ="t" name="nom" placeholder="Votre Nom ici" aria-label="nom" maxlength="24"></label></p>

			    <p><label>
			    <input type="text" class ="t" name="prenom" placeholder="Votre prénom ici" aria-label="prenom" maxlength="24"></label></p>

			    <p><label>
			    <input type="text" class ="e" name="email" placeholder="Votre E-mail ici" aria-label="email" maxlength="34"></label></p>

			    <p><label>
			    <input type="password" class ="pwd" name="motDePasse" placeholder="Votre choix de mot de passe" aria-label="passWord" maxlength="24"> </label></p>

			    <p><label>
			    <input type="password" class ="pwd" name="confirmation" placeholder="Confirmer votre saisie" aria-label="ConfirmerPw" maxlength="24"></label> </p>
	    	</div>
	    
	    	<div>	    
			    <p>
			    <input type="submit" NAME="valider" onclick= "return verifier()" id="btn" value="Se désinscrire"> 
			    <input type="reset" NAME="nom" id="btn1 value=" Annuler"></p>	  	
			</div>
			
		</fieldset>
	 </form>

	<div class = "nom">
			<p> Vous voulez plutôt vous connecter ? <a href="/ecf-16035-b">Connexion</a></p>
	</div> 
</body>
</html>