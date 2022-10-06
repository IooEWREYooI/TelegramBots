package Telegram.Bots.config.SQL;

import Telegram.Bots.config.BotsConfig;
import Telegram.Bots.service.u_bablo_bot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SQLcoingekoapitable {
    int error = 0;
    private Logger log = LoggerFactory.getLogger(SQLcoingekoapitable.class);

    /////// JDBC URL, username и пароль от MySQL ///////
    private static final String url = "jdbc:mysql://localhost:3306/coingekoapitable"; // <- Имя БД
    private static final String user = "root"; // Default
    private static final String password = BotsConfig.SQL_PASSWORD;
    public ArrayList<String> table = new ArrayList<>();
    
    /////// JDBC варианты для открытия и изменения подключения ///////
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;
    
    public ArrayList<String> idOfCrypto (String symbol) {
    String query = "SELECT id FROM coingekoapitable WHERE symbol = '"+symbol.toLowerCase()+"'";
	ArrayList<String> id = new ArrayList<String>();
        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                id.add(rs.getString(1));
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {con.close();} catch (SQLException e){log.error(e.getMessage()); error++;}
            try {stmt.close();} catch (SQLException e){log.error(e.getMessage()); error++;}
            try {rs.close();} catch (SQLException e){log.error(e.getMessage()); error++;}
            if (error == 0)
            log.info("Закрытие ->  успешно");
            else {
                log.error("Закрытие -> не успешно");
                error = 0;
            }
            return id;
        }
    }
}
