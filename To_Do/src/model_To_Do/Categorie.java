package model_To_Do;
import java.io.Serializable;

public class Categorie implements Serializable {

	private static final long serialVersionUID = 1L;
	private int identifiant;
	private static int compteur;
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
		return "Categorie : [identifiant=" + identifiant + ", nom=" + nom + "]";
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

	public static void setCompteur(int i) {
		compteur=i;
		
	}

	public static int getCompteur() {
		return compteur;
	}
	
}