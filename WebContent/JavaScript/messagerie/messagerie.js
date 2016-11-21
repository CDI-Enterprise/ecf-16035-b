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

function afficheMessage(element){
	
	console.log(element.className);
	
}