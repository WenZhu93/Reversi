package model;

/**
 * This method is to construct a exception which is to check if
 * the input is in the correct range.
 * 
 * @author Jerry Zhu
 *
 */
public class UnvalidInputException extends Exception{

	private static final long serialVersionUID = 1L;
	/**
	 * This is the constructor of the exception.
	 * @param message The message need to be printed.
	 */
	public UnvalidInputException(String message) {
		super(message);
	}

	/**
	 * This method is to show the message.
	 */
	public String toString() {
		return getMessage();
	}


}
