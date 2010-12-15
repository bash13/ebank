package ebank.bank;

import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import ebank.CardNumberException;
import ebank.InsufficientBalanceException;

class AuthorizationImpl extends AuthorizationPOA {

	/**
	 * Nom (Corba) du serveur d'autorisation
	 */
	private static final String acquisition_server_name = "SA";
		
	/**
	 * Nom (Corba) du centre de traitement bancaire
	 */
	private static final String bank_processing_center_name = "CTB";
	
	/**
	 * Nom (Corba) du réseau interbancaire
	 */
	
	private static final String interbank_network_name = "SIT";
	/**
	 * Demande de transaction
	 */
	
	@Override
	public boolean process(long cardNumber, float amount)
			throws InsufficientBalanceException, CardNumberException {
		return false;
	}
	
	public boolean checkClientAccount(Long card_number, Float amount) {
		return false;
	}
	
	public boolean checkClientCard(Long card_number) {
		return false;		
	}
	
	public void sendDebitRequestToBpc()
	{
		
	}

}

/**
 * Classe représentation le serveur d'autorisation
 * @author alex
 *
 */
public class AuthorizationServer {
	
	/**
	 * Nom (Corba) du serveur d'acquisition
	 */
	private static final String authorization_server_name = "SAA";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);
		try	{
			org.omg.PortableServer.POA poa =
			org.omg.PortableServer.POAHelper.narrow(
			orb.resolve_initial_references("RootPOA"));
			poa.the_POAManager().activate();
			org.omg.CORBA.Object o = poa.servant_to_reference(new AuthorizationImpl());
			NamingContextExt nc =
			NamingContextExtHelper.narrow(
			orb.resolve_initial_references("NameService"));
			nc.bind( nc.to_name(authorization_server_name), o);
			System.out.println("AuthorizationServer is running...");
		}
		catch ( Exception e ) {
			e.printStackTrace();
		}
		orb.run();
	}
	
}
