package ebank.network;

import java.sql.SQLException;

import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import ebank.TransactionRequest;
import ebank.bank.Acquisition;
import ebank.bank.AcquisitionHelper;
import ebank.data.Database;
import ebank.data.UnknowBinException;

public class InterbankNetworkImpl extends InterbankNetworkPOA {

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
			System.out.println("Searching correponding bank...");
			bankName = db.findAcquisitionServerNameFromBin(Integer.parseInt(transaction.getBin()));
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
		System.out.println("Transfering transaction to corresponding Acquisition server ("+acquisitionServerName+")...");
		Acquisition acq;
		org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init((String[])null, null);
		NamingContextExt nc = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));
		acq = AcquisitionHelper.narrow(nc.resolve(nc.to_name(acquisitionServerName)));
		return acq.process(transaction);
	}
	
}