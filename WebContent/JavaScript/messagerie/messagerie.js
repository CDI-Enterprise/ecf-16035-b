/**
 * Contain every function linked to messagerie
 * changeVisibility() Switch visibility of search bar and search logo
 */

function changeFocus(element){
	
	console.log(element);
	
	if(element.className == 'search-logo'){
		
		//
		element = document.getElementsByClassName('search-bar')[0];
		element.focus();
	}
	
	
	
}

function controleMail(formulaire){
	
	console.log(formulaire);
	controleMailBool = true;
	destination = formulaire.getElementById('destination');
	objet = formulaire.getElementById('objet');
	body = formulaire.getElementById('body');
	
	if(destination.value.length == 0 ){
		controleMailBool = false;
		destination.setAttribute('style','background-color: #ff3333')
	}
	
	if(objet.value.length == 0 ){
		controleMailBool = false;
		objet.setAttribute('style','background-color: #ff3333')
		
	}
	
	if(body.value.length == 0 ){
		controleMailBool = false;
		body.setAttribute('style','background-color: #ff3333')
		
	}
	
	return controleMailBool;
}