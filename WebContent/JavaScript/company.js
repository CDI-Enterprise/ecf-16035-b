/**
 * 
 */


function validate(){
	
	var companyNameForm = document.companyForm.companyName.value;
//	var confirmation = document.formulaire.confirmationMdP.value;
//	var pseudo = document.formulaire.pseudonyme.value;
	console.log(companyNameForm);
//	console.log(confirmation);
//	console.log(pseudo);
	
	if (companyNameForm == null || companyNameForm == ''){
//		alert('attention')
		inserer("companyName", 'Nom de l\'entreprise obligatoire');
		return false;
	}
//	if (pseudo == null || pseudo == ""){
//		console.log('Attention');
//		inserer('obligatoire', 'Pseudonyme obligatoire');
//		return false;
//	}
//	if (companyName != confirmation){
//		alert("Ce n'est pas le même mot de passe!!!!");
//		document.formulaire.motDePasse.value ="";
//		document.formulaire.confirmationMdP.value ="";
//		return false;
//	}else{
//		alert('envoyer');
		
	return false;	 
//	}

}

function supprimer(){
	var link = document.querySelector('obligatoire');
	console.log(link);
	link.parentNode.removeChild(link);
	
}


function inserer(idElement,text){	
		var idElement = '\'' +  idElement + '\'';
		console.log(idElement);
		var messError = document.createElement('p');				//création du nouvel élément
		messError.setAttribute('name', 'spanError');						//création des attributs
		messError.innerText= text;	
		document.getElementById(idElement).appendChild(messError);	
	}

//function messageErreur(txt) {
//	
//	var afficheErreur = document.createElement('span');
//	afficheErreur.setAttribute ("name", "spanErreur")
//	afficheErreur.setAttribute ("style", "display : block; color : red");
//	afficheErreur.innerText = txt;
//	document.getElementById("formulaire").appendChild(afficheErreur);
//TODO méthode validate pour envoie formulaire
//
//TODO méthode pour reset le formulaire