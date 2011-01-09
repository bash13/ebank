package ebank;

/**
 * Generated from IDL struct "TransactionRequest".
 *
 * @author JacORB IDL compiler V 2.3.1, 27-May-2009
 * @version generated at 9 janv. 2011 18:23:44
 */

public final class TransactionRequestHolder
	implements org.omg.CORBA.portable.Streamable
{
	public ebank.TransactionRequest value;

	public TransactionRequestHolder ()
	{
	}
	public TransactionRequestHolder(final ebank.TransactionRequest initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return ebank.TransactionRequestHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = ebank.TransactionRequestHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		ebank.TransactionRequestHelper.write(_out, value);
	}
}
