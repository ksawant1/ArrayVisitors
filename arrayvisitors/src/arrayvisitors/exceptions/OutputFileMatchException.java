package arrayvisitors.exceptions;

public class OutputFileMatchException extends Throwable {
	/**
	 * exception thrown if two output files have same name and path
	 * @author Krupa Sawant
	 */
	public OutputFileMatchException(String s){
		super(s);

	}
}
