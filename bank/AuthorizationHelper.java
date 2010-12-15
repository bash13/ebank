package ebank.bank;


/**
 * Generated from IDL interface "Authorization".
 *
 * @author JacORB IDL compiler V 2.3.1, 27-May-2009
 * @version generated at 12 déc. 2010 20:29:47
 */

public final class AuthorizationHelper
{
	public static void insert (final org.omg.CORBA.Any any, final ebank.bank.Authorization s)
	{
			any.insert_Object(s);
	}
	public static ebank.bank.Authorization extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static org.omg.CORBA.TypeCode type()
	{
		return org.omg.CORBA.ORB.init().create_interface_tc("IDL:ebank/bank/Authorization:1.0", "Authorization");
	}
	public static String id()
	{
		return "IDL:ebank/bank/Authorization:1.0";
	}
	public static Authorization read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(ebank.bank._AuthorizationStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final ebank.bank.Authorization s)
	{
		_out.write_Object(s);
	}
	public static ebank.bank.Authorization narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof ebank.bank.Authorization)
		{
			return (ebank.bank.Authorization)obj;
		}
		else if (obj._is_a("IDL:ebank/bank/Authorization:1.0"))
		{
			ebank.bank._AuthorizationStub stub;
			stub = new ebank.bank._AuthorizationStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static ebank.bank.Authorization unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof ebank.bank.Authorization)
		{
			return (ebank.bank.Authorization)obj;
		}
		else
		{
			ebank.bank._AuthorizationStub stub;
			stub = new ebank.bank._AuthorizationStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
