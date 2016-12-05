package view_To_Do;

import javax.swing.JDialog;

import model_To_Do.Tache;

public class TacheDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	 private Tache info;
	  private boolean sendData;


	  public TacheDialog(MyFrame parent, String title, boolean modal){
	    super(parent, title, modal);
	    System.out.println("Coucou");
	    this.setSize(550, 270);
	    this.setLocationRelativeTo(null);
	    this.setResizable(false);
	    setVisible(true);
	    this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	    this.initComponent();
	  }

	  public Tache showZDialog(){
	    this.sendData = false;
	    this.setVisible(true);      
	    return this.info;      
	  }

	  private void initComponent(){
	   
	}
}
