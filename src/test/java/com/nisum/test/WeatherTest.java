package com.nisum.test;

import com.nisum.rest.WeatherEndpoints;
import com.nisum.utils.CommonUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class WeatherTest {

    private static final Logger logger = LoggerFactory.getLogger(WeatherEndpoints.class);
    private static Response response;

    @BeforeTest
    public void setUp() throws Exception {
        String endpointURL = CommonUtils.readProperties().getProperty("URI");
        RestAssured.baseURI = endpointURL;
    }

    @Test
//    @DataProvider
    public void post_call() throws Exception {
        response = WeatherEndpoints.postCall("KUR_001", "Kurnool Test Station", 17, 78, 542);
        WeatherEndpoints.dataValidations(response);
    }

    @AfterTest
    public void tearDown() {
        logger.info("close()");
    }

    @Test
    public void get_call() throws Exception {
        response = WeatherEndpoints.getcall();
        WeatherEndpoints.dataValidations_get(response);

    }

    @Test
    public void delete_call() throws Exception {
        response = WeatherEndpoints.deletecall();
        WeatherEndpoints.dataValidations_delete(response);

    }

    @AfterTest
    public static void getDataprovider() {
    }

}

