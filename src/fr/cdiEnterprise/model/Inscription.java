package fr.cdiEnterprise.model;

public class Inscription {
	
	private static final long serialVersionUID = 1L;
	
	private int reference;
	private String statut;
	private String nom;
	private String prenom;
	private String email;
	private String motDePasse;
	private String confirmation;
	/**
	 * @param reference
	 * @param statut
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param motDePasse
	 * @param confirmerPasse
	 */
	public Inscription(int reference, String statut, String nom, String prenom, String email, String motDePasse,
			String confirmerPasse) {
		super();
		this.reference = reference;
		this.statut = statut;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.motDePasse = motDePasse;
		this.confirmation = confirmerPasse;
	}
	public int getReference() {
		return reference;
	}
	public void setReference(int reference) {
		this.reference = reference;
	}
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMotDePasse() {
		return motDePasse;
	}
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	public String getConfirmation() {
		return confirmation;
	}
	public void setConfirmation(String confirmerPasse) {
		this.confirmation = confirmerPasse;
	}
	@Override
	public String toString() {
		return "Inscription [reference=" + reference + ", statut=" + statut + ", nom=" + nom + ", prenom=" + prenom
				+ ", email=" + email + ", motDePasse=" + motDePasse + ", confirmerPasse=" + confirmation + "]";
	}
	
	

}
