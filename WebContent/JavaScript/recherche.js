/**
 * 
 */


function verifSaisie(){
	var toto= document.getElementsByName("nom_rech")[0].value;
	var bool = new Boolean;
	
	if (toto==""){
		alert("Veuillez saisir un nom pour enregistrer votre recherche")
		document.getElementsByName("nom_rech")[0];
		bool = false;
	}
	else {
		bool = true;
	}
	return bool;
}