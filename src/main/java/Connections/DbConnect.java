package Connections;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;


public class DbConnect implements ConnectionProvider {

    private MysqlDataSource datasource = new MysqlDataSource();

    public DbConnect() {
        String password = getProperties().getProperty("password");
        String serverName = getProperties().getProperty("host");
        String user = getProperties().getProperty("user");
        String dbName = getProperties().getProperty("dbName");

        datasource.setUser(user);
        datasource.setServerName(serverName);
        datasource.setPassword(password);
        datasource.setDatabaseName(dbName);
    }

    public Properties getProperties(){
        Properties prop = new Properties();
        try {
            datasource.setUseSSL(false); //removes warnings
            prop.load(new FileInputStream("src/main/resources/loginInfo.properties"));
            return prop;
        } catch (IOException | SQLException e) {
            System.out.println("Error with Properties file");
            e.printStackTrace();
        }
        return prop;
    }

    @Override
    public Connection getConnection() throws IOException, SQLException, ClassNotFoundException {
        return datasource.getConnection();
    }
}
