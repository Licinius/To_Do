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

import model_To_Do.Categorie;

public class CategorieDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	 private Categorie info;
	  private boolean sendData;
	  private  JTextField nom;
	 

	  public CategorieDialog(MyFrame parent, String title, boolean modal){
	    super(parent, title, modal);
	    this.setSize(550, 270);
	    this.setLocationRelativeTo(null);
	    this.setResizable(false);
	    this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    this.initComponent();
	  }
	  /**
	   * Envoie les informations d'une catégorie une fois fini
	   * @return
	   * 	Une Categorie
	   */
	  public Categorie showTacheDialog(){
	    this.sendData = false;
	    this.setVisible(true);      
	    return this.info;      
	  }
	  /**
	   * Initialise les composants de la fenêtre de dialogue 
	   */
	  private void initComponent(){
		    //Le nom
		  	JPanel panNom = new JPanel();
		    nom = new JTextField();
		    nom.setPreferredSize(new Dimension(100, 25));
		    panNom.setPreferredSize(new Dimension(220, 60));
		    panNom.setBorder(BorderFactory.createTitledBorder("Nom de la categorie"));
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
		    	  if(!nom.getText().trim().isEmpty()){
		    		  info = new Categorie(nom.getText());
		    		  setVisible(false);
		    	  }
		    	  else{
		    		  nom.setBackground(Color.PINK);
		    	  }
		        
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
