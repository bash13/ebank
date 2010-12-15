package ebank.network;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "InterbankNetwork".
 *
 * @author JacORB IDL compiler V 2.3.1, 27-May-2009
 * @version generated at 12 déc. 2010 20:29:47
 */

public class InterbankNetworkPOATie
	extends InterbankNetworkPOA
{
	private InterbankNetworkOperations _delegate;

	private POA _poa;
	public InterbankNetworkPOATie(InterbankNetworkOperations delegate)
	{
		_delegate = delegate;
	}
	public InterbankNetworkPOATie(InterbankNetworkOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public ebank.network.InterbankNetwork _this()
	{
		return ebank.network.InterbankNetworkHelper.narrow(_this_object());
	}
	public ebank.network.InterbankNetwork _this(org.omg.CORBA.ORB orb)
	{
		return ebank.network.InterbankNetworkHelper.narrow(_this_object(orb));
	}
	public InterbankNetworkOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(InterbankNetworkOperations delegate)
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
	public boolean transfer(long card_number, float amount) throws ebank.InsufficientBalanceException,ebank.CardNumberException
	{
		return _delegate.transfer(card_number,amount);
	}

}
