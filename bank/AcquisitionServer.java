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
 * Classe représentation le serveur d'acquisition
 * @author alex
 *
 */
public class AcquisitionServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);
		try	{
			String acquisitionServerName = "";
			org.omg.PortableServer.POA poa =
			org.omg.PortableServer.POAHelper.narrow(
			orb.resolve_initial_references("RootPOA"));
			poa.the_POAManager().activate();
			org.omg.CORBA.Object o = poa.servant_to_reference(new AcquisitionImpl());
			NamingContextExt nc =
			NamingContextExtHelper.narrow(
			orb.resolve_initial_references("NameService"));
			nc.bind( nc.to_name(acquisitionServerName), o);
			System.out.println("AcquisitionServer " + acquisitionServerName + " is running...");
		}
		catch ( Exception e ) {
			e.printStackTrace();
		}
		orb.run();
	}
	
}
