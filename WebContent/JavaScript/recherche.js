/**
 * 
 */


function verifSaisie(){
	var bool = new Boolean;
	
	if (document.getElementById("nom_rech").value.trim() == ""){		
		document.getElementById("nom_rech").setAttribute("style", "border:1px solid red");
		alert("Veuillez saisir un nom pour enregistrer votre recherche");
		bool = false;
	} else if (document.getElementById("nom_comp").value.trim() == "" && document.getElementById("secteur_comp").value.trim() == "" && document.getElementById("ville_comp").value.trim() == ""){
		alert("Les champs de votre recherche ne sont pas remplis!");
		bool = false;
	} else {
		bool = true;
	}
	
	return bool;
}