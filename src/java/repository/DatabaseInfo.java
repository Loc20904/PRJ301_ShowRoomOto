package repository;


public interface DatabaseInfo {
    //chinh database name voi cai server de connect database
    public static String DRIVERNAME="com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static String DBURL="jdbc:sqlserver://LAPTOP-MBUNCHF8\\SQLEXPRESS;databaseName=CarBookingDB;encrypt=false;trustServerCertificate=false;loginTimeout=30;";
    public static String USERDB="sa";
    public static String PASSDB="123";
   
}
