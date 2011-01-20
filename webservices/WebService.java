package ebank.webservices;

import ebank.TransactionRequest;
import ebank.bank.AcquisitionImpl;

public class WebService {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TransactionRequest transaction = new TransactionRequest(Long.parseLong("497300000000000000"), 810, "", 0, 1);
		AcquisitionImpl acq = new AcquisitionImpl();
		System.out.println("Appel du serveur d'acquisition");
		acq.process(transaction);
	}

}
