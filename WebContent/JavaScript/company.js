/**
 * JavaScript permettant de vérifier les formulires 
 */

function validate() {
	// On commence par effacer tous les spanErreur de la page (s'ils existent)
	deleteAllError();
	console.log('----Entrée dans validate---');
	// Initialisation de la valeur de retour
	var retour = false;
	
	if (verifCompanyName() & verifCompanyCity() & verifPostalCode()
			& verifSecteur() & verifSiteWeb() & verifLangage() ) {
		retour = true;
	} else {
		retour = false;
	}
	return retour;
}

/**
 * Permet de vérifier que le nom de l'entreprise est bien renseigné
 * 
 * @returns boolean
 */
function verifCompanyName() {
	// Récupération du nom de l'entreprise à partir du formulaire
	deleteAllError();
	var companyNameForm = document.companyForm.companyName.value;
	console.log(companyNameForm);
	
	// Test pour vérifier que le nom a bien été récupéré
	// console.log('Nom entreprise: ' + companyNameForm);
	if (companyNameForm == null || companyNameForm == '') {
		document.getElementById("companyName").setAttribute("style",
				"border:1px solid red");
		messErreur("companyName", "  Veuillez renseigner ce champ");
		return false;
	} else {
		return true;
	}
}

/**
 * Permet de vérifier si la ville de l'entreprise a bien été renseignée
 * 
 * @return boolean
 * 
 */
function verifCompanyCity() {
	// Récupération du nom de la ville à partir du formulaire
	var companyCityForm = document.companyForm.companyCity.value;
	// Test pour la récupération de la ville
	// console.log('Ville: ' + companyCityForm);
	if (companyCityForm == null || companyCityForm == '') {
		document.getElementById("companyCity").setAttribute("style",
				"border:1px solid red");
		messErreur("companyCity", "  Veuillez renseigner ce champ");

		return false;
	} else {
		return true;
	}
}

/**
 * Vérification du code postal : s'il est renseigné et si son format est bien de
 * la forme 5 chiffres de 0 à 9 (avec prise en compte des départements corses)
 * 
 * @returns {Boolean}
 * 
 */

function verifPostalCode() {
	// Récupération de la valeur du code postal à partir du formulaire
	var companyPostalCodeForm = document.companyForm.companyPostalCode.value;
	// Test pour vérification récupération code postal
	// console.log('Code postal: ' + companyPostalCodeForm);
	// Définition d'une expression régulière pour vérifier le code postal
	var regex = /^((0[1-9])|([1-8][0-9])|(9[0-8])|(2A)|(2B))[0-9]{3}$/;

	if (companyPostalCodeForm == null || companyPostalCodeForm == '') {
		document.getElementById("companyPostalCode").setAttribute("style",
				"border:1px solid red");
		messErreur("companyPostalCode", " Veuillez renseigner ce champ!");
		return false;
	} else if (!regex.test(companyPostalCodeForm)) {
		messErreur("companyPostalCode",
				"  Le code postal n'est pas au bon format (5 chiffres)");
		return false;
	} else {
		return true;
	}
}


/**
 * Vérifie qu'un langage a bien été renseignée
 * 
 * @returns boolean
 */

function verifLangage() {
	var selectElmt = document.getElementById("languages");
	var element = selectElmt.options.lenght;
	console.log(element);
	if (element == undefined) {
		return true;
	} else {
		document.getElementById("languages").setAttribute("style",
				"border:1px solid red");
		messErreur("languages", " Veuillez selectionner un langage informatique");
		return false;
	}
}

/**
 * Vérifie que le secteur a bien été renseigné
 * 
 * @returns boolean
 */
function verifSecteur() {
	// Récupération du secteur renseigné dans le formulaire
	var companySectorForm = document.companyForm.companySector.value;

	if (companySectorForm == null || companySectorForm == '') {
		document.getElementById("companySector").setAttribute('style',
				'border:1px solid red');
		messErreur("companySector", "   Veuillez renseigner ce champ")
		return false;
	} else {
		return true;
	}
}

/**
 * Vérifie que le site web est bien renseigné 
 * 
 * @returns boolean
 */
function verifSiteWeb() {
	// Récupération du site Web renseigné dans le formulaire
	var companyWebSiteForm = document.companyForm.companyWebSite.value;
	// Test pour vérifier que l'on a bien récupérer le site Web
	// console.log("site web: " + companyWebSiteForm);
	if (companyWebSiteForm == null || companyWebSiteForm == '') {
		document.getElementById("companyWebSite").setAttribute('style',
				'border:1px solid red');
		messErreur("companyWebSite", "   Veuillez renseigner ce champ")
		return false;
	} else {
		return true;
	}
}

///**
// * Permet de vérifier que le nom de l'entreprise a rechercher est bien renseigné
// * Utilisé dans la jsp companySearchList
// * 
// * @returns company
// */
//function selectCompany() {
//	console.log("---- Dans méthode --- ");
//	var selectElmt = document.getElementById("companiesSelec");
//	var company = selectElmt.options[selectElmt.selectedIndex].value;
//	console.log(company);
//	return company;
//}


/**
 * Permet de supprimer tous les spanErreurs de la page
 * @author: Anaïs
 * @version: 22/11/2016
 * 
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
 * Fonction qui permet de supprimer le span erreur associée à l'élement passé en
 * entrée Elle est utilisée lors de la perte de focus du champ en paramètre
 * 
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

/**
 * Permet d'indiquer les champs manquants à l'utilisateur (coloration de la
 * bordure de l'élément passé en paramètre) et le texte à afficher
 * 
 * @param idElement
 * @param text
 * @returns
 */
function messErreur(idElement, text) {
	var afficheErreur = document.createElement('span');
	afficheErreur.setAttribute('class', 'spanErreur')
	afficheErreur.setAttribute("style", "color : red");
	var txt = document.createTextNode(text);
	afficheErreur.appendChild(txt);
	var nodeParent = document.getElementById(idElement);
	nodeParent.parentNode.insertBefore(afficheErreur, nodeParent.nextSibling);
}

/**
 * Pour reset le formulaire
 * 
 * @returns
 */
function reset() {
	deleteAllError();
}

