/**
 * Contain every function linked to messagerie
 * 
 * changeVisibility() Switch visibility of search bar and search logo
 * controleMail() Controle si l'objet le destinataire et le corp du message n'est pas vide.
 * controleMenu() Controle si les menu lié au message dois etre affiché ou non.
 * showMenu() Affiche les menus lié a la gestion d'un seul message
 * hiddenMenu() cache les menus lié a la gestion d'un seul message
 * selectAll() selectionne toute les checkbox des messages
 * 
 */

function supprimeID(){
	
	console.log('suppression')
	var id = new Array();
	var y = 0;
	var url = "";
	
	var checkbox = document.getElementsByTagName('input');
	
	for(var i = 0;i < checkbox.length;i++){
		
		if(checkbox[i].type == "checkbox"){
			
			if(checkbox[i].checked){
				id[y] = checkbox[i].value;
				y++;
			}
		}
	}
	
	for(var i = 0;i < id.length;i++){
		if(i == 0){
			url = "http://localhost:8085/ecf-16035-b/messagerie/supprimer?id"+i+"=" + id[i];
		}else{
			url = url + "&id"+i+"=" + id[i];
		}
		
	}
	window.location.replace(url);
	console.log("changement d'adresse");
	console.log(url);
	
	
}

function selectAll(element){

	var checkbox = document.getElementsByTagName('input');
	
	if(element.checked){
		for(var i = 2;i < checkbox.length;i++){
			if(checkbox[i].type == "checkbox"){
				checkbox[i].checked = true;
				
			}
			showMenu();

		}
	}else{
		for(var i = 2;i < checkbox.length;i++){
			if(checkbox[i].type == "checkbox"){
				checkbox[i].checked = false;
				
			}
		}
		hideMenu();
	}
	
	
}

function controleMenu(element){
	
	if(element.checked){
		
		showMenu();
		
	}else{
		
	var hideBool = true;
	var checkbox = document.getElementsByTagName('input');
		
		for(var i = 2;i < checkbox.length;i++){
			if(checkbox[i].checked){
				hideBool = false;
				break;	
			}
		}
		
		if(hideBool){
			hideMenu();
		}
	}
}

function showMenu(){

	document.getElementById('repondre').style.visibility = 'visible';
	document.getElementById('supprimer').style.visibility = 'visible';
	document.getElementById('deplace').style.visibility = 'visible';
	
}

function hideMenu(){
	
	document.getElementById('repondre').style.visibility = 'hidden';
	document.getElementById('supprimer').style.visibility = 'hidden';
	document.getElementById('deplace').style.visibility = 'hidden';
	
}

function changeFocus(element){

	if(element.className == 'search-logo'){

		element = document.getElementsByClassName('search-bar')[0];
		element.focus();
	}
}

function controleNouveauMail(){
	
	var controleMailBool = true;
	
	var destination = document.getElementById('destination');
	var objet = document.getElementById('objet');
	var body = document.getElementById('body');

	destination.value.trim();
	objet.value.trim();
	body.value.trim();
	
	if(destination.value.trim().length == 0 ){
		controleMailBool = false;
		destination.setAttribute('style','background-color: #ff3333');
	}
	
	if(objet.value.trim().length == 0 ){
		controleMailBool = false;
		objet.setAttribute('style','background-color: #ff3333');
		
	}
	
	if(body.value.trim().length == 0 ){
		controleMailBool = false;
		body.setAttribute('style','background-color: #ff3333');
		
	}
	
	return controleMailBool;
}