/**
 * JavaScrip pour les formulaires inscription et désinscription
 */

function verifier() {      // confère demo proposition de solution javaScript
	
	var nomvideEXP = /./
	var prenomvideEXP = /./
	
	if (!nomvideEXP.test(document.all.nom.value)) {
		alert("Merci de vérifier que le champ nom n'est pas vide");
		document.all.nom.focus();
		return false;
	}
	if (!prenomvideEXP.test(document.all.prenom.value)) {
		alert("Merci de vérifier que le champ prenom n'est pas vide");
		document.all.prenom.focus();
		return false;
	}
	
}
