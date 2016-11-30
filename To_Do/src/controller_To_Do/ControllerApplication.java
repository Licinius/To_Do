package controller_To_Do;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

import model_To_Do.Categorie;
import model_To_Do.Tache;

public class ControllerApplication {
	private ArrayList<Categorie> listCategorie = new ArrayList<Categorie>();
	private ArrayList<Tache> listTache = new ArrayList<Tache>();
	
	public ControllerApplication() throws IOException, ClassNotFoundException{
		//Lecture des categories
		File fichierIn =  new File("save"+ File.separator +"categorie.ser") ;// ouverture d'un flux sur un fichier
		FileInputStream fichierInStream = new FileInputStream(fichierIn);
		ObjectInputStream ois=null;
		if(fichierInStream.available()>0){
			ois = new ObjectInputStream(fichierInStream);
			while(fichierInStream.available() > 0){
				listCategorie.add((Categorie)ois.readObject());
			}
			ois.close();
		}

		
		//Lecture des taches
		fichierIn =  new File("save"+ File.separator +"tache.ser") ;// ouverture d'un flux sur un fichier
		fichierInStream = new FileInputStream(fichierIn);
		if(fichierInStream.available()>0){
			ois = new ObjectInputStream(fichierInStream);
			while(fichierInStream.available() > 0){
				listTache.add((Tache)ois.readObject());
			}
			ois.close();
		}
		
		
	}
	/**
	 * Cette fonction crée un nouveau fichier contenant les nouvelles catégories
	 * @param categorie
	 * 	La categorie que l'on veut créer
	 * @throws IOException
	 */
	public void createCategorie(Categorie categorie) throws IOException{
		if(categorie!=null){
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
	}
	
	/**
	 * Cette fonction crée un nouveau fichier sans la categorie passé en paramètre
	 * @param categorie 
	 * 	La categorie que l'on veut supprimer
	 * @throws IOException
	 */
	public void deleteCategorie(Categorie categorie) throws IOException{
		if(categorie!=null){
			listCategorie.remove(categorie);
			File fichierOut =  new File("save"+ File.separator +"categorie.ser") ;// ouverture d'un flux sur un fichier
			FileOutputStream fichierOutStream = new FileOutputStream(fichierOut);
			ObjectOutputStream oos=null;
			oos = new ObjectOutputStream(fichierOutStream);
			for(Categorie cat : this.listCategorie){
				oos.writeObject(cat);
			}
			oos.close();
			for(Tache t : this.listTache){
				if(t.getCategorie() != null){
					if(t.getCategorie().equals(categorie))	t.setCategorie(null);
				}
			}
			updateTache();
		}
	}
	
	private void updateTache() throws IOException{
		File fichierOut =  new File("save"+ File.separator +"tache.ser") ;// ouverture d'un flux sur un fichier
		FileOutputStream fichierOutStream = new FileOutputStream(fichierOut);
		ObjectOutputStream oos=null;
		oos = new ObjectOutputStream(fichierOutStream);
		for(Tache t : this.listTache){
			oos.writeObject(t);
		}
		oos.close();
	}
	public void createTache(Tache tache) throws IOException{
		if(tache !=null){
			listTache.add(tache);
			File fichierOut =  new File("save"+ File.separator +"tache.ser") ;// ouverture d'un flux sur un fichier
			FileOutputStream fichierOutStream = new FileOutputStream(fichierOut);
			ObjectOutputStream oos=null;
			oos = new ObjectOutputStream(fichierOutStream);
			for(Tache t : this.listTache){
				oos.writeObject(t);
			}
			oos.close();
		}
	}
	
	public void deleteTache(Tache tache) throws IOException{
		if(tache!=null){
			listTache.remove(tache);
			File fichierOut =  new File("save"+ File.separator +"tache.ser") ;// ouverture d'un flux sur un fichier
			FileOutputStream fichierOutStream = new FileOutputStream(fichierOut);
			ObjectOutputStream oos=null;
			oos = new ObjectOutputStream(fichierOutStream);
			for(Tache t : this.listTache){
				oos.writeObject(t);
			}
			oos.close();
		}
	}
	
	public void printCategorie(){
		for(Categorie cat : this.listCategorie){
			System.out.println(cat.toString());
		}
		System.out.println();
	}
	
	public void deleteAll() throws FileNotFoundException{
		File fichierOut =  new File("save"+ File.separator +"tache.ser") ;// ouverture d'un flux sur un fichier
		FileOutputStream fichierOutStream = new FileOutputStream(fichierOut);
		
		fichierOut =  new File("save"+ File.separator +"categorie.ser") ;// ouverture d'un flux sur un fichier
		fichierOutStream = new FileOutputStream(fichierOut);
		
	}
	
	public void triSimple() {
		for (int i = 0; i < listTache.size()-1; i++) {
			for (int j = i+1; j < listTache.size(); j++) {
				if (listTache.get(i).getJourRestant() > listTache.get(j).getJourRestant()) {
					Collections.swap(listTache, i, j);
				}
			}
		}
	}
	public void printTache() {
		for(Tache t : this.listTache){
			System.out.println(t.toString());
		}
		System.out.println();
		
	}
}
