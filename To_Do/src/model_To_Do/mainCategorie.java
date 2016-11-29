package ModelTo_Do;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class mainCategorie {

	public static void main(String[] args) {
		
		File fichier =  new File("categorie.ser") ;

		 // ouverture d'un flux sur un fichier
		ObjectOutputStream oos=null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(fichier));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
				
		 // cr�ation d'un objet � s�rializer
		Categorie m =  new Categorie("Surcouf") ;
		Categorie m2 = new Categorie ("Perso");

		 // s�rialization de l'objet
		try {
			oos.writeObject(m) ;
			oos.writeObject(m2);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		
		
		/*Serialization d'une categorie*/
		File fichierOut =  new File("categorie.ser") ;

		 // ouverture d'un flux sur un fichier
		ObjectInputStream ois=null;
		try {
			ois = new ObjectInputStream(new FileInputStream(fichierOut));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		 // d�s�rialization de l'objet
		Categorie mOut = null;
		Categorie mOut2 = null;
		try {
			mOut = (Categorie)ois.readObject();
			mOut2 = (Categorie)ois.readObject();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(mOut) ;
		System.out.print(mOut2);

	}

}
