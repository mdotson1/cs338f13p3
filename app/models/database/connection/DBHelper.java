package models.database.connection;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class DBHelper {

    private DBHelper() { /* prevent instantiation */ }

    // creates the database locally if cannot connect to network database.
    // this is only for local testing not on heroku
    private static Connection createLocalDatabase(String username,
                                                  String password,
                                                  String dbName) {

        try {
            final Connection conn = establishConnection(username, password,
                    "jdbc:mysql://127.0.0.1:3306/");

            Statement st = conn.createStatement();

            st.execute("CREATE DATABASE " + dbName);

            st.execute("USE " + dbName);

            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // creates a connection to local database if cannot connect to heroku.
    // this is only used for testing locally.
    private static Connection getLocalConnection() {

        final String DATABASE_NAME = "dotson_tchassem_v1";
        final String USERNAME = "root";
        final String PASSWORD = "secret";

        try {

            return establishConnection(USERNAME, PASSWORD,
                    "jdbc:mysql://127.0.0.1:3306/" + DATABASE_NAME);

        } catch (SQLException e) {
            // happens when databse doesn't exist yet.
            if (e.getErrorCode() == 1049) {
                return createLocalDatabase(USERNAME, PASSWORD, DATABASE_NAME);
            } else {
                System.out.println("DBHelper: Connection Failed! " +
                        "Check output console");
                e.printStackTrace();
                return null;
            }
        }
    }

    // only works on heroku.
    private static Connection getNetworkConnection(final URI dbUri) {
        final String username = dbUri.getUserInfo().split(":")[0];
        final String password = dbUri.getUserInfo().split(":")[1];
        final String dbUrl = "jdbc:mysql://" + dbUri.getHost()
                + dbUri.getPath();

        try {
            return establishConnection(username, password, dbUrl);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Connection establishConnection(String username,
                                                  String password, String dbUrl)
            throws SQLException {
        try {

            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException e) {

            System.out.println("DBHelper: Check Where  your mySQL JDBC Driver" +
                    " exist and Include in your library path!");
            e.printStackTrace();
            return null;

        }

        return DriverManager.getConnection(dbUrl, username, password);
    }

	public static Connection getConnection() {

        try {
            String dbEnv = System.getenv("CLEARDB_DATABASE_URL");

            if (dbEnv == null) {
                return getLocalConnection();
            } else {
                return getNetworkConnection(new URI(dbEnv));
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
	}


}
