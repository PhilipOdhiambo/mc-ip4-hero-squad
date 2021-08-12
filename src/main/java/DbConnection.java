public class DbConnection {

    private String connection;
    private String userName;
    private String userPass;

    public DbConnection() {
        initializeConnection();

    }


    public void initializeConnection() {

        ProcessBuilder processBuilder = new ProcessBuilder();

        if (processBuilder.environment().get("PORT") != null) {
            // Set Heroku connection
            String host = "ec2-34-234-12-149.compute-1.amazonaws.com";
            userName= "efruqvhzekfish";
            userPass = "753e8fff14dba2b2a5d90d8c012db99b16d288be67bcea77eac2e5b833beaa7f";
            connection = "jdbc:postgresql://" + host + ":5432/de95p1vblei1va";
        }

        // Set Local connection
        connection = "jdbc:postgresql://localhost:5432/herosquad";
        userName = "philip";
        userPass = "1234";
    }


    public String getConnection() {
        return connection;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPass() {
        return userPass;
    }
}
