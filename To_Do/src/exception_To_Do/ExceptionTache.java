package exception_To_Do;
/**
 Classe qui permet de génerer des exceptions de type ExceptionTache
**/
public class ExceptionTache extends Exception{
	private static final long serialVersionUID = 4445839753876631340L;
	public ExceptionTache(String str){
		super(str);
	}
}
