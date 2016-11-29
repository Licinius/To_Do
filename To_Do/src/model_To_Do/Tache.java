package model_To_Do;
import java.util.Calendar;

import exception_To_Do.*;
public abstract class Tache {

	private final int id;
	private	String nom;
	private String description;
	private Calendar echeance;
	private Categorie categorie;
	private static int compteur = 0;

	public Tache(int id, String nom, String description, Calendar echeance, Categorie categorie) throws ExceptionTacheAnterieur{
		this.id = compteur++;
		this.nom = nom;
		this.description = description;
		this.categorie = categorie;
		if (echeance.after(Calendar.getInstance()))	this.echeance = echeance;
		else	throw new ExceptionTacheAnterieur(echeance.toString());
		
	}

	public Tache(int id, String nom, Calendar echeance) throws ExceptionTacheAnterieur{
		this.id = compteur++;
		this.nom = nom;
		this.description = "";
		this.categorie = null;
		if (echeance.after(Calendar.getInstance()))	this.echeance = echeance;
		else	throw new ExceptionTacheAnterieur(echeance.toString());
	}

public int getJourRestant() {
	long diff = echeance.getTimeInMillis() - System.currentTimeMillis();
	return (int)  (diff/86400000); // Diviser par le nombre de milliseconde dans une journee
	 
}


}
