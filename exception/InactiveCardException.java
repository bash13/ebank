package ebank.exception;

public class InactiveCardException  extends Exception {
	
	private static final long serialVersionUID = 1L;

	public InactiveCardException() {}
	
	public InactiveCardException(String msg) {
		super(msg);
	}
}
