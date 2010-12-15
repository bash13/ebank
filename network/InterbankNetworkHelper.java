package ebank.network;


/**
 * Generated from IDL interface "InterbankNetwork".
 *
 * @author JacORB IDL compiler V 2.3.1, 27-May-2009
 * @version generated at 12 déc. 2010 20:29:47
 */

public final class InterbankNetworkHelper
{
	public static void insert (final org.omg.CORBA.Any any, final ebank.network.InterbankNetwork s)
	{
			any.insert_Object(s);
	}
	public static ebank.network.InterbankNetwork extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static org.omg.CORBA.TypeCode type()
	{
		return org.omg.CORBA.ORB.init().create_interface_tc("IDL:ebank/network/InterbankNetwork:1.0", "InterbankNetwork");
	}
	public static String id()
	{
		return "IDL:ebank/network/InterbankNetwork:1.0";
	}
	public static InterbankNetwork read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(ebank.network._InterbankNetworkStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final ebank.network.InterbankNetwork s)
	{
		_out.write_Object(s);
	}
	public static ebank.network.InterbankNetwork narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof ebank.network.InterbankNetwork)
		{
			return (ebank.network.InterbankNetwork)obj;
		}
		else if (obj._is_a("IDL:ebank/network/InterbankNetwork:1.0"))
		{
			ebank.network._InterbankNetworkStub stub;
			stub = new ebank.network._InterbankNetworkStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static ebank.network.InterbankNetwork unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof ebank.network.InterbankNetwork)
		{
			return (ebank.network.InterbankNetwork)obj;
		}
		else
		{
			ebank.network._InterbankNetworkStub stub;
			stub = new ebank.network._InterbankNetworkStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
