package Dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexaoDao {

    public Connection conectaBd() {
        Connection conn = null;
        try {
            String url = "jdbc:mysql://localhost:3306/cadastros?useSSL=false&user=root&password=1234";
            conn = DriverManager.getConnection(url);
        } catch (Exception erro) {

        }
        return conn;
    }
}
