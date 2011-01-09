package ebank.bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import ebank.CardNumberException;
import ebank.InsufficientBalanceException;
import ebank.TransactionRequest;

class BankProcessingCenterImpl extends BankProcessingCenterPOA {
	
	/**
	 * Crédite le numéro de compte de la somme indiquée
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	@Override
	public boolean credit(TransactionRequest transaction)
			throws CardNumberException, ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/ebank_"+transaction.getBin();
		Connection connection = DriverManager.getConnection(url,"ebank","ebank");
		if (!connection.isClosed()) {			
			String laRequette = "update ebank_compte set compte_solde = (compte_solde + ?) where compte_numero = ?";
			PreparedStatement pstmt = connection.prepareStatement(laRequette);
			pstmt.setFloat(1, transaction.getAmount());
			pstmt.setLong(2, transaction.getDealer_account_number());
			pstmt.executeUpdate();
			
			laRequette = "insert into ebank_transaction (transaction_date, transaction_montant, transaction_cbNumero,transaction_etat, transaction_libelle, transaction_isCbTransaction) values (CURRENT_DATE, ?, ?, ?, ?, 1)";
			pstmt = connection.prepareStatement(laRequette);
			pstmt.setFloat(1, transaction.getAmount());
			pstmt.setString(2, "");
			pstmt.setString(3, "");
			pstmt.setString(4, "Vente E-Com");
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
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/ebank_"+transaction.getBin();
		Connection connection = DriverManager.getConnection(url,"ebank","ebank");
		if (!connection.isClosed()) {			
			String laRequette = "update ebank_compte set compte_solde = (compte_solde - ?) where compte_idCB = ?";
			PreparedStatement pstmt = connection.prepareStatement(laRequette);
			pstmt.setFloat(1, transaction.getAmount());
			pstmt.setString(2, ""+transaction.getDealer_account_number());
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
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/ebank_bnp";
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

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}
}

/**
 * Classe représentation le serveur d'autorisation
 * @author alex
 *
 */
public class BankProcessingCenterServer {
	
	/**
	 * Nom (Corba) du serveur d'acquisition
	 */
	private static final String bank_processing_center_server_name = "CTB";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);
		try	{
			org.omg.PortableServer.POA poa =
			org.omg.PortableServer.POAHelper.narrow(
			orb.resolve_initial_references("RootPOA"));
			poa.the_POAManager().activate();
			org.omg.CORBA.Object o = poa.servant_to_reference(new BankProcessingCenterImpl());
			NamingContextExt nc =
			NamingContextExtHelper.narrow(
			orb.resolve_initial_references("NameService"));
			nc.bind( nc.to_name(bank_processing_center_server_name), o);
			System.out.println("Bank Processing Center Server is running...");
		}
		catch ( Exception e ) {
			e.printStackTrace();
		}
		orb.run();
	}
	
}
