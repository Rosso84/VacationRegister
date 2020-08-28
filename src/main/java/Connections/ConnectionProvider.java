package Connections;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionProvider {
    Connection getConnection() throws IOException, SQLException, ClassNotFoundException;
}
