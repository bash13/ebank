package ebank.bank;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "Authorization".
 *
 * @author JacORB IDL compiler V 2.3.1, 27-May-2009
 * @version generated at 12 déc. 2010 20:29:47
 */

public class AuthorizationPOATie
	extends AuthorizationPOA
{
	private AuthorizationOperations _delegate;

	private POA _poa;
	public AuthorizationPOATie(AuthorizationOperations delegate)
	{
		_delegate = delegate;
	}
	public AuthorizationPOATie(AuthorizationOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public ebank.bank.Authorization _this()
	{
		return ebank.bank.AuthorizationHelper.narrow(_this_object());
	}
	public ebank.bank.Authorization _this(org.omg.CORBA.ORB orb)
	{
		return ebank.bank.AuthorizationHelper.narrow(_this_object(orb));
	}
	public AuthorizationOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(AuthorizationOperations delegate)
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
	public boolean process(long card_number, float amount) throws ebank.InsufficientBalanceException,ebank.CardNumberException
	{
		return _delegate.process(card_number,amount);
	}

}
