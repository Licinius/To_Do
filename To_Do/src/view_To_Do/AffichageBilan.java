package view_To_Do;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model_To_Do.Tache;

public class AffichageBilan extends JDialog {
	private ArrayList<Tache> to_Do;
	private int nombreTacheRetard;
	private int nombreTacheTermine;
	private int nombreTache;
	public AffichageBilan(MyFrame parent, String title, boolean modal,ArrayList<Tache> to_Do,int nombreTacheRetard,int nombreTacheTermine,int nombreTache){
		super(parent, title, modal);
		this.setSize(550, 270);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.to_Do = to_Do;
		this.nombreTache = nombreTache;
		this.nombreTacheTermine=nombreTacheTermine;
		this.nombreTacheRetard = nombreTacheRetard;
		this.initComponent();
	}
	
	public void initComponent(){
		 JLabel jl = new JLabel();
			
		 String text = "<html><p>Les taches à faire :<br>";
		 for(Tache t : to_Do){
			Map<String,String> str = t.getInformation();
			text+="-";
			Iterator<String> it =str.keySet().iterator();
			String key ="";
			while(it.hasNext()){
				 key= it.next().toString();
				 if(!key.equals("Description")){
					 text+=key + " : " + str.get(key)+"  ";
				 }
			}
			text+="<br>";
		 }
		 text+="</p>";
		 if(nombreTache!=0){
			 text+="<p> Pourcentage en retard "+nombreTacheRetard*100/nombreTache + "%</p>";
			 text+="<p> Pourcentage terminé "+nombreTacheTermine*100/nombreTache + "%</p>";
		 }
		 text+="</html>";
		 jl.setText(text);
		 this.add(jl);
		 setVisible(true);
		
	}
}
