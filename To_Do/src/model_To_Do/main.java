package model_To_Do;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import controller_To_Do.*;
public class main {

	public static void main(String[] args) {
			ControllerApplication cA = null;
			try {
				cA = new ControllerApplication();
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Categorie cat = new Categorie(2,"test");
			try {
				cA.createCategorie(cat);
			} catch (IOException e) {
				System.out.println("Erreur");
				e.printStackTrace();
			}
			cA.printCategorie();
			try {
				cA.deleteCategorie(cat);
				cA.createCategorie(null);
			} catch (IOException e) {
				System.out.println("Erreur");
				e.printStackTrace();
			}
			cA.printCategorie();

	}

}
