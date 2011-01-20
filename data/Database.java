package ebank.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe repr�sentation la base de donne�s
 * @author aleks
 *
 */
public class Database {
	
	Connection conn;	

	private String userName;
	private String password;
	private String url;
	
	/**
	 * Constructeur de la classe Database. Cr�e/ouvre la connexion avec la base de donn�es.
	 */
	public Database(String userName, String password, String url)
	{
		this.userName=userName;
		this.password=password;
		this.url=url;
	}
	
	public void connect()
	{		
        try
        {	
            Class.forName ("com.mysql.jdbc.Driver").newInstance ();
            conn = DriverManager.getConnection (url, userName, password);
        }
        catch (Exception e)
        {
        	System.out.println(e);
        }
	}
	
	/**
	 * Ferme la connexion �tablie avec la base de donn�es
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
	 * @throws UnknowBinException 
	 */
	public String findAcquisitionServerNameFromBin(Integer bin) throws SQLException, UnknowBinException	{
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT name FROM directory WHERE bin='"+bin+"';");
		
		if (rs.next())
			return rs.getString("name");
		else 
			throw new UnknowBinException();
	}
	
	public static void main(String arg[])
	{
//		Database db = new Database();
	}

	
}
