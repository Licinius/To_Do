package ExceptionTo_Do;

public class ExceptionTacheAnterieur extends ExceptionTache {
	private static final long serialVersionUID = -2007491263257383995L;
	public ExceptionTacheAnterieur(String strDate){
		super("La date saisie : "+strDate +"est antérieur à la date d'aujourd'hui");
	}
}
