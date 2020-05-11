package com.restassured.baseTest.Weather;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesClass {


    public static Properties loader_properties() throws IOException {

        File file = new File("src/main/resources/Files/config.properties");
        File file1 = new File("src/main/resources/Files/ReqresConfig");

        InputStream fileInput = null;
        InputStream fileInput1 = null;
        fileInput = new FileInputStream(file);
        fileInput1 = new FileInputStream(file1);

        Properties prop = new Properties();
        prop.load(fileInput);
        prop.load(fileInput1);

        return prop;

    }

}
