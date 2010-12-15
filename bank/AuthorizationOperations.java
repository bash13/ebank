package ebank.bank;


/**
 * Generated from IDL interface "Authorization".
 *
 * @author JacORB IDL compiler V 2.3.1, 27-May-2009
 * @version generated at 12 déc. 2010 20:29:47
 */

public interface AuthorizationOperations
{
	/* constants */
	/* operations  */
	boolean process(long card_number, float amount) throws ebank.InsufficientBalanceException,ebank.CardNumberException;
}
