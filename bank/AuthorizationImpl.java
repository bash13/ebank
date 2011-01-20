package ebank.bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import ebank.CardNumberException;
import ebank.TransactionRequest;
import ebank.data.Database;

public class AuthorizationImpl extends AuthorizationPOA {

	/**
	 * Nom (Corba) du serveur d'autorisation
	 */
	private static final String acquisition_server_name = "SA";
		
	/**
	 * Nom (Corba) du centre de traitement bancaire
	 */
	private static final String bank_processing_center_name = "CTB";
	
	/**
	 * Nom (Corba) du réseau interbancaire
	 */
	private static final String interbank_network_name = "SIT";
	
	/**
	 * Nom de l'utilisateur de la base de donne�s
	 */
	private static final String user_name = "ebank";	

	/**
	 * Mot de passe de l'utilisateur de la base de donne�s
	 */
	private static final String password = "ebank";
	
	/**
	 * Mot de passe de l'utilisateur de la base de donne�s
	 */
	private static final String url = "jdbc:mysql://localhost/ebank_directory?useUnicode=true&characterEncoding=UTF-8&characterSetResults=UTF-8";	
	
	
	private TransactionRequest transaction;
	
	private Database db;
	
	String BIN="";
	
//	public void AuthorizationServer() {
//		db = new Database(user_name, password, url);
//	}
	
	/**
	 * Demande de transaction
	 */
	@Override
	public boolean process(TransactionRequest transaction) {
		BIN = ""+transaction.getBin();
		try{
			if(doesCardExist(transaction)){
				if(isActive(transaction.getCard_number())){ // La carte est active ?
					float debitMaxAutoriseEnCours = getDebitMaxEnCours(transaction.getCard_number());
					if(debitMaxAutoriseEnCours > transaction.getAmount()){ // debit hebdomadaire OK ?
						
						if(isSoldeSufficient(transaction.getCard_number(), transaction.getAmount())){ // Solde + Decouvert permettent le débit ?
							debitClientAccount(transaction);
							setEnCours(transaction.getCard_number(),transaction.getAmount());
							System.out.println("demande de transaction OK");
						}
						else{
							System.out.println("Solde insuffisant");
						}
					}
					else{
						System.out.println("Debit hebdomadaire max insuffisant");
					}
				}
				else{
					System.out.println("Carte non active");
				}
			}
			else{
				throw new CardNumberException("Carte inconnue");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	private boolean doesCardExist(TransactionRequest transaction) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		//TODO Modifier pour que la base de données soit specifique à la banque
		String url = "jdbc:mysql://localhost:3306/ebank_"+transaction.getBin();
		Connection connection = DriverManager.getConnection(url,"ebank","ebank");
		if (!connection.isClosed()) {			
			ResultSet rs;
			String laRequette = "select cb_numero from ebank_cb where cb_numero = ? and cb_ccv = ?";
			PreparedStatement pstmt = connection.prepareStatement(laRequette);
			pstmt.setString(1, ""+transaction.getCard_number());
			pstmt.setInt(2, transaction.getCcv());
			rs = pstmt.executeQuery();
			if(rs.next()) return true;
			return false;
			
		}
		return false;
	
	}
	
	private boolean isActive(long numeroCarte) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/ebank_"+BIN;
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
	
	public float getDebitMaxEnCours(long numeroCarte) throws ClassNotFoundException, SQLException { // debit max que l'encours permet de débiter
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/ebank_"+BIN;
		Connection connection = DriverManager.getConnection(url,"ebank","ebank");
		if (!connection.isClosed()) {			
			ResultSet rs;
			String laRequette = "select cb_debitAutoriseMax - cb_debitAutoriseEnCours from ebank_cb where cb_numero = ?";
			PreparedStatement pstmt = connection.prepareStatement(laRequette);
			pstmt.setString(1, ""+numeroCarte);
			rs = pstmt.executeQuery();
			if(rs.next()) return rs.getFloat(1);
			else return 0;
		}
		return 0;
	}
	
	private void debitClientAccount(TransactionRequest transaction) {
		try	{
			sendDebitRequestToBankProcessCenter(transaction);
		}
		catch (Exception e)	{
			e.printStackTrace();
		}		
	}
	
	public void sendDebitRequestToBankProcessCenter(TransactionRequest transaction) throws Exception{
		BankProcessingCenter bpc;
		org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init();
		NamingContextExt nc = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));
		bpc = BankProcessingCenterHelper.narrow(nc.resolve(nc.to_name(bank_processing_center_name)));
		bpc.debit(transaction);
	}

	private Boolean isSoldeSufficient(long numeroCarte, float montant) throws InvalidName, NotFound, CannotProceed, org.omg.CosNaming.NamingContextPackage.InvalidName, CardNumberException, ClassNotFoundException, SQLException {
		BankProcessingCenter bpc;
		org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init();
		NamingContextExt nc = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));
		bpc = BankProcessingCenterHelper.narrow(nc.resolve(nc.to_name(bank_processing_center_name)));
		if(bpc.getBalance(numeroCarte) > montant) return true;
		return false;
	}
	
	private boolean setEnCours(long numeroCarte, float montant) throws ClassNotFoundException, SQLException { // ajouter le montant à l'encours
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/ebank_"+BIN;//transaction.getBIN();;
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