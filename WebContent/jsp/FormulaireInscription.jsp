
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
		<form action="Post">
		
		<div>
			<p>Bienvenue</p>
			<p>Vous pouvez vous incrire en remplissant le formulaire ci-dessous</p>
		</div>

	  	<fieldset style="width: 50%">
	    	<legend>Inscription</legend>
	    	
	    	<div>
		    	<p style="margin-left:2em">Statut actuel <input type="radio"
			    name="radio"> Stagiaire <input type="radio" name="radio"> Formateur <input
			    type="radio" name="radio"> Ancien stagiaire</p>
	    	</div>
	    
	    	<div>
			    <p style="margin-left:2em"><label>
			    <input type="text" id="n" name="nom" placeholder="Votre Nom ici" aria-label="nom" maxlength="24"></label></p>

			    <p style="margin-left:2em"><label>
			    <input type="text" id="p" name="prenom" placeholder="Votre prénom ici" aria-label="prenom" maxlength="24"></label></p>

			    <p style="margin-left:2em"><label>
			    <input type="text" id="e" name="email" placeholder="Votre E-mail ici" aria-label="email" maxlength="34"></label></p>

			    <p style="margin-left:2em"><label>
			    <input type="password" id="pwd" name="motDePasse" placeholder="Votre choix de mot de passe" aria-label="passWord" maxlength="24"> </label></p>

			    <p style="margin-left:2em"><label>
			    <input type="password" id="rpwd" name="confirmation" placeholder="Confirmer votre saisie" aria-label="ConfirmerPw" maxlength="24"></label> </p>
	    	</div>
	    
	    	<div>
	    
			    <p style="margin-left:2em">
			    <input type="submit" value="S'inscrire"> 
			    <INPUT TYPE="reset" NAME="nom" VALUE=" Annuler "></p>
	  	
			</div>

	    </fieldset>
		</form>

		<div>
			<p style="margin-right:auto;line-height:2em;text-align:justify;margin-left:2em"> Vous possedez un compte ? <a href="Connexion">Connexion</a></p>
		</div> 
	
</body>
</html>