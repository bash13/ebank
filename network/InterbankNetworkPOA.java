package ebank.network;


/**
 * Generated from IDL interface "InterbankNetwork".
 *
 * @author JacORB IDL compiler V 2.3.1, 27-May-2009
 * @version generated at 9 janv. 2011 18:23:44
 */

public abstract class InterbankNetworkPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, ebank.network.InterbankNetworkOperations
{
	static private final java.util.Hashtable m_opsHash = new java.util.Hashtable();
	static
	{
		m_opsHash.put ( "transfer", new java.lang.Integer(0));
	}
	private String[] ids = {"IDL:ebank/network/InterbankNetwork:1.0"};
	public ebank.network.InterbankNetwork _this()
	{
		return ebank.network.InterbankNetworkHelper.narrow(_this_object());
	}
	public ebank.network.InterbankNetwork _this(org.omg.CORBA.ORB orb)
	{
		return ebank.network.InterbankNetworkHelper.narrow(_this_object(orb));
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
			case 0: // transfer
			{
				ebank.TransactionRequest _arg0=ebank.TransactionRequestHelper.read(_input);
				_out = handler.createReply();
				_out.write_boolean(transfer(_arg0));
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
