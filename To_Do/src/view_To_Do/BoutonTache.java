package view_To_Do;

import javax.swing.JButton;

import model_To_Do.Tache;

public class BoutonTache  extends JButton{
	private static final long serialVersionUID = 1L;
	private Tache t;
	
	 public BoutonTache(String str, Tache t){
		 super(str);
		 this.t= t;
	 }
	 
	 public Tache getTache(){
		 return t;
	 }
}
