package repository;

public interface DatabaseInfo {

    //chinh database name voi cai server de connect database
      public static String DRIVERNAME="com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static String DBURL="jdbc:sqlserver://ACER;databaseName=CarBookingDB;encrypt=false;trustServerCertificate=true;loginTimeout=30;";
    public static String USERDB="sa";
    public static String PASSDB="123";

}
