package com.nisum.test;

import com.nisum.rest.EmployeeEndpoints;
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

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

public class EmployeeTest {

    private static Logger log = Logger.getLogger(EmployeeEndpoints.class);

    @BeforeTest
    public void setUp() throws Exception {
        String endpointURL = CommonUtils.readProperties().getProperty("Employee_path");
        RestAssured.baseURI = endpointURL;
    }

    @Test
    //@DataProvider  : data sent :
    public void postTest() {
        Response response = EmployeeEndpoints.postMethod();
        String str = response.getBody().asString();
        JSONObject jsonObject = new JSONObject(str);
        System.out.println(jsonObject);

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

    @Test
    public void getTest() {
        Response response = EmployeeEndpoints.getMethod();
        String str = response.getBody().asString();
        JSONObject jsonObject = new JSONObject(str);
        System.out.println(jsonObject);


        ResponseBody body = response.getBody();
        String bodyAsString1 = body.asString();

        JSONObject JSONResponseBody1 = new JSONObject(response.asString());

        assertTrue(bodyAsString1.contains("data"), "data value received from Response " + JSONResponseBody1.get("data").equals("[]"));
        log.info("employee_age received from Response ");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);


    }

    @AfterTest
    public void tearDown() {
        log.info("close");
    }
}


