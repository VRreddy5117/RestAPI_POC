package com.nisum.test;

import com.nisum.rest.EmployeeEndpoints;
import com.nisum.rest.ReqResEndpoints;
import com.nisum.utils.CommonUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.nisum.rest.WeatherEndpoints.postMethod;

public class EmployeeTest {

    private static Logger log = Logger.getLogger(EmployeeEndpoints.class);

    @BeforeTest
    public void setUp() throws Exception{
        String endpointURL = CommonUtils.readProperties().getProperty("REQRES_ENDPOINT");
        RestAssured.baseURI = endpointURL;
    }

    @Test
    //@DataProvider  : data sent :
    public void postTest(){
        Response response = ReqResEndpoints.postMethod();
        JSONObject jsonObject = (JSONObject)response;


        //asserts

    }

    @Test
    public void getTest(){
        postMethod();

    }

    @AfterTest
    public void tearDown(){
        log.info("close");
    }
}


