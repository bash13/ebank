package ebank.bank;


/**
 * Generated from IDL interface "Authorization".
 *
 * @author JacORB IDL compiler V 2.3.1, 27-May-2009
 * @version generated at 12 déc. 2010 20:29:47
 */

public abstract class AuthorizationPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, ebank.bank.AuthorizationOperations
{
	static private final java.util.Hashtable m_opsHash = new java.util.Hashtable();
	static
	{
		m_opsHash.put ( "process", new java.lang.Integer(0));
	}
	private String[] ids = {"IDL:ebank/bank/Authorization:1.0"};
	public ebank.bank.Authorization _this()
	{
		return ebank.bank.AuthorizationHelper.narrow(_this_object());
	}
	public ebank.bank.Authorization _this(org.omg.CORBA.ORB orb)
	{
		return ebank.bank.AuthorizationHelper.narrow(_this_object(orb));
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
			case 0: // process
			{
			try
			{
				long _arg0=_input.read_ulonglong();
				float _arg1=_input.read_float();
				_out = handler.createReply();
				_out.write_boolean(process(_arg0,_arg1));
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
