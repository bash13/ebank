package ebank.network;

import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import ebank.CardNumberException;
import ebank.InsufficientBalanceException;
import ebank.TransactionRequest;
import ebank.data.Database;

class InterbankNetworkImpl extends InterbankNetworkPOA {

	/**
	 * Connexion à la base de données
	 */
	private Database db;

	/**
	 * Constructeur. Crée la connexion à la base.
	 */
	public InterbankNetworkImpl()
	{
		 db = new Database();
	}
	
	/**
	 * Transfert de transaction vers la banque emettrice
	 */
	@Override
	public boolean transfer(TransactionRequest transaction) {
		return false;
	}
	
}

/**
 * Classe reprÃ©sentation le serveur d'autorisation
 * @author alex
 *
 */
public class InterbankNetworkServer {
	
	/**
	 * Nom (Corba) du serveur d'acquisition
	 */
	private static final String interbank_network_server_name = "SIT";

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
			org.omg.CORBA.Object o = poa.servant_to_reference(new InterbankNetworkImpl());
			NamingContextExt nc =
			NamingContextExtHelper.narrow(
			orb.resolve_initial_references("NameService"));
			nc.bind( nc.to_name(interbank_network_server_name), o);
			System.out.println("Interbank Network Server is running...");
		}
		catch ( Exception e ) {
			e.printStackTrace();
		}
		orb.run();
	}
	
}
