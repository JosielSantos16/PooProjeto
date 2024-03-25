package BancoDeDados;

import java.sql.Connection;
import java.sql.SQLException;

public class ConectaBanco {

    private static ConectaBanco instance;
    private Connection connection;

    public static ConectaBanco getInstance() {
        if (instance == null) {
            instance = new ConectaBanco();
        }
        return instance;
    }

    private ConectaBanco() {

    }

    public void connectToDatabase() throws SQLException {
        String server = "localhost";
        String port = "3306";
        String database = "my_db";
        String userName = "root";
        String password = "";
        connection = java.sql.DriverManager.getConnection("jdbc:mysql://" + server + ":" + port + "/" + database, userName, password);
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
