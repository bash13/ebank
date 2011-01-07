package ebank.bank;


/**
 * Generated from IDL interface "Authorization".
 *
 * @author JacORB IDL compiler V 2.3.1, 27-May-2009
 * @version generated at 12 déc. 2010 20:29:47
 */

public class _AuthorizationStub
	extends org.omg.CORBA.portable.ObjectImpl
	implements ebank.bank.Authorization
{
	private String[] ids = {"IDL:ebank/bank/Authorization:1.0"};
	public String[] _ids()
	{
		return ids;
	}

	public final static java.lang.Class _opsClass = ebank.bank.AuthorizationOperations.class;
	public boolean process(long card_number, float amount) throws ebank.InsufficientBalanceException,ebank.CardNumberException
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			org.omg.CORBA.portable.OutputStream _os = null;
			try
			{
				_os = _request( "process", true);
				_os.write_ulonglong(card_number);
				_os.write_float(amount);
				_is = _invoke(_os);
				boolean _result = _is.read_boolean();
				return _result;
			}
			catch( org.omg.CORBA.portable.RemarshalException _rx ){}
			catch( org.omg.CORBA.portable.ApplicationException _ax )
			{
				String _id = _ax.getId();
				try
				{
					if( _id.equals("IDL:ebank/InsufficientBalanceException:1.0"))
					{
						throw ebank.InsufficientBalanceExceptionHelper.read(_ax.getInputStream());
					}
					else 
					if( _id.equals("IDL:ebank/CardNumberException:1.0"))
					{
						throw ebank.CardNumberExceptionHelper.read(_ax.getInputStream());
					}
					else 
					{
						throw new RuntimeException("Unexpected exception " + _id );
					}
				}
				finally
				{
				try
				{
					_ax.getInputStream().close();
				}
				catch (java.io.IOException e)
				{
					throw new RuntimeException("Unexpected exception " + e.toString() );
				}
			}
			}
			finally
			{
				if (_os != null)
				{
					try
					{
						_os.close();
					}
					catch (java.io.IOException e)
					{
					throw new RuntimeException("Unexpected exception " + e.toString() );
					}
				}
				this._releaseReply(_is);
			}
		}
		else
		{
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "process", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			AuthorizationOperations _localServant = (AuthorizationOperations)_so.servant;
			boolean _result;
			try
			{
				_result = _localServant.process(card_number,amount);
			}
			finally
			{
				_servant_postinvoke(_so);
			}
			return _result;
		}

		}

	}

}