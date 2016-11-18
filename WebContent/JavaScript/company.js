/**
 * 
 */

function validate() {
	// On commence par effacer tous les spanErreur de la page (s'ils existent)
	deleteAllError();

	var retour = false;
	var companyNameForm = document.companyForm.companyName.value;
	var companyCityForm = document.companyForm.companyCity.value;
	var companyPostalCodeForm = document.companyForm.companyPostalCode.value;
	var companySectorForm = document.companyForm.companySector.value;

	console.log('Nom entreprise: ' + companyNameForm);
	console.log('Ville: ' + companyCityForm);
	// console.log('Code postal: ' + companyPostalCodeForm);
	// console.log(pseudo);

	if (companyNameForm == null || companyNameForm == '') {
		document.getElementById("companyName").setAttribute("style",
				"border:1px solid red");
		messErreur("companyName", "  Champ obligatoire!!");
		retour = false;
	} else {
		retour = true;
	}
	if (companyCityForm == null || companyCityForm == '') {
		document.getElementById("companyCity").setAttribute("style",
				"border:1px solid red");
		messErreur("companyCity", "  Champ obligatoire!!");
		retour = false;
	}
	if (companyPostalCodeForm == null || companyPostalCodeForm == '') {
		document.getElementById("companyPostalCode").setAttribute("style",
				"border:1px solid red");
		messErreur("companyPostalCode", "  Champ obligatoire!!");
		retour = false;
	} else if (!verifPostalCode(companyPostalCodeForm)) {
		// messErreur("companyPostalCode", " Le code postal n'est pas au bon
		// format (5 chiffres)");
		retour = false;
	}
	if (companySectorForm == null || companySector == '') {
		document.getElementById("companySector").setAttribute('style',
				'border:1px solid red');
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
function verifCompanyName(companyNameForm) {
	if (companyNameForm == null || companyNameForm == '') {
		document.getElementById("companyName").setAttribute("style",
				"border:1px solid red");
		messErreur("companyName", "  Champ obligatoire!");
		return false;
	} else {
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
function verifCompanyCity(companyCityForm) {
	if (companyCityForm == null || companyCityForm == '') {
		document.getElementById("companyCity").setAttribute("style",
				"border:1px solid red");
		messErreur("companyCity", "  Champ obligatoire!!");
		return false;
	}
}

/**
 * Vérification du code postal : s'il est renseigné et si son format est bien de
 * la forme 5 chiffres de 0 à 9 (avec prise en compte des département corses)
 * 
 * @param companyPostalCodeForm
 * @returns {Boolean}
 */

function verifPostalCode(companyPostalCodeForm) {
	var regex = /^((0[1-9])|([1-8][0-9])|(9[0-8])|(2A)|(2B))[0-9]{3}$/;
	// if (companyPostalCodeForm == null || companyPostalCodeForm == ''){
	// document.getElementById("companyPostalCode").setAttribute("style",
	// "border:1px solid red");
	// messErreur("companyPostalCode", " Champ obligatoire!!");
	// return false;
	if (!regex.test(companyPostalCodeForm)) {
		messErreur("companyPostalCode",
				"  Le code postal n'est pas au bon format (5 chiffres)");
		return false;
	} else {
		return true;
	}
}

/***
 * Permet de supprimer tous les spanErreurs de la page 
 */
function deleteAllError() {
	var link = document.querySelectorAll('.spanErreur');
	if (link != null) {
		for (i = 0; i < link.length; i++) {
			var element = link.item(i);
			element.parentNode.removeChild(element);
		}
	}
}

/**
 * Fonction qui permet de supprimer le span erreur associée à l'élement passé en entrée
 * Elle est utilisée lors de la perte de focus du champ en paramètre
 * @param element
 */
function changeElement(element) {
	var link = document.querySelector('.spanErreur');
	console.log(link);
	link.parentNode.removeChild(link);
	console.log(element);
	var idel = element.id;
	console.log(idel);
	// TODO switch pour les différentes valeurs que pourra prendre id
	var elem = document.getElementById(idel);
	elem.setAttribute("style", "border:1px solid");
}

function messErreur(idElement, text) {
	var afficheErreur = document.createElement('span');
	afficheErreur.setAttribute('class', 'spanErreur')
	afficheErreur.setAttribute("style", "color : red");
	var txt = document.createTextNode(text);
	afficheErreur.appendChild(txt);
	var nodeParent = document.getElementById(idElement);
	nodeParent.parentNode.insertBefore(afficheErreur, nodeParent.nextSibling);
}

function reset() {
	deleteAllError();
}
//TODO méthode pour reset le formulaire