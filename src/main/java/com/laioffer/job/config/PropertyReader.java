package com.laioffer.job.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class PropertyReader {
  public static String getProperty(String src, String propertyName) {
    Properties prop = new Properties();
    try {
      InputStream inputStream = PropertyReader.class.getResourceAsStream("/properties/" + src + ".properties");
      prop.load(inputStream);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return prop.getProperty(propertyName);
  }
}
