package ebank.bank;

import java.sql.SQLException;

import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import ebank.CardNumberException;
import ebank.TransactionRequest;
import ebank.network.InterbankNetwork;
import ebank.network.InterbankNetworkHelper;


/**
 * Classe représentation l'implémentation du serveur d'acquisition
 * @author alex
 *
 */
public class AcquisitionImpl extends AcquisitionPOA {

	/**
	 * Bank Identify Number
	 */
	private static final String BIN = "974";	

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

	public void AcquisitionImpl() {}
	
	@Override
	public boolean process(TransactionRequest transaction) {
		trans_request=transaction;
		if (!trans_request.getBin().equals(BIN))
			return transferTransactionRequest();
		else {
			return treatTransactionRequest();
		}
	}
	
	private boolean transferTransactionRequest() {
		try	{
			return transferRequestToInterbankNetwork();
		}
		catch (Exception e)	{
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean transferRequestToInterbankNetwork() throws Exception {
		InterbankNetwork in;
		org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init( (String[])null, null);
		NamingContextExt nc = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));
		in = InterbankNetworkHelper.narrow(nc.resolve(nc.to_name(interbank_network_name)));
		return in.transfer(trans_request);
	}
	
	private boolean treatTransactionRequest()
	{
		if (authorize()) {
			creditClientAccount();
			return true;
		}
		return false;
	}
	
	private boolean authorize() {
		try {
			return askAuthorizationToAuthorizationServer();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private boolean askAuthorizationToAuthorizationServer() throws Exception {
		Authorization auth;
		org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init( (String[])null, null);
		NamingContextExt nc = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));
		auth = AuthorizationHelper.narrow(nc.resolve(nc.to_name(authorization_server_name)));
		return auth.process(trans_request);
	}
	
	private void creditClientAccount() {
		try	{
			sendCreditRequestToBankProcessCenter();
		}
		catch (Exception e)	{
			e.printStackTrace();
		}
	}
	
	private void sendCreditRequestToBankProcessCenter() throws InvalidName, NotFound, CannotProceed, org.omg.CosNaming.NamingContextPackage.InvalidName, CardNumberException, ClassNotFoundException, SQLException {
		BankProcessingCenter bpc;
		org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init( (String[])null, null);
		NamingContextExt nc = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));
		bpc = BankProcessingCenterHelper.narrow(nc.resolve(nc.to_name(bank_processing_center_name)));
		bpc.credit(trans_request);
	}

}

