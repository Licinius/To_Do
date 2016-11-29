package model_To_Do;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;

import exception_To_Do.ExceptionTacheAnterieur;

public abstract class Tache {

	private int id;
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
	
	protected void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
		this.id = ois.readInt();
		this.description = ois.readUTF();
		this.nom = ois.readUTF();
		this.echeance = (Calendar)ois.readObject();
		this.categorie=(Categorie)ois.readObject();
	}
	
	protected void writeObject(ObjectOutputStream oos) throws IOException{
		oos.writeInt(id);
		oos.writeUTF(description);
		oos.writeUTF(nom);
		oos.writeObject(echeance);
		oos.writeObject(categorie);
	}


}
