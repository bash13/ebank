package ebank.network;


/**
 * Generated from IDL interface "InterbankNetwork".
 *
 * @author JacORB IDL compiler V 2.3.1, 27-May-2009
 * @version generated at 12 déc. 2010 20:29:47
 */

public interface InterbankNetworkOperations
{
	/* constants */
	/* operations  */
	boolean transfer(long card_number, float amount) throws ebank.InsufficientBalanceException,ebank.CardNumberException;
}
