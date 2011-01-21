package ebank.bank;

import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import ebank.tools.ArgumentParser;

/**
 * Classe représentation le serveur d'acquisition
 * @author alex
 *
 */
public class AcquisitionServer {

	private static String bin = "974";
	private static String authorizationServerName = "SAA";
	private static String acquisitionServerName = "SA";
	private static String bankProcessingCenterName = "CTB";
	private static String interbankNetworkServerName = "SIT";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		parseArgumentsAndDisplayCorrespondingInformations(args);
		
		org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);
		try	{
			org.omg.PortableServer.POA poa =
			org.omg.PortableServer.POAHelper.narrow(
			orb.resolve_initial_references("RootPOA"));
			poa.the_POAManager().activate();
			org.omg.CORBA.Object o = poa.servant_to_reference(new AcquisitionImpl(bin, authorizationServerName, bankProcessingCenterName, interbankNetworkServerName));
			NamingContextExt nc =
			NamingContextExtHelper.narrow(
			orb.resolve_initial_references("NameService"));
			nc.bind( nc.to_name(acquisitionServerName), o);
			System.out.println("BANK "+bin+": AcquisitionServer " + acquisitionServerName + " is running...");
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
        System.out.printf(format, "bin", "specify bank identify number (default is: " + bin + ")");
        System.out.printf(format, "auth", "specify corba name for authorization server (default is: " + authorizationServerName + ")");
        System.out.printf(format, "acqu", "specify corba name for acquisition server (default is: " + acquisitionServerName + ")");
        System.out.printf(format, "netw", "specify corba name for interbank network (default is: " + interbankNetworkServerName + ")");
        System.out.printf(format, "center", "specify corba name for Bank processing center (default is: " + bankProcessingCenterName + ")");
    }
	
	private static void parseArgumentsAndDisplayCorrespondingInformations(String[] args) {
		ArgumentParser params = new ArgumentParser(args);
		
        if (params.hasOption("help")) { 
        	printUsage(); 
        	System.exit(0); 
        }

		if (params.hasOption("bin"))
			bin=params.getOption("bin");
		if (params.hasOption("acqu"))
			acquisitionServerName=params.getOption("acqu");
		if (params.hasOption("auth"))
			authorizationServerName=params.getOption("auth");
		if (params.hasOption("netw"))
			interbankNetworkServerName=params.getOption("netw");
		if (params.hasOption("center"))
			bankProcessingCenterName=params.getOption("center");
		
	}
	
}
