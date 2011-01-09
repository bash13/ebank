package ebank.network;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "InterbankNetwork".
 *
 * @author JacORB IDL compiler V 2.3.1, 27-May-2009
 * @version generated at 9 janv. 2011 18:23:44
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
	public boolean transfer(ebank.TransactionRequest transaction)
	{
		return _delegate.transfer(transaction);
	}

}
