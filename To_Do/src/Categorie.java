import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Categorie implements Serializable {

	private static final long serialVersionUID = 1L;
	private int identifiant;
	private String nom;

	/**
	 * Constructeur d'une cat�gorie avec un nom
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
	 * Modifier le nom de la cat�gorie par le nom pass� en param�tre
	 * @param nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/**
	 * ToString d'une cat�gorie
	 */
	public String toString() {
		return "Categorie [identifiant=" + identifiant + ", nom=" + nom + "]";
	}

	private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
		this.identifiant = ois.readInt();
		this.nom = ois.readUTF();
	}
	
	private void writeObject(ObjectOutputStream oos) throws IOException{
		oos.writeInt(identifiant);
		oos.writeUTF(nom);
	}
	
}