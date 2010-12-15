package ebank.bank;

/**
 * Generated from IDL interface "Authorization".
 *
 * @author JacORB IDL compiler V 2.3.1, 27-May-2009
 * @version generated at 12 déc. 2010 20:29:47
 */

public final class AuthorizationHolder	implements org.omg.CORBA.portable.Streamable{
	 public Authorization value;
	public AuthorizationHolder()
	{
	}
	public AuthorizationHolder (final Authorization initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return AuthorizationHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = AuthorizationHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		AuthorizationHelper.write (_out,value);
	}
}
