package ebank;

/**
 * Generated from IDL exception "CardNumberException".
 *
 * @author JacORB IDL compiler V 2.3.1, 27-May-2009
 * @version generated at 9 janv. 2011 18:23:44
 */

public final class CardNumberException
	extends org.omg.CORBA.UserException
{
	public CardNumberException()
	{
		super(ebank.CardNumberExceptionHelper.id());
	}

	public java.lang.String message = "";
	public CardNumberException(java.lang.String _reason,java.lang.String message)
	{
		super(_reason);
		this.message = message;
	}
	public CardNumberException(java.lang.String message)
	{
		super(ebank.CardNumberExceptionHelper.id());
		this.message = message;
	}
}
