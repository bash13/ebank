package ebank.bank;

/**
 * Generated from IDL interface "BankProcessingCenter".
 *
 * @author JacORB IDL compiler V 2.3.1, 27-May-2009
 * @version generated at 9 janv. 2011 18:23:44
 */

public final class BankProcessingCenterHolder	implements org.omg.CORBA.portable.Streamable{
	 public BankProcessingCenter value;
	public BankProcessingCenterHolder()
	{
	}
	public BankProcessingCenterHolder (final BankProcessingCenter initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return BankProcessingCenterHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = BankProcessingCenterHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		BankProcessingCenterHelper.write (_out,value);
	}
}
