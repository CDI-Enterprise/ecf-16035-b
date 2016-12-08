/**
 * Contain every function linked to messagerie
 * 
 * changeVisibility() Switch visibility of search bar and search logo
 * changeFocus() modifie le focus quand on click sur la loupe de recherche pour mettre le focus dans la bar de recherche
 * controleNouveauMail() Controle si l'objet le destinataire et le corp du message n'est pas vide.
 * controleMenu() Controle si les menu lié au message dois etre affiché ou non.
 * showMenu() Affiche les menus lié a la gestion d'un seul message
 * hideMenu() cache les menus lié a la gestion d'un seul message
 * selectAll() selectionne toute les checkbox des messages
 * supprimeID() modifie l'url en ajoutant toute les ID lié au checkbox selectionné pour suppression.
 *  compteLongueur() fait un decompte du nombre de caractere restant pour la taille du corp du mail
 *  recherche() recherche dans la liste des mail l'objet qui correspond a la recherche.
 */

function recherche(recherche){

	var search = recherche.value;
	var ligne = document.getElementsByTagName("tr");
	var parent = document.getElementById('content');
	var x = 0;

	
	for(var i = 1;i < ligne.length;i++){
		if(ligne[i].getElementsByTagName('a')[0].innerHTML.trim().startsWith(search)){
			
			console.log('je garde');
			
		}else{
			//TODO Passer les mail en hidden? oldLigne[x] = 
			ligne[i].remove();
			x++;
		}
	}
	
}

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
	
	if(destination.value.trim().length == 0){
		
		controleMailBool = false;
		
		var messageErr = document.createElement('span');
		messageErr.setAttribute("class","msgErr");
		var textNode = document.createTextNode("Champs Requis");
		messageErr.appendChild(textNode);
		
		var parentElement = destination.parentNode;
		var refElement = destination.nextSibling;
		if(refElement.tagName == "SPAN"){
			
			parentElement.replaceChild(refElement, refElement);
			
		}else{
			var insertElement = parentElement.insertBefore(messageErr, refElement);
		}

		destination.setAttribute('style','background-color: #ff3333');
		
	}else{
		
		var refElement = destination.nextSibling;
		var parentElement = destination.parentNode;
		
		if(refElement.tagName == "SPAN"){
			
			parentElement.removeChild(refElement);
			
		}
		
		destination.setAttribute('style','background-color: #ebebe0');
		
	}
	
	if(objet.value.trim().length == 0){
		
		controleMailBool = false;
		
		messageErr = document.createElement('span');
		messageErr.setAttribute("class","msgErr");
		textNode = document.createTextNode("Champs Requis");
		messageErr.appendChild(textNode);
		
		parentElement = objet.parentNode;
		refElement = objet.nextSibling;
		if(refElement.tagName == "SPAN"){
			
			parentElement.replaceChild(refElement, refElement);
			
		}else{
			var insertElement = parentElement.insertBefore(messageErr, refElement);
		}
		objet.setAttribute('style','background-color: #ff3333');
		
	}else{
		
		var refElement = objet.nextSibling;
		
		if(refElement.tagName == "SPAN"){
			
			parentElement.removeChild(refElement);
			
		}
		
		objet.setAttribute('style','background-color: #ebebe0');
		
	}
	
	
	if(body.value.trim().length == 0 || body.value.trim().length > 4000){
		
		controleMailBool = false;
		
		messageErr = document.createElement('span');
		messageErr.setAttribute("class","msgErr");
		
		if(body.value.trim().length == 0 ){
			
			textNode = document.createTextNode("Champs Requis");
			
		}else{
			
			textNode = document.createTextNode("Vous avez dépassé le nombre maximum de caractères");
			
		}
		
		
		messageErr.appendChild(textNode);
		
		parentElement = body.parentNode;
		refElement = body;
		var previousSibling = body.previousSibling
		if(previousSibling.tagName == "SPAN"){
			
			parentElement.replaceChild(refElement, refElement);
			
		}else{
			var insertElement = parentElement.insertBefore(messageErr, refElement);
		}
		body.setAttribute('style','background-color: #ff3333');
		
	}else{
		
		var previousSibling = body.previousSibling
		
		if(previousSibling.tagName == "SPAN"){
			
			parentElement.removeChild(previousSibling);
			
		}
		
		body.setAttribute('style','background-color: #ebebe0');
		
	}
	
	
	return controleMailBool;
}

function compteLongueur(element){
	
	var nombreCaracter = element.value.length
	var nombreRestant = 4000 - nombreCaracter;
	document.getElementById('caractereRestant').innerHTML = nombreRestant;
	
}

function plusOuMoin(){
	
	var max = 1000;
	var min = 1;
    var nb = min + (max-min+1) * Math.random();
    nb = Math.trunc(nb)
    
    var i = 0;
    var game = true;
    
    while(game){
    	
    	i++;
    	var nbUser = prompt("Jeux du plus ou moin", "Entrer un chiffre entre 1 et 1000.");
    	
    	if(nbUser == nb){
    		
    		game = false;
    		
    	}else if(nbUser < nb){
    		
    		alert('Plus grand')
    		
    	}else if(nbUser > nb){
    		
    		alert('Plus petit')
    	}
    }
   alert('GAGNEEEER !!! en : ' + i + " essaie")
    
}