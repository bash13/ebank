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
	private String bin;	

	/**
	 * Nom (Corba) du serveur d'autorisation
	 */
	private String authorizationServerName;
		
	/**
	 * Nom (Corba) du centre de traitement bancaire
	 */
	private String bankProcessingCenterName;
	
	/**
	 * Nom (Corba) du réseau interbancaire
	 */	
	private String interbankNetworkName;
		
	/**
	 * Demande de transaction
	 */	
	private TransactionRequest trans_request;

	public AcquisitionImpl(String bin, String authorizationServerName, String bankProcessingCenterName, String interbankNetworkName) {
		this.bin=bin;
		this.authorizationServerName=authorizationServerName;
		this.interbankNetworkName=interbankNetworkName;
		this.bankProcessingCenterName=bankProcessingCenterName;
	}
	
	@Override
	public boolean process(TransactionRequest transaction) {
		trans_request=transaction;
		if (!trans_request.getBin().equals(bin))
		{
			System.out.println("Transfer to Interbank Network("+interbankNetworkName+")");
			return transferTransactionRequest();
		}
		else {
			System.out.println("Transaction request processing...");
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
		in = InterbankNetworkHelper.narrow(nc.resolve(nc.to_name(interbankNetworkName)));
		if (in.transfer(trans_request)) {
			creditDealerAccount();
			return true;
		}
		return false;
			
	}
	
	private boolean treatTransactionRequest()
	{
		if (authorize()) {
			creditDealerAccount();
			System.out.println("Transaction succeed...");
			return true;
		}
		System.out.println("Transaction refused...");
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
		System.out.println("Ask authorization to Authorization Server ("+authorizationServerName+")...");
		Authorization auth;
		org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init( (String[])null, null);
		NamingContextExt nc = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));
		auth = AuthorizationHelper.narrow(nc.resolve(nc.to_name(authorizationServerName)));
		return auth.process(trans_request);
	}
	
	private void creditDealerAccount() {
		try	{
			sendCreditRequestToBankProcessingCenter();
		}
		catch (Exception e)	{
			e.printStackTrace();
		}
	}
	
	private void sendCreditRequestToBankProcessingCenter() throws InvalidName, NotFound, CannotProceed, org.omg.CosNaming.NamingContextPackage.InvalidName, CardNumberException, ClassNotFoundException, SQLException {
		System.out.println("Send credit request to Bank Processing Center ("+bankProcessingCenterName+")...");		
		BankProcessingCenter bpc;
		org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init( (String[])null, null);
		NamingContextExt nc = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));
		bpc = BankProcessingCenterHelper.narrow(nc.resolve(nc.to_name(bankProcessingCenterName)));
		bpc.credit(trans_request);
	}

}

