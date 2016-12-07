package view_To_Do;

import javax.swing.JButton;

import model_To_Do.Tache;

public class BoutonTache  extends JButton{
	private Tache t;
	
	 public BoutonTache(String str, Tache t){
		 super(str);
		 this.t= t;
	 }
	 
	 public Tache getTache(){
		 return t;
	 }
}
