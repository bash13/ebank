package ebank.bank;

import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import ebank.TransactionRequest;
import ebank.network.InterbankNetwork;
import ebank.network.InterbankNetworkHelper;

/**
 * Classe représentation l'implémentation du serveur d'acquisition
 * @author alex
 *
 */
class AcquisitionImpl extends AcquisitionPOA {

	/**
	 * Bank Identify Number
	 */
	private static final Integer BIN = 4097;	

	/**
	 * Nom (Corba) du serveur d'autorisation
	 */
	private static final String authorization_server_name = "SAA";
		
	/**
	 * Nom (Corba) du centre de traitement bancaire
	 */
	private static final String bank_processing_center_name = "CTB";
	
	/**
	 * Nom (Corba) du réseau interbancaire
	 */	
	private static final String interbank_network_name = "SIT";
	
	/**
	 * Nom (Corba) du serveur d'acquisition
	 */
	private static final String acquisition_server_name = "SA";
	
	/**
	 * Demande de transaction
	 */	
	private TransactionRequest trans_request;

	public AcquisitionImpl() {
		
	}
	
	/**
	 * 
	 */
	@Override
	public boolean process(TransactionRequest transaction) {
		if (!checkBin(trans_request.getBin())) {
			//Banques différentes
			//On renvoie vers le réseau interbancaire
			try	{
				InterbankNetwork in;
				org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init();
				NamingContextExt nc = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));
				in = InterbankNetworkHelper.narrow(nc.resolve(nc.to_name(interbank_network_name)));
				in.transfer(trans_request);
			}
			catch (Exception e)	{
				e.printStackTrace();
			}
		}
		else {
			//Banques identiques
		}		
		return false;
	}
	
	/**
	 * Indique si le BIN de la carte bancaire de l'émetteur
	 * correspond à celui de la banque acquéreur
	 * @param bin
	 * @return
	 */
	private boolean checkBin(Integer card) {
		return card==BIN;
	}
	
	/**
	 * Ordonne au centre de traitement bancaire de créditer un compte
	 */
	private void sendCreditRequestToBpc() {
		try	{
			BankProcessingCenter bpc;
			org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init();
			NamingContextExt nc = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));
			bpc = BankProcessingCenterHelper.narrow(nc.resolve(nc.to_name(bank_processing_center_name)));
			bpc.credit(trans_request);
		}
		catch (Exception e)	{
			e.printStackTrace();
		}

	}

}

/**
 * Classe représentation le serveur d'acquisition
 * @author alex
 *
 */
public class AcquisitionServer {
	
	/**
	 * Nom (Corba) du serveur d'acquisition
	 */
	private static final String acquisition_server_name = "SA";

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
			org.omg.CORBA.Object o = poa.servant_to_reference(new AcquisitionImpl());
			NamingContextExt nc =
			NamingContextExtHelper.narrow(
			orb.resolve_initial_references("NameService"));
			nc.bind( nc.to_name(acquisition_server_name), o);
			System.out.println("AcquisitionServer is running...");
		}
		catch ( Exception e ) {
			e.printStackTrace();
		}
		orb.run();
	}
	
}
