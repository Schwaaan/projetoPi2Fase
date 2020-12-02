package be.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase  {

  private static String URL = "jdbc:postgresql://localhost:5432/poo-t2";
  private static String USER = "postgres";
  private static String PSWORD = "123";

  private static Connection connection;

  private DataBase() {
  }

  public static  Connection getConnection() throws SQLException {
    if (connection == null) {
      connection = DriverManager.getConnection(URL, USER, PSWORD);
    }
    return connection;
  }
}
