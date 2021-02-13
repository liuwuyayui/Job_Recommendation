package com.laioffer.job.db;

import com.laioffer.job.config.PropertyReader;

public class MySQLDBUtil {
  
  public static String url() {
    String INSTANCE = PropertyReader.getProperty("db", "INSTANCE");
    String PORT_NUM = PropertyReader.getProperty("db", "PORT_NUM");
    String DB_NAME = PropertyReader.getProperty("db", "DB_NAME");
    String USERNAME = PropertyReader.getProperty("db", "USERNAME");
    String PASSWORD = PropertyReader.getProperty("db", "PASSWORD");
    return "jdbc:mysql://"
                   + INSTANCE + ":" + PORT_NUM + "/" + DB_NAME
                   + "?user=" + USERNAME + "&password=" + PASSWORD
                   + "&autoReconnect=true&serverTimezone=UTC";
  }
}
