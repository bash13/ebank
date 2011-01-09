package ebank.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe représentation la base de donneés
 * @author aleks
 *
 */
public class Database {
	
	Connection conn;	

	/**
	 * Nom de l'utilisateur de la base de donneés
	 */
	private static final String user_name = "ebank";	

	/**
	 * Mot de passe de l'utilisateur de la base de donneés
	 */
	private static final String password = "ebank";
	
	/**
	 * Mot de passe de l'utilisateur de la base de donneés
	 */
	private static final String url = "jdbc:mysql://localhost/qcm?useUnicode=true&characterEncoding=UTF-8&characterSetResults=UTF-8";	
	
	/**
	 * Constructeur de la classe Database. Crée/ouvre la connexion avec la base de données.
	 */
	public Database()
	{
        try
        {	
            Class.forName ("com.mysql.jdbc.Driver").newInstance ();
            conn = DriverManager.getConnection (url, user_name, password);
        }
        catch (Exception e)
        {
        	System.out.println(e);
        }
	}
	
	/**
	 * Ferme la connexion établie avec la base de données
	 * @throws SQLException
	 */
	public void close() throws SQLException
	{
		conn.close();
	}
	
	/**
	 * Renvoie le nom (Corba) du serveur d'acquisition de la banque correspodant
	 * au bin.
	 * @param bin Bank Identify Number de la banque.
	 * @return Nom (Corba) du serveur d'acquisition
	 * @throws SQLException
	 */
	public String getAcquisitionServerNameFromBin(Integer bin) throws SQLException
	{
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT name FROM annuaire WHERE bin='"+bin+"';");
		
		if (rs.next())
			return rs.getString("name");
		else 
			return null;
	}
	
	public static void main(String arg[])
	{
		Database db = new Database();
	}

	
}
