/**
 * 
 */

function validate() {
	// On commence par effacer tous les spanErreur de la page (s'ils existent)
	deleteAllError();
	console.log('----Entrée dans validate---');

	// Initialisation de la valeur de retour
	var retour = false;

	if (verifCompanyName() || verifCompanyCity() || verifPostalCode()
			|| verifDepartment() || verifRegion() || verifSecteur()
			|| verifSiteWeb()) {
		retour = false;
	}
	console.log(retour);
	return false;
}

/**
 * Permet de vérifier que le nom de l'entreprise est bien renseigné
 * 
 * @returns boolean
 */
function verifCompanyName() {
	// Récupération du nom de l'entreprise à partir du formulaire
	var companyNameForm = document.companyForm.companyName.value;
	// Test pour vérifier que le nom a bien été récupéré
	//	console.log('Nom entreprise: ' + companyNameForm);
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
	//Test pour la récupération de la ville
	//	console.log('Ville: ' + companyCityForm);
	if (companyCityForm == null || companyCityForm == '') {
		document.getElementById("companyCity").setAttribute("style",
				"border:1px solid red");
		messErreur("companyCity", "  Veuillez renseigner ce champ");

		return false;
	} else {
		return false;
	}
}

/**
 * Vérification du code postal : s'il est renseigné et si son format est bien de
 * la forme 5 chiffres de 0 à 9 (avec prise en compte des département corses)
 * 
 * @returns {Boolean}
 */

function verifPostalCode() {
	// Récupération de la valeur du code postal à partir du formulaire
	var companyPostalCodeForm = document.companyForm.companyPostalCode.value;
	//Test pour vérification récupération code postal
	// 	console.log('Code postal: ' + companyPostalCodeForm);
	// Définition d'une expression régulière pour vérifier le code postal
	var regex = /^((0[1-9])|([1-8][0-9])|(9[0-8])|(2A)|(2B))[0-9]{3}$/;

	if (companyPostalCodeForm == null || companyPostalCodeForm == '') {
		document.getElementById("companyPostalCode").setAttribute("style",
				"border:1px solid red");
		messErreur("companyPostalCode", " Champ obligatoire!!");
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
 * Vérifie que le département a bien été reseigné
 * 
 * @returns
 */

function verifDepartment() {
	var selectElmt = document.getElementById("departments");
	var department = selectElmt.options[selectElmt.selectedIndex].value;
	if(department == "" || department == null ){
		document.getElementById("companyDepartment").setAttribute("style",
		"border:1px solid red");
		messErreur("companyDepartment", " Veuillez renseigner un département");
		return false;
	}else{
		return true;
	}
}

/**
 * Vérifie que la région a bien été renseignée
 * 
 * @returns
 */
function verifRegion() {
	var selectElmt = document.getElementById("regions");
	var region = selectElmt.options[selectElmt.selectedIndex].value;
	if(region == "" || region == null ){
		document.getElementById("regions").setAttribute("style",
		"border:1px solid red");
		messErreur("companyRegion", " Veuillez renseigner la région");
		return false;
	}else{
		return true;
	}
}

/**
 * Vérifie qu'un langage a bien été renseignée
 * 
 * @returns
 */
function verifLangage() {
	var selectElmt = document.getElementById("languages");
	var language = selectElmt.options[selectElmt.selectedIndex].value;
	if(language == "" || language == null ){
		document.getElementById("languages").setAttribute("style",
		"border:1px solid red");
		messErreur("companyLanguages", " Veuillez renseigner la région");
		return false;
	}else{
		return true;
	}
}


/**
 * Vérifie que le secteur a bien été renseigné
 * 
 * @returns
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
 * Vérifie que le site web est bien renseigné et est valide
 * 
 * @returns
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

/**
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
 * Permet d'indiquer les champs manquants à l'utilisateur (coloration de la bordure de l'élément passé en paramètre
 * et le texte à afficher)
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
 * @returns
 */
function reset() {
	deleteAllError();
}
// TODO méthode pour reset le formulaire
