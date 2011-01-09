package ebank.bank;


/**
 * Generated from IDL interface "Acquisition".
 *
 * @author JacORB IDL compiler V 2.3.1, 27-May-2009
 * @version generated at 9 janv. 2011 18:23:44
 */

public final class AcquisitionHelper
{
	public static void insert (final org.omg.CORBA.Any any, final ebank.bank.Acquisition s)
	{
			any.insert_Object(s);
	}
	public static ebank.bank.Acquisition extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static org.omg.CORBA.TypeCode type()
	{
		return org.omg.CORBA.ORB.init().create_interface_tc("IDL:ebank/bank/Acquisition:1.0", "Acquisition");
	}
	public static String id()
	{
		return "IDL:ebank/bank/Acquisition:1.0";
	}
	public static Acquisition read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(ebank.bank._AcquisitionStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final ebank.bank.Acquisition s)
	{
		_out.write_Object(s);
	}
	public static ebank.bank.Acquisition narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof ebank.bank.Acquisition)
		{
			return (ebank.bank.Acquisition)obj;
		}
		else if (obj._is_a("IDL:ebank/bank/Acquisition:1.0"))
		{
			ebank.bank._AcquisitionStub stub;
			stub = new ebank.bank._AcquisitionStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static ebank.bank.Acquisition unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof ebank.bank.Acquisition)
		{
			return (ebank.bank.Acquisition)obj;
		}
		else
		{
			ebank.bank._AcquisitionStub stub;
			stub = new ebank.bank._AcquisitionStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
