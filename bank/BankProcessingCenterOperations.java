package ebank.bank;


/**
 * Generated from IDL interface "BankProcessingCenter".
 *
 * @author JacORB IDL compiler V 2.3.1, 27-May-2009
 * @version generated at 9 janv. 2011 18:23:44
 */

public interface BankProcessingCenterOperations
{
	/* constants */
	/* operations  */
	boolean debit(long card_number, float amount) throws ebank.InsufficientBalanceException,ebank.CardNumberException;
	boolean credit(long card_number, float amount) throws ebank.CardNumberException;
	float getBalance(long card_number) throws ebank.CardNumberException;
}
