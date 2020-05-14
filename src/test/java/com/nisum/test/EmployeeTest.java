package com.nisum.test;

import com.nisum.rest.EmployeeEndpoints;
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

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

public class EmployeeTest {

    private static Logger log = Logger.getLogger(EmployeeEndpoints.class);
    static Response response;

    @BeforeTest
    public void setUp() throws Exception {
        String endpointURL = CommonUtils.readProperties().getProperty("Employee_path");
        RestAssured.baseURI = endpointURL;
    }

    @Test(priority = 1)
    //@DataProvider  : data sent :
    public static void postTest() throws IOException, ParseException {
        response = EmployeeEndpoints.postMethod();
        String str = response.getBody().asString();
        JSONObject jsonObject = new JSONObject(str);
        System.out.println(jsonObject);
    }

    @AfterMethod
    public static void validatepost() {
        ResponseBody body = response.getBody();
        String bodyAsString = body.asString();
        JSONObject JSONResponseBody = new JSONObject(response.asString());

        assertEquals(JSONResponseBody.getJSONObject("data").getString("name"), "Ramana");
        log.info("name verified from Response ");

        assertEquals(JSONResponseBody.getJSONObject("data").getString("salary"), "12000");
        log.info("salary verified from Response ");

        assertEquals(JSONResponseBody.getJSONObject("data").getString("age"), "25");
        log.info("age verified from Response ");


        //asserts

    }

    @Test(priority = 2)
    public static void getTest() {
        response = EmployeeEndpoints.getMethod();
        String str = response.getBody().asString();
        JSONObject jsonObject = new JSONObject(str);
        LOGGER.info("Print jsonObject : " + jsonObject);
    }

    @AfterMethod
    public static void validateget() {
        ResponseBody body = response.getBody();
        String bodyAsString1 = body.asString();

        JSONObject JSONResponseBody1 = new JSONObject(response.asString());

        assertTrue(bodyAsString1.contains("data"), "data value received from Response " + JSONResponseBody1.get("data").equals("[]"));
        LOGGER.info("employee_age received from Response");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);

    }

    @AfterMethod
    public static void getReponseValidation() {
        ResponseBody body = response.getBody();
        String bodyAsString1 = body.asString();
        JSONObject JSONResponseBody = new JSONObject(response.asString());

        assertTrue(bodyAsString1.toLowerCase().contains("status"), "Response body contains status");
        assertTrue(bodyAsString1.contains("success"), "Response body contains success");
        LOGGER.info("status received from Response : " + JSONResponseBody.get("status"));
        LOGGER.info("close");
    }
}


