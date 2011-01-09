package ebank.bank;

import java.sql.SQLException;

import ebank.TransactionRequest;


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
	boolean debit(TransactionRequest transaction) throws ebank.InsufficientBalanceException,ebank.CardNumberException, SQLException, ClassNotFoundException;
	boolean credit(TransactionRequest transaction) throws ebank.CardNumberException, ClassNotFoundException, SQLException;
	float getBalance(long card_number) throws ebank.CardNumberException, SQLException, ClassNotFoundException;
}
