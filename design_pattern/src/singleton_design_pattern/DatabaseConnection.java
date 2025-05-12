package singleton_design_pattern;

public class DatabaseConnection {

    private String dbHost;
    private String dbUsrname;
    private String dbPassword;

    private static DatabaseConnection databaseConnection;

    // constructor is private. We don't provide access to outside, so they can't create object of this class. Smart!!
    private DatabaseConnection(String dbHost, String dbUsrname, String dbPassword) {
        this.dbHost = dbHost;
        this.dbUsrname = dbUsrname;
        this.dbPassword = dbPassword;
    }

    public static DatabaseConnection getInstance() {

        // If databaseConnection is not created previously, then and only we create first object.
        if(databaseConnection == null) {
            databaseConnection = new DatabaseConnection("127.0.0.1", "aatman", "psswd");
            return databaseConnection;
        }

        // Otherwise, we return first object everytime.
        return  databaseConnection;
    }

    public String getDbHost() {
        return dbHost;
    }

    public String getDbUsrname() {
        return dbUsrname;
    }

    public String getDbPassword() {
        return dbPassword;
    }
}
