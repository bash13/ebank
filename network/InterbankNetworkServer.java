package ebank.network;

import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import ebank.tools.ArgumentParser;

/**
 * Classe représentation le serveur d'autorisation
 * @author alex
 *
 */
public class InterbankNetworkServer {

	private static String interbankNetworkServerName = "SIT";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		parseArgumentsAndDisplayCorrespondingInformations(args);
		
		org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init((String[])null, null);
		try	{
			org.omg.PortableServer.POA poa =
			org.omg.PortableServer.POAHelper.narrow(
			orb.resolve_initial_references("RootPOA"));
			poa.the_POAManager().activate();
			org.omg.CORBA.Object o = poa.servant_to_reference(new InterbankNetworkImpl());
			NamingContextExt nc =
			NamingContextExtHelper.narrow(
			orb.resolve_initial_references("NameService"));
			nc.bind( nc.to_name(interbankNetworkServerName), o);
			System.out.println("Interbank Network Server named "+interbankNetworkServerName+" is running...");
		}
		catch ( Exception e ) {
			e.printStackTrace();
			System.out.println("Interbank Network Server named "+interbankNetworkServerName+" is not running...");
		}
		orb.run();
	}
	
	private static void printUsage() {
        String format = "%20s - %s\n";
        System.out.printf(format, "[--<option>[=arg]]", "action:");
        System.out.println();
        System.out.printf(format, "help", "print this help");
        System.out.printf(format, "netw", "specify corba name for interbank network (default is: " + interbankNetworkServerName + ")");
   }
	
	private static void parseArgumentsAndDisplayCorrespondingInformations(String[] args) {
		ArgumentParser params = new ArgumentParser(args);
		
        if (params.hasOption("help")) { 
        	printUsage();
        	System.exit(0);
        }

		if (params.hasOption("netw"))
			interbankNetworkServerName=params.getOption("netw");
		
	}
	
}
