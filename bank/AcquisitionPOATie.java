package ebank.bank;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "Acquisition".
 *
 * @author JacORB IDL compiler V 2.3.1, 27-May-2009
 * @version generated at 12 déc. 2010 20:29:47
 */

public class AcquisitionPOATie
	extends AcquisitionPOA
{
	private AcquisitionOperations _delegate;

	private POA _poa;
	public AcquisitionPOATie(AcquisitionOperations delegate)
	{
		_delegate = delegate;
	}
	public AcquisitionPOATie(AcquisitionOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public ebank.bank.Acquisition _this()
	{
		return ebank.bank.AcquisitionHelper.narrow(_this_object());
	}
	public ebank.bank.Acquisition _this(org.omg.CORBA.ORB orb)
	{
		return ebank.bank.AcquisitionHelper.narrow(_this_object(orb));
	}
	public AcquisitionOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(AcquisitionOperations delegate)
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
	public boolean process(long card_number, float amount, int dealer_account_number) throws ebank.InsufficientBalanceException,ebank.CardNumberException
	{
		return _delegate.process(card_number,amount,dealer_account_number);
	}

}
