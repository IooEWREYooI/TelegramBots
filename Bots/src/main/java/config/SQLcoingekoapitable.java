package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SQLcoingekoapitable {

    /////// JDBC URL, username и пароль от MySQL ///////
    private static final String url = "jdbc:mysql://localhost:3306/coingekoapitable"; // <- Имя БД
    private static final String user = "root";
    private static final String password = "YashaRocketman2005@";
    public ArrayList<String> table = new ArrayList<>();
    
    /////// JDBC варианты для открытия и изменения подключения ///////
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;
    
    public ArrayList<String> idOfCrypto (String symbol) throws SQLException {
    String query = "SELECT id FROM coingekoapitable WHERE symbol = '"+symbol.toLowerCase()+"'";
	ArrayList<String> id = new ArrayList<String>();
	
	con = DriverManager.getConnection(url, user, password);
	stmt = con.createStatement();
	rs = stmt.executeQuery(query);
	while(rs.next()) {
			id.add(rs.getString(1));
		}
	return id;
    }
}
