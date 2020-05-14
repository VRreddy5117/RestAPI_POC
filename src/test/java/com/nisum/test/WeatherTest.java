package com.nisum.test;

import com.nisum.rest.ReqResEndpoints;
import com.nisum.rest.WeatherEndpoints;
import com.nisum.utils.CommonUtils;
import io.restassured.RestAssured;
import io.restassured.http.Headers;
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

public class WeatherTest {
    private static Logger log = Logger.getLogger(WeatherEndpoints.class);
    static Response response;

    @BeforeTest
    public void setUp() throws Exception {
        String endpointURL = CommonUtils.readProperties().getProperty("URI");
        RestAssured.baseURI = endpointURL + CommonUtils.readProperties().getProperty("post_path");
        System.out.println(endpointURL);


    }

    @Test(priority = 1)
    public static void postTest() throws IOException, ParseException {
        response = WeatherEndpoints.postMethod();
        String str = response.getBody().asString();
        JSONObject jsonObject = new JSONObject(str);
        System.out.println(jsonObject);
    }

    @Test(priority = 2)
    public static void postResponseValidation() {
        ResponseBody body = response.getBody();
        String bodyAsString = body.asString();

        JSONObject JSONResponseBody = new JSONObject(response.asString());

        assertTrue(bodyAsString.contains("latitude"), "latitude value received from Response " + JSONResponseBody.get("latitude").equals("60.01"));
        LOGGER.info("latitude received from Response");

        assertTrue(bodyAsString.contains("longitude"), "longitude value received from Response " + JSONResponseBody.get("longitude").equals("128.02"));
        LOGGER.info("longitude received from Response");

        assertTrue(bodyAsString.contains("altitude"), "altitude value received from Response " + JSONResponseBody.get("altitude").equals("120.98"));
        LOGGER.info("altitude received from Response ");

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 201);
    }

    @Test(priority = 3)
    public void getTest() {
        Response response = WeatherEndpoints.getResponse();
        String str = response.getBody().asString();
        JSONObject jsonObject = new JSONObject(str);

        LOGGER.info("print json : " + jsonObject);

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);


    }

    @AfterMethod
    public void getReposnseValidation() {
        ResponseBody body = response.getBody();
        String bodyAsString = body.asString();

        JSONObject JSONResponseBody = new JSONObject(response.asString());


        assertTrue(bodyAsString.toLowerCase().contains("external_id"), "Response body contains external_id");
        assertTrue(bodyAsString.contains("99585"), "Response body contains 99585");

        LOGGER.info("external_id received from Response :" + JSONResponseBody.get("external_id"));

        assertTrue(bodyAsString.toLowerCase().contains("name"), "Response body contains name");
        assertTrue(bodyAsString.contains("cadapa"), "Response body contains cadapa");
        LOGGER.info("name received from Response :" + JSONResponseBody.get("name"));

        assertTrue(bodyAsString.toLowerCase().contains("longitude"), "Response body contains longitude");
        assertTrue(bodyAsString.contains("62"), "Response body contains 62");

        LOGGER.info("longitude received from Response :" + JSONResponseBody.get("longitude"));


        assertTrue(bodyAsString.toLowerCase().contains("latitude"), "Response body contains latitude");
        assertTrue(bodyAsString.contains("126"), "Response body contains 126");

        LOGGER.info("latitude received from Response :" + JSONResponseBody.get("latitude"));


        assertTrue(bodyAsString.toLowerCase().contains("altitude"), "Response body contains altitude");
        assertTrue(bodyAsString.contains("118"), "Response body contains 118");

        LOGGER.info("altitude received from Response :" + JSONResponseBody.get("altitude"));


        LOGGER.info("close");
    }






  /*  @Test
    //@DataProvider  : data sent :
    public void postTest(){
        Response response = WeatherEndpoints.postMethod();
        JSONObject jsonObject = (JSONObject)response;


        //asserts

    }*/
}
