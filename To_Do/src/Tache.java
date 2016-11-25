import java.util.Calendar;

public abstract class Tache {

	private final int id;
	private	String nom;
	private String description;
	private Calendar echeance;
	private Categorie categorie;
	private static int compteur = 0;

	public Tache(int id, String nom, String description, Calendar echeance, Categorie categorie) {
		this.id = compteur++;
		this.nom = nom;
		this.description = description;
		this.categorie = categorie;
		if (echeance.after(Calendar.getInstance())) {
			this.echeance = echeance;
		} else {
			System.out.println("Erreur date d'echeance anterieur à la date actuelle");
		}
	}

	public Tache(int id, String nom, Calendar echeance) {
		this.id = compteur++;
		this.nom = nom;
		this.description = "";
		this.categorie = null;
		if (echeance.after(Calendar.getInstance())) {
			this.echeance = echeance;
		} else {
			System.out.println("Erreur date d'echeance anterieur à la date actuelle");
		}
	}

//	public int getJourRestant() {
//		
//	}


}
