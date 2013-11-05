package models.database.connection;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class DBHelper {
	
	public static Connection getConnection() {

        URI dbUri = null;
        try {
            dbUri = new URI(System.getenv("CLEARDB_DATABASE_URL"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];

        String dbUrl = "jdbc:mysql://" + dbUri.getHost() + dbUri.getPath();

		try {

			Class.forName("com.mysql.jdbc.Driver");

		} catch (ClassNotFoundException e) {

			System.out.println("DBHelper: Check Where  your mySQL JDBC Driver exist and "
                    + "Include in your library path!");
			e.printStackTrace();
			return null;

		}

		Connection connection = null;

		try {

			connection = DriverManager.getConnection(dbUrl, username, password);
			
		} catch (SQLException e) {
			// happens when databse doesn't exist yet.
			System.out.println("DBHelper: Connection Failed! Check output console");
			e.printStackTrace();
			return null;
		}

		return connection;
	}
	
	
}
