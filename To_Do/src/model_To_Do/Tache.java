package model_To_Do;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

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
	
	private static int compteur = 0;

	public Tache(String nom, String description, Calendar echeance, Categorie categorie) throws ExceptionTacheAnterieur{
		this.id = compteur++;
		this.nom = nom;
		this.description = description;
		this.categorie = categorie;
		this.retard = false;
		this.termine = false;
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
		if (echeance.after(Calendar.getInstance()))	this.echeance = echeance;
		else	throw new ExceptionTacheAnterieur(echeance.toString());
	}

	public int getJourRestant() {
		long diff = echeance.getTimeInMillis() - System.currentTimeMillis();
		return (int)  (diff/86400000); // Diviser par le nombre de milliseconde dans une journee
		 
	}
	
	protected void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
		compteur = ois.readInt();
		this.id = compteur++;
		this.description = ois.readUTF();
		this.nom = ois.readUTF();
		this.echeance = (Calendar)ois.readObject();
		this.categorie=(Categorie)ois.readObject();
		this.retard = ois.readBoolean();
		this.termine = ois.readBoolean();
	}
	
	protected void writeObject(ObjectOutputStream oos) throws IOException{
		oos.writeInt(id);
		oos.writeUTF(description);
		oos.writeUTF(nom);
		System.out.println("Je plante là");
		oos.writeObject(echeance);
		oos.writeObject(categorie);
		oos.writeBoolean(termine);
		oos.writeBoolean(retard);
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
	public boolean isRetarded(){
		return this.getEcheance().before(Calendar.getInstance());
	}
	public String toString() {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		String formatted = format1.format(echeance.getTime());
		return "Tache [id=" + id + ", nom=" + nom + ", description=" + description + ", echeance=" + formatted
				+ ", categorie=" + categorie.getNom() + "]";
	}
	
	public String toStringPourTesterPourLesJLabels() {
		SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
		String affichage = nom + "\t" + description + "\t" + format1.format(echeance.getTime()) + "\t";
		affichage += this.categorie == null ? "Sans Catégorie" : categorie.getNom();
		return affichage;
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

	public Map <String,String> getInformation() { //recupère les informations pour les affichés au bon endroit
		SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
		Map<String,String> str = new LinkedHashMap<String,String>(); //LinkedHashMap sinon les maps en général font des tries
		str.put("Nom", this.nom);
		str.put("Echeance",format1.format(echeance.getTime()));
		str.put("Categorie",this.categorie == null ? "Sans Catégorie" : categorie.getNom());
		str.put("Description",this.description);
		return str;
	}
	
	public boolean equals(Tache t){
		return t.id == this.id;
	}

}
