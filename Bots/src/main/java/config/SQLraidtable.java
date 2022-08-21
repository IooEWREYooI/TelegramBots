package config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class SQLraidtable {

    // JDBC URL, username и пароль от MySQL 
    private static final String url = "jdbc:mysql://localhost:3306/raidtable";
    private static final String user = "root";
    private static final String password = "YashaRocketman2005@";
    public static ArrayList<String> table = new ArrayList<>();
    public static int i = 0;

    // JDBC варианты для открытия и изменения подключения
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;
    
    // Методы SQL запросов
    public static String getAllTableSortByPoints // <- Дает данные по всей таблице и сортирует по возрастанию 
    (String textFirst, String textEnd) throws SQLException {
    	String query = "SELECT * FROM raidtable ORDER BY points DESC"; 
    	int i = 0;
    	con = DriverManager.getConnection(url, user, password);
    	stmt = con.createStatement();
    	rs = stmt.executeQuery(query);
    	
    	// Пока запрос содержит символы *до конца таблицы
    	while (rs.next()) 
        {
            String text2 = rs.getString(1); 	// Значение 2 столбца 
            String text3 = rs.getString(2); 	// Значение 3 столбца 
            String tables ="";
            if(i<=9) {
            tables = textFirst+text2+textEnd+text3+", место - "+(i+1)+" 🐼";
            }
            else 
            	tables = textFirst+text2+textEnd+text3+", место - "+(i+1);
            table.add(tables);
            i++;
        }
    	// Закрытие подключения
    	con.close();
    	stmt.close();
    	rs.close();
    	return SQLraidtable.table.toString();
    }
    public static boolean addUser // <- Добавляет пользователя 
    (String name, int points) {
    	String query = "INSERT INTO raidtable(name, points) VALUES('"+name+"', "+points+")";
    	try {
    	con = DriverManager.getConnection(url, user, password);
    	stmt = con.createStatement();
    	stmt.executeUpdate(query);
    	return true;
    	}
    	catch (SQLException e) {
    		return false;
    	}
    }
    public static boolean deleteUser // <- Удаляет пользователя 
    (String name) {
    	String query = "DELETE FROM raidtable WHERE name = '"+name+"';";
    	try {
        	con = DriverManager.getConnection(url, user, password);
        	stmt = con.createStatement();
        	stmt.executeUpdate(query);
        	return true;
        	}
        	catch (SQLException e) {
        		return false;
        	}
    }
    public static boolean updatePoints // <- Обновляет значения баллов 
    (String name, int points) {
    	String query = "UPDATE raidtable SET points="+points+" WHERE name='"+name+"';";
    	try {
        	con = DriverManager.getConnection(url, user, password);
        	stmt = con.createStatement();
        	stmt.executeUpdate(query);
        	return true;
        	}
        	catch (SQLException e) {
        		return false;
        	}
    }
    public static void placeOfUser() {
    	
    }
}