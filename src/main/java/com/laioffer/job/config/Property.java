package com.laioffer.job.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Property {
  public static String getProperty(String src, String propertyName) {
    Properties prop = new Properties();
    try {
      FileInputStream inputStream = new FileInputStream("src/main/java/com/laioffer/job/config/" + src + ".properties");
      prop.load(inputStream);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return prop.getProperty(propertyName);
  }
}
