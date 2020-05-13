package com.nisum.test;

import com.nisum.rest.ReqResEndpoints;
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

public class ReqResTest {

    private static Logger log = Logger.getLogger(ReqResEndpoints.class);

    @BeforeTest
    public void setUp() throws Exception {
        String endpointURL = CommonUtils.readProperties().getProperty("REQRES_ENDPOINT");
        RestAssured.baseURI = endpointURL;
    }

    @Test
    //@DataProvider  : data sent :
    public void postTest() {
        Response response = ReqResEndpoints.postMethod();
        String str = response.getBody().asString();
        JSONObject jsonObject = new JSONObject(str);
        System.out.println(jsonObject);

        ResponseBody body = response.getBody();
        String bodyAsString = body.asString();

        JSONObject JSONResponseBody = new JSONObject(response.asString());

        assertTrue(bodyAsString.contains("id"), "name value received from Response " + JSONResponseBody.get("id").equals("793"));
        log.info("latitude received from Response ");

        assertTrue(bodyAsString.contains("empty"), "job value received from Response " + JSONResponseBody.get("empty").equals("false"));
        log.info("longitude received from Response ");

    }

    @Test
    public void getTest() {
        Response response = ReqResEndpoints.getMethod();
        String str = response.getBody().asString();
        JSONObject jsonObject = new JSONObject(str);
        System.out.println(jsonObject);

        ResponseBody body = response.getBody();
        String bodyAsString1 = body.asString();

        JSONObject JSONResponseBody1 = new JSONObject(response.asString());

        assertTrue(bodyAsString1.contains("per_page"), "company value received from Response " + JSONResponseBody1.get("per_page").equals("6"));
        log.info("latitude received from Response ");

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
        System.out.println("Test Passed");




    }

    @AfterTest
    public void tearDown() {
        log.info("close");
    }
}
