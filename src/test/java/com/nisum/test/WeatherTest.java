package com.nisum.test;

import com.nisum.rest.ReqResEndpoints;
import com.nisum.rest.WeatherEndpoints;
import com.nisum.utils.CommonUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.nisum.rest.WeatherEndpoints.postMethod;
import static org.testng.Assert.assertTrue;

public class WeatherTest {
    private static Logger log = Logger.getLogger(WeatherEndpoints.class);

    @BeforeTest
    public void setUp() throws Exception {
        String endpointURL = CommonUtils.readProperties().getProperty("URI");
        RestAssured.baseURI = endpointURL + CommonUtils.readProperties().getProperty("post_path");
        System.out.println(endpointURL);


    }

    @Test
    public void postTest() {
        Response response = WeatherEndpoints.postMethod();
        String str = response.getBody().asString();
        JSONObject jsonObject = new JSONObject(str);
        System.out.println(jsonObject);

        ResponseBody body = response.getBody();
        String bodyAsString = body.asString();

        JSONObject JSONResponseBody = new JSONObject(response.asString());

        assertTrue(bodyAsString.contains("latitude"),"latitude value received from Response " + JSONResponseBody.get("latitude").equals("60.01"));
        log.info("latitude received from Response ");

        assertTrue(bodyAsString.contains("longitude"),"longitude value received from Response " + JSONResponseBody.get("longitude").equals("128.02"));
        log.info("longitude received from Response ");

        assertTrue(bodyAsString.contains("altitude"),"altitude value received from Response " + JSONResponseBody.get("altitude").equals("120.98"));
        log.info("altitude received from Response ");

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 201);
    }

    @Test
    public void getTest() {
        Response response = WeatherEndpoints.getResponse();
        String str = response.getBody().asString();
        JSONObject jsonObject = new JSONObject(str);
        System.out.println(jsonObject);

        int statusCode = response.getStatusCode();
      Assert.assertEquals(statusCode, 200);


    }

    @AfterTest
    public void tearDown() {
        log.info("close");
    }






  /*  @Test
    //@DataProvider  : data sent :
    public void postTest(){
        Response response = WeatherEndpoints.postMethod();
        JSONObject jsonObject = (JSONObject)response;


        //asserts

    }*/
}
