package view_To_Do;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model_To_Do.Tache;

public class PanelTache extends JPanel {

	private static final long serialVersionUID = -1417327191503600006L;
	private Tache t;
	public PanelTache(Tache t){
		this.t =  t;
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setMaximumSize(new Dimension(800,200));
		initCompo();
	}
	
	private void initCompo(){
		Map<String,String> str = t.getInformation();
		Iterator it =str.keySet().iterator();
		JPanel north = new JPanel();
		String key ="";
		while(it.hasNext()){
			 key= it.next().toString();
			 if(key.equals("Description")){
				 JLabel jl = new JLabel();
				
				 String text = "<html><p>" + key + " :<br> " + str.get(key).toString()+ "</p></html>";
				 jl.setPreferredSize(new Dimension(600, str.get(key).toString().length()*2));
				 jl.setText(text);
				 this.add(jl,BorderLayout.CENTER);
			 }else{
				 
				 north.add(new JLabel(key + " : " + str.get(key)));
			 }
		}
		this.add(north, BorderLayout.PAGE_START);
	}
	
}
