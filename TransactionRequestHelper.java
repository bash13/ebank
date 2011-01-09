package ebank;


/**
 * Generated from IDL struct "TransactionRequest".
 *
 * @author JacORB IDL compiler V 2.3.1, 27-May-2009
 * @version generated at 9 janv. 2011 18:23:44
 */

public final class TransactionRequestHelper
{
	private static org.omg.CORBA.TypeCode _type = null;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			_type = org.omg.CORBA.ORB.init().create_struct_tc(ebank.TransactionRequestHelper.id(),"TransactionRequest",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("card_number", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(24)), null),new org.omg.CORBA.StructMember("ccv", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5)), null),new org.omg.CORBA.StructMember("date", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("amount", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(6)), null),new org.omg.CORBA.StructMember("dealer_account_number", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5)), null)});
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final ebank.TransactionRequest s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static ebank.TransactionRequest extract (final org.omg.CORBA.Any any)
	{
		return read(any.create_input_stream());
	}

	public static String id()
	{
		return "IDL:ebank/TransactionRequest:1.0";
	}
	public static ebank.TransactionRequest read (final org.omg.CORBA.portable.InputStream in)
	{
		ebank.TransactionRequest result = new ebank.TransactionRequest();
		result.card_number=in.read_ulonglong();
		result.ccv=in.read_ulong();
		result.date=in.read_string();
		result.amount=in.read_float();
		result.dealer_account_number=in.read_ulong();
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final ebank.TransactionRequest s)
	{
		out.write_ulonglong(s.card_number);
		out.write_ulong(s.ccv);
		out.write_string(s.date);
		out.write_float(s.amount);
		out.write_ulong(s.dealer_account_number);
	}
}
