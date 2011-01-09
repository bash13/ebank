package ebank.bank;

import java.sql.SQLException;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "BankProcessingCenter".
 *
 * @author JacORB IDL compiler V 2.3.1, 27-May-2009
 * @version generated at 9 janv. 2011 18:23:44
 */

public class BankProcessingCenterPOATie
	extends BankProcessingCenterPOA
{
	private BankProcessingCenterOperations _delegate;

	private POA _poa;
	public BankProcessingCenterPOATie(BankProcessingCenterOperations delegate)
	{
		_delegate = delegate;
	}
	public BankProcessingCenterPOATie(BankProcessingCenterOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public ebank.bank.BankProcessingCenter _this()
	{
		return ebank.bank.BankProcessingCenterHelper.narrow(_this_object());
	}
	public ebank.bank.BankProcessingCenter _this(org.omg.CORBA.ORB orb)
	{
		return ebank.bank.BankProcessingCenterHelper.narrow(_this_object(orb));
	}
	public BankProcessingCenterOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(BankProcessingCenterOperations delegate)
	{
		_delegate = delegate;
	}
	public POA _default_POA()
	{
		if (_poa != null)
		{
			return _poa;
		}
		return super._default_POA();
	}
	public boolean credit(ebank.TransactionRequest transaction) throws ebank.CardNumberException, ClassNotFoundException, SQLException
	{
		return _delegate.credit(transaction);
	}

	public float getBalance(long card_number) throws ebank.CardNumberException, SQLException, ClassNotFoundException
	{
		return _delegate.getBalance(card_number);
	}

	public boolean debit(ebank.TransactionRequest transaction) throws ebank.InsufficientBalanceException,ebank.CardNumberException, SQLException, ClassNotFoundException
	{
		return _delegate.debit(transaction);
	}

}
