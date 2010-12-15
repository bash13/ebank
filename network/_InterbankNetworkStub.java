package ebank.network;


/**
 * Generated from IDL interface "InterbankNetwork".
 *
 * @author JacORB IDL compiler V 2.3.1, 27-May-2009
 * @version generated at 12 déc. 2010 20:29:47
 */

public class _InterbankNetworkStub
	extends org.omg.CORBA.portable.ObjectImpl
	implements ebank.network.InterbankNetwork
{
	private String[] ids = {"IDL:ebank/network/InterbankNetwork:1.0"};
	public String[] _ids()
	{
		return ids;
	}

	public final static java.lang.Class _opsClass = ebank.network.InterbankNetworkOperations.class;
	public boolean transfer(long card_number, float amount) throws ebank.InsufficientBalanceException,ebank.CardNumberException
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			org.omg.CORBA.portable.OutputStream _os = null;
			try
			{
				_os = _request( "transfer", true);
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "transfer", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			InterbankNetworkOperations _localServant = (InterbankNetworkOperations)_so.servant;
			boolean _result;
			try
			{
				_result = _localServant.transfer(card_number,amount);
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
