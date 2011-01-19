package ebank.network;

import java.sql.SQLException;

import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import ebank.CardNumberException;
import ebank.InsufficientBalanceException;
import ebank.TransactionRequest;
import ebank.bank.Acquisition;
import ebank.bank.AcquisitionHelper;
import ebank.data.Database;
import ebank.data.UnknowBinException;

class InterbankNetworkImpl extends InterbankNetworkPOA {

	/**
	 * Nom de l'utilisateur de la base de donne�s
	 */
	private static final String user_name = "ebank";	

	/**
	 * Mot de passe de l'utilisateur de la base de donne�s
	 */
	private static final String password = "ebank";
	
	/**
	 * Mot de passe de l'utilisateur de la base de donne�s
	 */
//	private static final String url = "jdbc:mysql://localhost/ebank_directory?useUnicode=true&characterEncoding=UTF-8&characterSetResults=UTF-8";	
	private static final String url = "jdbc:mysql://localhost/ebank_directory";	
		
	/**
	 * Connexion � la base de donn�es
	 */
	private Database db;
	
	private TransactionRequest transaction;

	/**
	 * Constructeur. Cr�e la connexion � la base.
	 */
	public InterbankNetworkImpl()
	{
		 db = new Database(user_name, password, url);
		 db.connect();
	}
	
	/**
	 * Transfert de transaction vers la banque emettrice
	 */
	@Override
	public boolean transfer(TransactionRequest transaction) {
		this.transaction = transaction;
		return findAcquisitionServerNameAndTransferTransactionRequest();
	}
	
	private boolean findAcquisitionServerNameAndTransferTransactionRequest() {
		String bankName="";
		try {
			bankName = db.findAcquisitionServerNameFromBin(transaction.getBin());
			return transferToCorrespondingBankAcquisitionServer(bankName);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (UnknowBinException e) {
			System.out.println("BIN inconnu: Aucune entrée disponible dans l'annuaire.");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private boolean transferToCorrespondingBankAcquisitionServer(String acquisitionServerName) throws Exception {
		Acquisition acq;
		org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init();
		NamingContextExt nc = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));
		acq = AcquisitionHelper.narrow(nc.resolve(nc.to_name(acquisitionServerName)));
		return acq.process(transaction);
	}
	
}

/**
 * Classe représentation le serveur d'autorisation
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
