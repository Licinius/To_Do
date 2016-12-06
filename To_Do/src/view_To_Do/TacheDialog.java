package view_To_Do;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model_To_Do.Tache;

public class TacheDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	 private Tache info;
	  private boolean sendData;
	  private  JTextField nom;
	 

	  public TacheDialog(MyFrame parent, String title, boolean modal){
	    super(parent, title, modal);
	    this.setSize(550, 270);
	    this.setLocationRelativeTo(null);
	    this.setResizable(false);
	    this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	    this.initComponent();
	  }

	  public Tache showTacheDialog(){
	    this.sendData = false;
	    this.setVisible(true);      
	    return this.info;      
	  }

	  private void initComponent(){
		    //Le nom
		  	JPanel panNom = new JPanel();
		    nom = new JTextField();
		    nom.setPreferredSize(new Dimension(100, 25));
		    panNom.setPreferredSize(new Dimension(220, 60));
		    panNom.setBorder(BorderFactory.createTitledBorder("Nom de la tache"));
		    JLabel nomLabel = new JLabel("Saisir un nom :");
		    panNom.add(nomLabel);
		    panNom.add(nom);

		    JPanel content = new JPanel();
		    content.setBackground(Color.white);
		    content.add(panNom);

		    JPanel control = new JPanel();
		    JButton okBouton = new JButton("OK");
		    
		    okBouton.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent arg0) {        
		         setVisible(false);
		      }
		      }      
		    );

		    JButton cancelBouton = new JButton("Annuler");
		    cancelBouton.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent arg0) {
		        setVisible(false);
		      }      
		    });

		    control.add(okBouton);
		    control.add(cancelBouton);
		    this.getContentPane().add(content, BorderLayout.CENTER);
		    this.getContentPane().add(control, BorderLayout.SOUTH);
		  }  
}
