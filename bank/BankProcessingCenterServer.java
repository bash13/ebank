package ebank.bank;

import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import ebank.CardNumberException;
import ebank.InsufficientBalanceException;

class BankProcessingCenterImpl extends BankProcessingCenterPOA {

	/**
	 * Crédite le numéro de compte de la somme indiquée
	 */
	@Override
	public boolean credit(long cardNumber, float amount)
			throws CardNumberException {
		return false;
	}

	/**
	 * Débite le compte de la somme indiquée
	 */
	@Override
	public boolean debit(long cardNumber, float amount)
			throws InsufficientBalanceException, CardNumberException {
		return false;
	}

	/**
	 * Renvoie le solde du compte demandée
	 */
	@Override
	public boolean getBalance(long cardNumber) throws CardNumberException {
		if (!checkCardNumber(cardNumber))
			throw new CardNumberException("Carte inconnue");
		return false;
	}
	
	/**
	 * Vérifier la validité du numéro de la carte bancaire
	 * @param cardNumber
	 * @return
	 */
	public boolean checkCardNumber(long cardNumber)
	{
		return false;		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}

/**
 * Classe représentation le serveur d'autorisation
 * @author alex
 *
 */
public class BankProcessingCenterServer {
	
	/**
	 * Nom (Corba) du serveur d'acquisition
	 */
	private static final String bank_processing_center_server_name = "CTB";

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
			org.omg.CORBA.Object o = poa.servant_to_reference(new BankProcessingCenterImpl());
			NamingContextExt nc =
			NamingContextExtHelper.narrow(
			orb.resolve_initial_references("NameService"));
			nc.bind( nc.to_name(bank_processing_center_server_name), o);
			System.out.println("Bank Processing Center Server is running...");
		}
		catch ( Exception e ) {
			e.printStackTrace();
		}
		orb.run();
	}
	
}
