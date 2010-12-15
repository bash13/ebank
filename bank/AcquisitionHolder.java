package ebank.bank;

/**
 * Generated from IDL interface "Acquisition".
 *
 * @author JacORB IDL compiler V 2.3.1, 27-May-2009
 * @version generated at 12 déc. 2010 20:29:47
 */

public final class AcquisitionHolder	implements org.omg.CORBA.portable.Streamable{
	 public Acquisition value;
	public AcquisitionHolder()
	{
	}
	public AcquisitionHolder (final Acquisition initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return AcquisitionHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = AcquisitionHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		AcquisitionHelper.write (_out,value);
	}
}
