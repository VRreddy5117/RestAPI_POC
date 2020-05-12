package com.nisum.test;

import com.nisum.rest.ReqResEndpoints;
import com.nisum.rest.WeatherEndpoints;
import com.nisum.utils.CommonUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.nisum.rest.WeatherEndpoints.postMethod;

public class WeatherTest {
    private static Logger log = Logger.getLogger(WeatherEndpoints.class);

    @Test
    public void setUp() throws Exception{
        String endpointURL = CommonUtils.readProperties().getProperty("URI");
        System.out.println(endpointURL);
        RestAssured.baseURI = endpointURL+CommonUtils.readProperties().getProperty("post_path");
        postMethod();
    }






  /*  @Test
    //@DataProvider  : data sent :
    public void postTest(){
        Response response = WeatherEndpoints.postMethod();
        JSONObject jsonObject = (JSONObject)response;


        //asserts

    }*/
}
