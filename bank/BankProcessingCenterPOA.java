package ebank.bank;

import java.sql.SQLException;


/**
 * Generated from IDL interface "BankProcessingCenter".
 *
 * @author JacORB IDL compiler V 2.3.1, 27-May-2009
 * @version generated at 9 janv. 2011 18:23:44
 */

public abstract class BankProcessingCenterPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, ebank.bank.BankProcessingCenterOperations
{
	static private final java.util.Hashtable m_opsHash = new java.util.Hashtable();
	static
	{
		m_opsHash.put ( "credit", new java.lang.Integer(0));
		m_opsHash.put ( "getBalance", new java.lang.Integer(1));
		m_opsHash.put ( "debit", new java.lang.Integer(2));
	}
	private String[] ids = {"IDL:ebank/bank/BankProcessingCenter:1.0"};
	public ebank.bank.BankProcessingCenter _this()
	{
		return ebank.bank.BankProcessingCenterHelper.narrow(_this_object());
	}
	public ebank.bank.BankProcessingCenter _this(org.omg.CORBA.ORB orb)
	{
		return ebank.bank.BankProcessingCenterHelper.narrow(_this_object(orb));
	}
	public org.omg.CORBA.portable.OutputStream _invoke(String method, org.omg.CORBA.portable.InputStream _input, org.omg.CORBA.portable.ResponseHandler handler)
		throws org.omg.CORBA.SystemException
	{
		org.omg.CORBA.portable.OutputStream _out = null;
		// do something
		// quick lookup of operation
		java.lang.Integer opsIndex = (java.lang.Integer)m_opsHash.get ( method );
		if ( null == opsIndex )
			throw new org.omg.CORBA.BAD_OPERATION(method + " not found");
		switch ( opsIndex.intValue() )
		{
			case 0: // credit
			{
			try
			{
				ebank.TransactionRequest _arg0=ebank.TransactionRequestHelper.read(_input);
				_out = handler.createReply();
				_out.write_boolean(credit(_arg0));
			}
			catch(ebank.CardNumberException _ex0)
			{
				_out = handler.createExceptionReply();
				ebank.CardNumberExceptionHelper.write(_out, _ex0);
			} catch (Exception e) {
				e.printStackTrace();
			}
				break;
			}
			case 1: // getBalance
			{
			try
			{
				long _arg0=_input.read_ulonglong();
				_out = handler.createReply();
				_out.write_float(getBalance(_arg0));
			}
			catch(ebank.CardNumberException _ex0)
			{
				_out = handler.createExceptionReply();
				ebank.CardNumberExceptionHelper.write(_out, _ex0);
			}catch (Exception e) {
				e.printStackTrace();
			}
				break;
			}
			case 2: // debit
			{
			try
			{
				ebank.TransactionRequest _arg0=ebank.TransactionRequestHelper.read(_input);
				_out = handler.createReply();
				_out.write_boolean(debit(_arg0));
			}
			catch(ebank.InsufficientBalanceException _ex0)
			{
				_out = handler.createExceptionReply();
				ebank.InsufficientBalanceExceptionHelper.write(_out, _ex0);
			}
			catch(ebank.CardNumberException _ex1)
			{
				_out = handler.createExceptionReply();
				ebank.CardNumberExceptionHelper.write(_out, _ex1);
			}catch (Exception e) {
				e.printStackTrace();
			}
				break;
			}
		}
		return _out;
	}

	public String[] _all_interfaces(org.omg.PortableServer.POA poa, byte[] obj_id)
	{
		return ids;
	}
}
