package ebank;


/**
 * Generated from IDL exception "InsufficientBalanceException".
 *
 * @author JacORB IDL compiler V 2.3.1, 27-May-2009
 * @version generated at 9 janv. 2011 18:23:44
 */

public final class InsufficientBalanceExceptionHelper
{
	private static org.omg.CORBA.TypeCode _type = null;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			_type = org.omg.CORBA.ORB.init().create_exception_tc(ebank.InsufficientBalanceExceptionHelper.id(),"InsufficientBalanceException",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("message", org.omg.CORBA.ORB.init().create_string_tc(0), null)});
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final ebank.InsufficientBalanceException s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static ebank.InsufficientBalanceException extract (final org.omg.CORBA.Any any)
	{
		return read(any.create_input_stream());
	}

	public static String id()
	{
		return "IDL:ebank/InsufficientBalanceException:1.0";
	}
	public static ebank.InsufficientBalanceException read (final org.omg.CORBA.portable.InputStream in)
	{
		String id = in.read_string();
		if (!id.equals(id())) throw new org.omg.CORBA.MARSHAL("wrong id: " + id);
		java.lang.String x0;
		x0=in.read_string();
		final ebank.InsufficientBalanceException result = new ebank.InsufficientBalanceException(id, x0);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final ebank.InsufficientBalanceException s)
	{
		out.write_string(id());
		out.write_string(s.message);
	}
}
