
public class Categorie {
	private int identifiant;
	private String nom;

	/**
	 * Constructeur d'une catégorie avec un nom
	 * @param nom
	 */
	public Categorie(String nom) {
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
	 * Modifier le nom de la catégorie par le nom passé en paramètre
	 * @param nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/**
	 * ToString d'une catégorie
	 */
	public String toString() {
		return "Categorie [identifiant=" + identifiant + ", nom=" + nom + "]";
	}

	
}