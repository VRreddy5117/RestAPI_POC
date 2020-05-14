package com.nisum.test;

import com.nisum.rest.ReqResEndpoints;
import com.nisum.utils.CommonUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.nisum.rest.WeatherEndpoints.postMethod;
import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import static org.testng.Assert.assertTrue;

public class ReqResTest {

    private static Logger log = Logger.getLogger(ReqResEndpoints.class);
    static Response response;

    @BeforeTest
    public void setUp() throws Exception {
        String endpointURL = CommonUtils.readProperties().getProperty("REQRES_ENDPOINT");
        RestAssured.baseURI = endpointURL;
    }

    @Test(priority = 1)
    //@DataProvider  : data sent :
    public static void postTest() throws IOException, ParseException {
        response = ReqResEndpoints.postMethod();
        String str = response.getBody().asString();
        JSONObject jsonObject = new JSONObject(str);
        System.out.println(jsonObject);
    }

    @AfterTest
    public static void postResponseValidation() {

        ResponseBody body = response.getBody();
        String bodyAsString = body.asString();

        JSONObject JSONResponseBody = new JSONObject(response.asString());

        assertTrue(bodyAsString.contains("id"), "name value received from Response " + JSONResponseBody.get("id").equals("793"));
        LOGGER.info("latitude received from Response");

        assertTrue(bodyAsString.contains("empty"), "job value received from Response " + JSONResponseBody.get("empty").equals("false"));
        LOGGER.info("longitude received from Response");

    }

    @Test(priority = 2)
    public static void getTest() {
        Response response = ReqResEndpoints.getMethod();
        String str = response.getBody().asString();
        JSONObject jsonObject = new JSONObject(str);
        System.out.println(jsonObject);

    }

    @AfterTest
    public void getResponseValidation() {
        ResponseBody body = response.getBody();
        String bodyAsString = body.asString();

        JSONObject JSONResponseBody = new JSONObject(response.asString());

        assertTrue(bodyAsString.toLowerCase().contains("name"), "Response body contains name");
        assertTrue(bodyAsString.contains("venkat"), "Response body contains venkat");
        LOGGER.info("external_id received from Response :" + JSONResponseBody.get("name"));
        log.info("close");
    }
}
/* @Test
    public static void getvalidation() {
        ResponseBody body = response.getBody();
        String bodyAsString1 = body.asString();

        JSONObject JSONResponseBody1 = new JSONObject(response.asString());

        assertTrue(bodyAsString1.contains("per_page"), "company value received from Response " + JSONResponseBody1.get("per_page").equals("6"));
        LOGGER.info("latitude received from Response ");

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
        LOGGER.info("Test Passed");


    }*/