package ebank.bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ebank.CardNumberException;
import ebank.InsufficientBalanceException;
import ebank.TransactionRequest;

public class BankProcessingCenterImpl extends BankProcessingCenterPOA {
	
	private String bin;
	
	public BankProcessingCenterImpl(String bin) {
		this.bin=bin;
	}
	
	/**
	 * Crédite le numéro de compte de la somme indiquée
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	@Override
	public boolean credit(TransactionRequest transaction)
			throws CardNumberException, ClassNotFoundException, SQLException {
		System.out.println("Credit dealer in progress...");
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/ebank_"+this.bin;
		Connection connection = DriverManager.getConnection(url,"ebank","ebank");
		if (!connection.isClosed()) {			
			String laRequette = "update ebank_compte set compte_solde = (compte_solde + ?) where compte_numero = ?";
			PreparedStatement pstmt = connection.prepareStatement(laRequette);
			pstmt.setFloat(1, transaction.getAmount());
			pstmt.setLong(2, transaction.getDealer_account_number());
			pstmt.executeUpdate();
			
			laRequette = "insert into ebank_transaction (transaction_date, transaction_montant, transaction_compteNumero, transaction_etat, transaction_libelle, transaction_isCbTransaction) values (CURRENT_DATE, ?, ?, ?, ?, 1)";
			pstmt = connection.prepareStatement(laRequette);
			pstmt.setFloat(1, transaction.getAmount());			
			pstmt.setLong(2, transaction.getDealer_account_number());
			pstmt.setString(3, "OK");
			pstmt.setString(4, "Vente E-Com");
			System.out.println(pstmt);
			pstmt.executeUpdate();
			return true;
		}
		return false;
	}

	/**
	 * Débite le compte de la somme indiquée
	 */
	@Override
	public boolean debit(TransactionRequest transaction) throws InsufficientBalanceException, CardNumberException, SQLException, ClassNotFoundException {
		System.out.println("Debit client in progress...");
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/ebank_"+bin;
		Connection connection = DriverManager.getConnection(url,"ebank","ebank");
		if (!connection.isClosed()) {			
			String laRequette = "update ebank_compte set compte_solde = (compte_solde - ?) where compte_idCB = ?";
			PreparedStatement pstmt = connection.prepareStatement(laRequette);
			pstmt.setFloat(1, transaction.getAmount());
			pstmt.setString(2, ""+transaction.getCard_number());
			pstmt.executeUpdate();
			
			laRequette = "insert into ebank_transaction (transaction_date, transaction_montant, transaction_cbNumero, transaction_etat, transaction_libelle, transaction_isCbTransaction) values (CURRENT_DATE, ?, ?, ?, ?, 1)";
			pstmt = connection.prepareStatement(laRequette);
			pstmt.setFloat(1, -transaction.getAmount());			
			pstmt.setLong(2, transaction.getCard_number());
			pstmt.setString(3, "OK");
			pstmt.setString(4, "Achat sur E-Com");
			System.out.println(pstmt);
			pstmt.executeUpdate();
			
			return true;
		}
		return false;
	}

	/**
	 * @throws ClassNotFoundException 
	 * Renvoie le solde du compte demandée
	 * @throws  
	 */
	@Override
	public float getBalance(long card_number) throws CardNumberException, SQLException, ClassNotFoundException {
		System.out.println("Get client account balance in progress...");
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/ebank_"+bin;
		Connection connection = DriverManager.getConnection(url,"ebank","ebank");
		if (!connection.isClosed()) {			
			ResultSet rs;
			String laRequette = "select SUM(compte_solde + compte_decouvertAutorise) from ebank_compte where compte_idCB = ?";
			PreparedStatement pstmt = connection.prepareStatement(laRequette);
			pstmt.setString(1, ""+card_number);
			rs = pstmt.executeQuery();
			if(rs.next()){
				return rs.getFloat(1);
			}
			else return -1;
		}
		return 0;
	}
}