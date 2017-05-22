package com.dufeng;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestHive {
    
    private static String driverName = "org.apache.hive.jdbc.HiveDriver";
    
    public static void main(String[] args) throws SQLException {
      try {
        Class.forName(driverName);
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
        System.exit(1);
      }
      Connection con = DriverManager.getConnection("jdbc:hive2://hadoop252:10000/myhivebook", "", "");
      Statement stmt = con.createStatement();
      String tableName = "employee_internal";
   
      // regular hive query
      String sql = "select * from " + tableName;
      System.out.println("Running: " + sql);
      ResultSet res = stmt.executeQuery(sql);
      while (res.next()) {
        System.out.println(res.getString(1));
      }
    }


}
