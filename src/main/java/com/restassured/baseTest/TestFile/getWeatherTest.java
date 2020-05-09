package com.restassured.baseTest.TestFile;

import com.restassured.baseTest.Weather.GetRequest;
import com.restassured.baseTest.Weather.PropertiesClass;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class getWeatherTest {

        private static Logger log = Logger.getLogger(String.valueOf(ReqresTest.class));
        protected static Properties prop = null;
        static {
            try {
                prop = PropertiesClass.loader_properties();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    @BeforeMethod(description = "Initializes URI",enabled = true)
    public void setUp(){
        RestAssured.baseURI = prop.getProperty("URI");
    }

    @Test(description = "This method is used for validation of retriving weather details ",enabled = false,groups= {"Exclude Groups"})
    public void checkgetWeather() {
        GetRequest.getweather();
    }
}
