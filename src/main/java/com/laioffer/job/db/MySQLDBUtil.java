package com.laioffer.job.db;

import com.laioffer.job.config.Property;

public class MySQLDBUtil {
  
  public static String url() {
    String INSTANCE = Property.getProperty("db", "INSTANCE");
    String PORT_NUM = Property.getProperty("db", "PORT_NUM");
    String DB_NAME = Property.getProperty("db", "DB_NAME");
    String USERNAME = Property.getProperty("db", "USERNAME");
    String PASSWORD = Property.getProperty("db", "PASSWORD");
    return "jdbc:mysql://"
                   + INSTANCE + ":" + PORT_NUM + "/" + DB_NAME
                   + "?user=" + USERNAME + "&password=" + PASSWORD
                   + "&autoReconnect=true&serverTimezone=UTC";
  }
}
