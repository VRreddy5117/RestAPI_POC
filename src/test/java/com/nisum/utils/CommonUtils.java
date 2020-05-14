package com.nisum.utils;

import com.nisum.rest.ReqResEndpoints;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;

public class CommonUtils {

    public static Object JSONObject;
    public static JSONObject res;

    public static Properties readProperties() throws Exception {
        Properties properties = new Properties();
        File file = new File("src/test/resources/application.properties");
        FileInputStream fileInputStream = new FileInputStream(file);
        properties.load(fileInputStream);
        return properties;
    }

    public static void readJson() {

        final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ReqResEndpoints.class);

        Object obj = null;
        try {

            org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) obj;

            LOGGER.info("Json response : " + jsonObject);
            res = new JSONObject(jsonObject.toString());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
