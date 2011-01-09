package ebank.network;

/**
 * Generated from IDL interface "InterbankNetwork".
 *
 * @author JacORB IDL compiler V 2.3.1, 27-May-2009
 * @version generated at 9 janv. 2011 18:23:44
 */

public final class InterbankNetworkHolder	implements org.omg.CORBA.portable.Streamable{
	 public InterbankNetwork value;
	public InterbankNetworkHolder()
	{
	}
	public InterbankNetworkHolder (final InterbankNetwork initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return InterbankNetworkHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = InterbankNetworkHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		InterbankNetworkHelper.write (_out,value);
	}
}
