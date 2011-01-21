package ebank.webservices;

import ebank.TransactionRequest;
import ebank.bank.AcquisitionImpl;

public class WebService {
	
	//TODO Date à redéfinir
	public boolean treatTransactionRequest(Long cardNumber, Integer ccv, String date, Float amount, Integer dealerAccountNumber, Integer dealerBankNumber)
	{
		//TODO vérification des paramètres		
		TransactionRequest transaction = new TransactionRequest(cardNumber, ccv, date, amount, dealerAccountNumber, dealerBankNumber);
		AcquisitionImpl acq = new AcquisitionImpl("974", "SAA1", "CTB1", "SIT");
		return acq.process(transaction);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TransactionRequest transaction = new TransactionRequest(Long.parseLong("4973216382122585"), 790, "", 1, 2, 973);
		AcquisitionImpl acq = new AcquisitionImpl("974", "SAA1", "CTB1", "SIT");
		System.out.println("Appel du serveur d'acquisition");
		if (acq.process(transaction))
			System.out.println("Transaction succeed!!");
		else 
			System.out.println("Transaction failed!!");		
	}

}
