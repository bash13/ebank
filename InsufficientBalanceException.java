package ebank;

/**
 * Generated from IDL exception "InsufficientBalanceException".
 *
 * @author JacORB IDL compiler V 2.3.1, 27-May-2009
 * @version generated at 9 janv. 2011 18:23:44
 */

public final class InsufficientBalanceException
	extends org.omg.CORBA.UserException
{
	public InsufficientBalanceException()
	{
		super(ebank.InsufficientBalanceExceptionHelper.id());
	}

	public java.lang.String message = "";
	public InsufficientBalanceException(java.lang.String _reason,java.lang.String message)
	{
		super(_reason);
		this.message = message;
	}
	public InsufficientBalanceException(java.lang.String message)
	{
		super(ebank.InsufficientBalanceExceptionHelper.id());
		this.message = message;
	}
}
