package ebank.webservices;

import ebank.TransactionRequest;
import ebank.bank.AcquisitionImpl;

public class WebService {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TransactionRequest transaction = new TransactionRequest(Long.parseLong("4973216382122585"), 790, "", 50, 2, 973);
		AcquisitionImpl acq = new AcquisitionImpl("974", "SAA1", "CTB1", "SIT");
		System.out.println("Appel du serveur d'acquisition");
		if (acq.process(transaction))
			System.out.println("Transaction succeed!!");
		else 
			System.out.println("Transaction failed!!");			
	}

}
