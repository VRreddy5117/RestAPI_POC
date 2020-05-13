package com.nisum.utils;

import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class CommonUtils {

    public static Properties readProperties() throws Exception {
        Properties properties = new Properties();
        File file = new File("src/test/resources/application.properties");
        FileInputStream fileInputStream = new FileInputStream(file);
        properties.load(fileInputStream);
        return properties;
    }

    public  static JSONObject readJson(){
        return null;
    }
}
