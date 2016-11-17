/**
 * 
 */


function validate(){
	
	var retour = false;
	var companyNameForm = document.companyForm.companyName.value;
	var companyCityForm = document.companyForm.companyCity.value;
	var companyPostalCodeForm = document.companyForm.companyPostalCode.value;
	var companySectorForm = document.companyForm.companySector.value;

	console.log('Nom entreprise: ' + companyNameForm);
	console.log('Ville: ' + companyCityForm);
//	console.log(confirmation);
//	console.log(pseudo);
	
	if (companyNameForm == null || companyNameForm == ''){
		document.getElementById("companyName").setAttribute("style", "border:1px solid red");
		messErreur("companyName", "  Champ obligatoire!!");
		return retour;
	}else{
		retour = true;
	}
	if (companyCityForm == null || companyCityForm == ''){
		document.getElementById("companyCity").setAttribute("style", "border:1px solid red");
		messErreur("companyCity", "  Champ obligatoire!!");
		 retour =false;
	}
	if (companyPostalCodeForm == null || companyPostalCodeForm == ''){
		document.getElementById("companyPostalCode").setAttribute("style", "border:1px solid red");
		messErreur("companyPostalCode", "  Champ obligatoire!!");
		return false;
	}else if (!verifPostalCode(companyPostalCodeForm)){
			messErreur("companyPostalCode", "  Le code postal n'est pas au bon format (5 chiffres)");
			return false;
		}
	if (companySectorForm == null || companySector == ''){
		document.getElementById("companySector").setAttribute('style', 'border:1px solid red');
		
		
	}
	console.log(retour);
	return retour;
	
	
}


/**
 * Permet de vérifier que le nom de l'entreprise est bien renseigné
 * 
 * @param name
 * @returns boolean
 */
function verifCompanyName(companyNameForm){
	
	if (companyNameForm == null || companyNameForm == ''){
		document.getElementById("companyName").setAttribute("style", "border:1px solid red");
		messErreur("companyName", "  Champ obligatoire!");
		return false;
	}else{
		return true;
	}
}

/**
 * Permet de vérifier si la ville de l'entreprise a bien été renseignée
 * 
 * @param city
 * @return boolean
 * 
 */
function verifCompanyCity(companyCityForm){
	
	if (companyCityForm == null || companyCityForm == ''){
		document.getElementById("companyCity").setAttribute("style", "border:1px solid red");
		messErreur("companyCity", "  Champ obligatoire!!");
		 return false;
	}
}


/**Vérification du code postal : s'il est renseigné et si son format est bien de la forme 5 chiffres de 0 à 9 
 * (avec prise en compte des département corses)
 * 
 * @param companyPostalCodeForm
 * @returns {Boolean}
 */

function verifPostalCode(companyPostalCodeForm){
	var regex = /^((0[1-9])|([1-8][0-9])|(9[0-8])|(2A)|(2B))[0-9]{3}$/;
	
	if (companyPostalCodeForm == null || companyPostalCodeForm == ''){
		document.getElementById("companyPostalCode").setAttribute("style", "border:1px solid red");
		messErreur("companyPostalCode", "  Champ obligatoire!!");
		return false;
		
	}else if (!regex.test(companyPostalCodeForm)){
			messErreur("companyPostalCode", "  Le code postal n'est pas au bon format (5 chiffres)");
			return false;
	}else{
		return true;
	}
}
	




function supprimer(element){
	var link = document.querySelector('.spanErreur');
	console.log(link);
	link.parentNode.removeChild(link);
//	var idElement = "\"" + idElement + "\"";
	console.log(element);
	var idel = element.id;
	console.log(idel);
	id = "\"\#"+idel+"\"";
	console.log(id);
	var elem = document.querySelector(id);
	elem.setAttribute("style", "border:1px solid");
	
}



function messErreur(idElement, text){	
	var afficheErreur = document.createElement('span');
	afficheErreur.setAttribute ('class', 'spanErreur')
	afficheErreur.setAttribute ("style", "color : red");
	 var txt = document.createTextNode(text);
	 afficheErreur.appendChild(txt);
	var nodeParent = document.getElementById(idElement);
	nodeParent.parentNode.insertBefore(afficheErreur, nodeParent.nextSibling);
}


//TODO méthode pour reset le formulaire