/**
 * Javascript lié a la page d'index on y trouve les fonction suivante:
 * 
 * backgroundSize(); retaille le background de l'index a la taille de l'ecran
 * 
 */

function backgroundSize(){
	
	var width = window.innerWidth;
	var height = window.innerHeight;
	
	background = document.getElementsByClassName('background')[0].getElementsByTagName('img')[0];
	background.setAttribute('style','height: ' + height +'px;');
	background.setAttribute('style','width: ' + width +'px;');
	
	
}

function changeIcone(){
	
	logo = document.getElementsByClassName('logo')[0].getElementsByTagName('img')[0];
	
	if(logo.src == "file:///D:/workspaces/Git/ecf-16035-b/WebContent/ressource/img/logo.png"){
		logo.setAttribute('src','ressource/img/logo-variant.png');
	}else{
		logo.setAttribute('src','ressource/img/logo.png');
	}
	
	
	
}
