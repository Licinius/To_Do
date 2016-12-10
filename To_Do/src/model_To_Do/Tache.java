package model_To_Do;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;

import exception_To_Do.ExceptionTacheAnterieur;

public abstract class Tache implements Serializable {
	private static final long serialVersionUID = 5421937201739723605L;
	private int id;
	private	String nom;
	private String description;
	private Calendar echeance;
	private Categorie categorie;
	private boolean retard;
	private boolean termine;
	private Calendar dateDebut;
	
	private static int compteur = 0;

	public Tache(String nom, String description, Calendar echeance, Categorie categorie) throws ExceptionTacheAnterieur{
		this.id = compteur++;
		this.nom = nom;
		this.description = description;
		this.categorie = categorie;
		this.retard = false;
		this.termine = false;
		dateDebut=  Calendar.getInstance();
		if (echeance.after(Calendar.getInstance()))	this.echeance = echeance;
		else	throw new ExceptionTacheAnterieur(echeance.toString());
		
	}

	public Tache(int id, String nom, Calendar echeance) throws ExceptionTacheAnterieur{
		this.id = compteur++;
		this.nom = nom;
		this.description = "";
		this.categorie = null;
		this.retard = false;
		this.termine = false;
		dateDebut=  Calendar.getInstance();
		if (echeance.after(Calendar.getInstance()))	this.echeance = echeance;
		else	throw new ExceptionTacheAnterieur(echeance.toString());
	}
	/**
	 * Permet de connaitre en le nombre de jours restants avant l'échéance
	 * @return un entier correspondant au nombre de jours restants
	 */
	public int getJourRestant() {
		long diff = echeance.getTimeInMillis() - System.currentTimeMillis();
		return (int)  (diff/86400000); // Diviser par le nombre de milliseconde dans une journee
		 
	}
	
	public Calendar getDateDebut(){
		return dateDebut;
	}
	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Calendar getEcheance() {
		return echeance;
	}

	public void setEcheance(Calendar echeance) {
		this.echeance = echeance;
	}

	public static int getCompteur() {
		return compteur;
	}

	public static void setCompteur(int compteur) {
		Tache.compteur = compteur;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
	/**
	 * Permet de connaitre si la tache est en retard
	 * @return
	 * 	true si la tache est en retard 
	 */
	public boolean isRetarded(){
		return this.getEcheance().before(Calendar.getInstance());
	}
	
	public String toString() { 
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		String formatted = format1.format(echeance.getTime());
		return "Tache [id=" + id + ", nom=" + nom + ", description=" + description + ", echeance=" + formatted
				+ ", categorie=" + categorie.getNom() + "]";
	}
	
	public boolean isRetard() {
		return retard;
	}

	public void setRetard(boolean retard) {
		this.retard = retard;
	}

	public boolean isTermine() {
		return termine;
	}

	public void setTermine(boolean termine) {
		this.termine = termine;
	}
	/**
	 * Retourne la prochaine échéance d'une date
	 * @return
	 * 	Un objet calendar correspond à la prochaine échéance d'une date
	 */
	public Calendar getNextEcheance(){
		return this.echeance;
	}
	/**
	 * Retourne une map (ou tableau associatif) d'un objet Tache
	 * @return
	 * 	Une LinkedHashMap contenant ["nom","Echeance","Description","Categorie"]
	 */
	public Map <String,String> getInformation() { //recupère les informations pour les affichés au bon endroit
		SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
		Map<String,String> str = new LinkedHashMap<String,String>(); //LinkedHashMap sinon les maps en général font des tries
		str.put("Nom", this.nom);
		str.put("Echeance",format1.format(echeance.getTime()));
		str.put("Categorie",this.categorie == null ? "Sans Catégorie" : categorie.getNom());
		str.put("Description",this.description);
		return str;
	}
	
	/**
	 * Deux taches sont équivalentes quand leurs identifiants sont identiques
	 * @param t
	 * 	Tache que l'on souhaite vérifier l'équivalence
	 * @return
	 * 	true si les taches sont équivalentes
	 */
	public boolean equals(Tache t){
		return t.id == this.id;
	}

}
