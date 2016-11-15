/**
 * Javascript lié a la page d'index on y trouve les fonction suivante:
 * 
 * backgroundSize(); retaille le background de l'index a la taille de l'ecran
 * checkAccount(); qui verifie si le compte existe en base de données ou non pour la connexion.
 */

function backgroundSize(){
	
	var width = window.innerWidth;
	var height = window.innerHeight;
	
	background = document.getElementsByClassName('background')[0].getElementsByTagName('img')[0];
	background.setAttribute('style','height: ' + height +'px;');
	background.setAttribute('style','width: ' + width +'px;');
	
	
}
