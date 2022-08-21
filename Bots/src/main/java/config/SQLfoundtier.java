package config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SQLfoundtier {

    /////// JDBC URL, username и пароль от MySQL ///////
    private static final String url = "jdbc:mysql://localhost:3306/foundtier"; // <- Имя БД
    private static final String user = "root";
    private static final String password = "YOUR_PASSWORD";
    public static ArrayList<String> table = new ArrayList<>();
    
    /////// JDBC варианты для открытия и изменения подключения ///////
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;
    
    
    public ArrayList<String> getAllTable // <- Дает данные по всей таблице  
    (String textFirst, String textSecond, String textEnd) throws SQLException {
    	String query = "SELECT * FROM foundtier"; 
    	con = DriverManager.getConnection(url, user, password);
    	stmt = con.createStatement();
    	rs = stmt.executeQuery(query);
    	
    	// Пока запрос содержит символы *до конца таблицы
    	while (rs.next()) 
        {
            String text1 = rs.getString(1); 	// Значение 1 столбца 
            String text2 = rs.getString(2); 	// Значение 2 столбца  
            String tables = textFirst+text1+textSecond+text2+textEnd;
            table.add(tables);
        }
    	// Закрытие подключения
    	con.close();
    	stmt.close();
    	rs.close();
    	return table;
    }
    public boolean addFoundWithOutURL // <- Добавляет фонд 
    (String nameF, double tierF) throws ClassNotFoundException {
    	String query = "INSERT INTO foundtier(nameF, tierF) VALUES('"+nameF+"', "+tierF+")";
    	try {
    	con = DriverManager.getConnection(url, user, password);
    	stmt = con.createStatement();
    	stmt.executeUpdate(query);
    	return true;
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    public String getTierOfFound // <- Получить тир фонда 
    (String nameOfFound) throws SQLException {
    	String query = "SELECT tierF FROM foundtier WHERE namef = '"+nameOfFound.toLowerCase()+"';";
    	String tierINT = "0";
    	
    	con = DriverManager.getConnection(url, user, password);
    	stmt = con.createStatement();
    	rs = stmt.executeQuery(query);
    	
    	while(rs.next()) {
    			tierINT = rs.getString(1);
    		}
    	return tierINT;
    }

}
