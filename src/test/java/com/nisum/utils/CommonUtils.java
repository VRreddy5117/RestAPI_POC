package com.nisum.utils;

import com.nisum.rest.ReqResEndpoints;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;

public class CommonUtils {

    public static JSONObject res;
    private static final Logger logger = LoggerFactory.getLogger(ReqResEndpoints.class);


    public static String readProperties(String key) {
        Properties properties = new Properties();
        String  propValue = null;
        try {
            File file = new File("src/test/resources/application.properties");
            properties.load(new FileInputStream(file));
             propValue = properties.get(key).toString();
        }catch (Exception e){
            logger.debug(e.getMessage());
        }
        return propValue;
    }


    public static JSONObject readJsonFile(String fileName) {
        JSONObject jsonObject = new JSONObject();
        try {
            File reader = new File("src/test/resources/data/" + fileName + "");
            jsonObject = new JSONObject(FileUtils.readFileToString(reader, "uft-8"));
        } catch (Exception e) {
            logger.debug(e.getMessage());
        }
        return jsonObject;

    }

}

