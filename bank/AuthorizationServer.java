package ebank.bank;

import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import ebank.tools.ArgumentParser;



/**
 * Classe représentation le serveur d'autorisation
 * @author alex
 *
 */
public class AuthorizationServer {
	
	private static String bin = "974";
	private static String authorizationServerName = "SAA";
	private static String bankProcessingCenterName = "CTB";
	
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
			org.omg.CORBA.Object o = poa.servant_to_reference(new AuthorizationImpl(bin, bankProcessingCenterName));
			NamingContextExt nc =
			NamingContextExtHelper.narrow(
			orb.resolve_initial_references("NameService"));
			nc.bind( nc.to_name(authorizationServerName), o);
			System.out.println("AuthorizationServer ("+authorizationServerName+") is running...");
		}
		catch ( Exception e ) {
			e.printStackTrace();
		}
		orb.run();
	}
	
	private static void printUsage() {
        String format = "%20s - %s\n";
        System.out.printf(format, "[--<option>[=arg]]", "action:");
        System.out.println();
        System.out.printf(format, "help", "print this help");
        System.out.printf(format, "auth", "specify corba name for authorization server (default is: " + authorizationServerName + ")");
        System.out.printf(format, "center", "specify corba name for Bank process center (default is: " + bankProcessingCenterName + ")");
    }
	
	private static void parseArgumentsAndDisplayCorrespondingInformations(String[] args) {
		ArgumentParser params = new ArgumentParser(args);
		
        if (params.hasOption("help")) { 
        	printUsage(); 
        	System.exit(0); 
        }
		if (params.hasOption("center"))
			bankProcessingCenterName=params.getOption("center");
		if (params.hasOption("auth"))
			authorizationServerName=params.getOption("auth");
		if (params.hasOption("bin"))
			bin=params.getOption("bin");
		
	}
	
}
