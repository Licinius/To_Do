package view_To_Do;

import javax.swing.JButton;

import model_To_Do.Tache;

public class SuppressionBouton  extends JButton{
	private Tache t;
	
	 public SuppressionBouton( Tache t){
		 super("Supprimer");
		 this.t= t;
	 }
	 
	 public Tache getTache(){
		 return t;
	 }
}
