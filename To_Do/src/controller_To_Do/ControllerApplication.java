package controller_To_Do;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


import model_To_Do.Categorie;

public class ControllerApplication {
	private ArrayList<Categorie> listCategorie = new ArrayList<Categorie>();
	private ArrayList listTache = new ArrayList();
	
	public ControllerApplication() throws IOException, ClassNotFoundException{
		File fichierIn =  new File("save"+ File.separator +"categorie.ser") ;// ouverture d'un flux sur un fichier
		FileInputStream fichierInStream = new FileInputStream(fichierIn);
		ObjectInputStream ois=null;
		ois = new ObjectInputStream(fichierInStream);
		while(fichierInStream.available() > 0){
			listCategorie.add((Categorie)ois.readObject());
		}
		ois.close();		
	}
	/**
	 * Cette fonction crée un nouveau fichier contenant les nouvelles catégories
	 * @param categorie
	 * 	La categorie que l'on veut créer
	 * @throws IOException
	 */
	public void createCategorie(Categorie categorie) throws IOException{
		listCategorie.add(categorie);
		File fichierOut =  new File("save"+ File.separator +"categorie.ser") ;// ouverture d'un flux sur un fichier
		FileOutputStream fichierOutStream = new FileOutputStream(fichierOut);
		ObjectOutputStream oos=null;
		oos = new ObjectOutputStream(fichierOutStream);
		for(Categorie cat : this.listCategorie){
			oos.writeObject(cat);
		}
		oos.close();

	}
	
	/**
	 * Cette fonction crée un nouveau fichier sans la categorie passé en paramètre
	 * @param categorie 
	 * 	La categorie que l'on veut supprimer
	 * @throws IOException
	 */
	public void deleteCategorie(Categorie categorie) throws IOException{
		listCategorie.remove(categorie);
		File fichierOut =  new File("save"+ File.separator +"categorie.ser") ;// ouverture d'un flux sur un fichier
		FileOutputStream fichierOutStream = new FileOutputStream(fichierOut);
		ObjectOutputStream oos=null;
		oos = new ObjectOutputStream(fichierOutStream);
		for(Categorie cat : this.listCategorie){
			oos.writeObject(cat);
		}
		oos.close();
	}
	
	public void printCategorie(){
		for(Categorie cat : this.listCategorie){
			System.out.println(cat.toString());
		}
		System.out.println();
	}
}
