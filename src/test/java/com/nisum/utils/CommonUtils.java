package com.nisum.utils;

import com.nisum.rest.ReqResEndpoints;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;

public class CommonUtils {

    public static JSONObject res;
    private static final Logger logger = LoggerFactory.getLogger(ReqResEndpoints.class);


    public static Properties readProperties() throws Exception {
        Properties properties = new Properties();
        File file = new File("src/test/resources/application.properties");
        FileInputStream fileInputStream = new FileInputStream(file);
        properties.load(fileInputStream);
        return properties;
    }


    public static JSONObject readJsonFile(String fileName) {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;
        try {
            Reader reader = new FileReader("src/test/resources/data/" + fileName + "");
            jsonObject = (JSONObject) parser.parse(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

}

