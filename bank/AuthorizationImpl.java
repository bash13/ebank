package ebank.bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import ebank.CardNumberException;
import ebank.InsufficientBalanceException;
import ebank.TransactionRequest;
import ebank.exception.InactiveCardException;
import ebank.exception.InsufficientWeeklyDebitMaxException;

//TODO utiliser la classe Database pour la connexion vers la base de données et y insérer les différentes requêtes
public class AuthorizationImpl extends AuthorizationPOA {
		
	/**
	 * Nom (Corba) du centre de traitement bancaire
	 */
	private String bankProcessingCenterName;
	
	private String bin;	
		
	public AuthorizationImpl(String bin, String bankProcessingCenterName) {
		this.bin=bin;
		this.bankProcessingCenterName=bankProcessingCenterName;
	}
	
	@Override
	public boolean process(TransactionRequest transaction) {
		System.out.println("Transaction Reception...");
		try {
			checkCardCharacteristics(transaction);
		} catch (InsufficientBalanceException e) {
			System.out.println("Insufficient balance.");
			System.out.println("Transaction refused...");
			return false;
		} catch (InsufficientWeeklyDebitMaxException e) {
			System.out.println("Insufficient weekly debit maximum.");
			System.out.println("Transaction refused...");
			return false;
		} catch (InactiveCardException e) {
			System.out.println("Inactive Card.");
			System.out.println("Transaction refused...");
			return false;
		} catch (CardNumberException e) {
			System.out.println("Unknown card number.");
			System.out.println("Transaction refused...");
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Transaction refused...");
			return false;
		}
		System.out.println("Transaction succeed...");
		return true;
	}	
	
	private void checkCardCharacteristics(TransactionRequest transaction) throws Exception  {
		System.out.println("Check card characteristics in progress...");
		if(doesCardExist(transaction)) {
			if(isActive(transaction.getCard_number())) {
				float debitMaxAutoriseEnCours = getDebitMaxEnCours(transaction.getCard_number());
				if(debitMaxAutoriseEnCours > transaction.getAmount()) { 						
					if(isSoldeSufficient(transaction.getCard_number(), transaction.getAmount())) { 
						debitClientAccount(transaction);
						setEnCours(transaction.getCard_number(),transaction.getAmount());
					} else throw new InsufficientBalanceException();
				} else throw new InsufficientWeeklyDebitMaxException();
			} else throw new InactiveCardException();
		} else throw new CardNumberException();
	}

	private boolean doesCardExist(TransactionRequest transaction) throws Exception {
		System.out.println("Searching card...");		
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/ebank_"+bin;
		Connection connection = DriverManager.getConnection(url,"ebank","ebank");
		if (!connection.isClosed()) {			
			ResultSet rs;
			String laRequette = "select cb_numero from ebank_cb where cb_numero = ? and cb_ccv = ? and cb_dateExpiration = ?";
			PreparedStatement pstmt = connection.prepareStatement(laRequette);
			pstmt.setString(1, ""+transaction.getCard_number());
			pstmt.setInt(2, transaction.getCcv());
			pstmt.setString(3, transaction.getDate());
			System.out.println(laRequette);
			rs = pstmt.executeQuery();
			if(rs.next()) return true;
			return false;			
		}
		return false;	
	}
	
	private boolean isActive(long numeroCarte) throws Exception {
		System.out.println("Search card status...");
		
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/ebank_"+bin;
		Connection connection = DriverManager.getConnection(url,"ebank","ebank");
		if (!connection.isClosed()) {			
			ResultSet rs;
			String laRequette = "select cb_etatCarte from ebank_cb where cb_numero = ?";
			PreparedStatement pstmt = connection.prepareStatement(laRequette);
			pstmt.setString(1, ""+numeroCarte);
			rs = pstmt.executeQuery();
			rs.next();
			if(rs.getString(1).equals("Active")) return true;
			else return false;
		}
		return false;
	}
	
	public float getDebitMaxEnCours(long numeroCarte) throws Exception { // debit max que l'encours permet de débiter
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/ebank_"+bin;
		Connection connection = DriverManager.getConnection(url,"ebank","ebank");
		if (!connection.isClosed()) {			
			ResultSet rs;
			String laRequette = "select cb_debitAutoriseMax - cb_debitAutoriseEnCours from ebank_cb where cb_numero = ?";
			PreparedStatement pstmt = connection.prepareStatement(laRequette);
			pstmt.setString(1, ""+numeroCarte);
			rs = pstmt.executeQuery();
			System.out.println(pstmt);
			if(rs.next()) return rs.getFloat(1);
			else return 0;
		}
		return 0;
	}
	
	private void debitClientAccount(TransactionRequest transaction) {
		System.out.println("Debit client account in progress...");
		try	{
			sendDebitRequestToBankProcessingCenter(transaction);
		}
		catch (Exception e)	{
			e.printStackTrace();
		}		
	}
	
	public void sendDebitRequestToBankProcessingCenter(TransactionRequest transaction) throws Exception  {
		System.out.println("Send debit request to Bank Processing Center ("+bankProcessingCenterName+")...");
		BankProcessingCenter bpc;
		org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init((String[])null, null);
		NamingContextExt nc = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));
		bpc = BankProcessingCenterHelper.narrow(nc.resolve(nc.to_name(bankProcessingCenterName)));
		bpc.debit(transaction);
	}

	private Boolean isSoldeSufficient(long numeroCarte, float montant) throws Exception {
		BankProcessingCenter bpc;
		org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init((String[])null, null);
		NamingContextExt nc = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));
		bpc = BankProcessingCenterHelper.narrow(nc.resolve(nc.to_name(bankProcessingCenterName)));
		if(bpc.getBalance(numeroCarte) > montant) return true;
		return false;
	}
	
	private boolean setEnCours(long numeroCarte, float montant) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/ebank_"+bin;
		Connection connection = DriverManager.getConnection(url,"ebank","ebank");
		if (!connection.isClosed()) {			
			String laRequette = "update ebank_cb set cb_debitAutoriseEnCours = (cb_debitAutoriseEnCours + ?) where cb_numero = ?";
			PreparedStatement pstmt = connection.prepareStatement(laRequette);
			pstmt.setFloat(1, montant);
			pstmt.setString(2, ""+numeroCarte);
			pstmt.executeUpdate();
			return true;
		}
		return false;
	}

}
