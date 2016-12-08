package model_To_Do;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Categorie implements Serializable {

	private static final long serialVersionUID = 1L;
	private int identifiant;
	private static int compteur = 0;
	private String nom;

	/**
	 * Constructeur d'une cat�gorie avec un nom
	 * @param nom
	 */
	public Categorie(String nom) {
		this.identifiant = compteur++;
		this.nom = nom;
	}
	
	/**
	 * Recuperation du nom de la Categorie
	 * @return String : this.nom 
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * Modifier le nom de la catégorie par le nom pass� en param�tre
	 * @param nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public int getIdentifiant() {
		return identifiant;
	}

	/**
	 * ToString d'une catégorie
	 */
	public String toString() {
		return "Categorie [identifiant=" + identifiant + ", nom=" + nom + "]";
	}

	/**
	 * Permet de recreer l'objet contenu dans le stream envoyé en paramètre
	 * @param ois
	 * 	Stream de lecture de l'objet
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
		compteur = ois.readInt();
		this.identifiant = compteur++;
		this.nom = ois.readUTF();
	}
	/**
	 * Permet d'écrire l'objet dans le stream envoyé en paramètre
	 * @param oos
	 * 	Stream d'écriture de l'objet
	 * @throws IOException
	 */
	private void writeObject(ObjectOutputStream oos) throws IOException{
		oos.writeInt(identifiant);
		oos.writeUTF(nom);
	}
	/**
	 * Deux catégories sont équivalentes quand leurs identifiants sont identiques
	 * @param c
	 * 	Categorie que l'on souhaite vérifier l'équivalence
	 * @return
	 * 	true si les catégories sont équivalentes
	 */
	public boolean equals(Categorie c){
		return c.identifiant == this.identifiant;
	}
	
}