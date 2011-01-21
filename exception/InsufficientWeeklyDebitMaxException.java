package ebank.exception;

public class InsufficientWeeklyDebitMaxException extends Exception {

	private static final long serialVersionUID = 1L;

	public InsufficientWeeklyDebitMaxException() {}
	
	public InsufficientWeeklyDebitMaxException(String msg) {
		super(msg);
	}
}
