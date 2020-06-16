package com.nisum.test;

import com.nisum.rest.EmployeeEndpoints;
import com.nisum.rest.ReqResEndpoints;
import com.nisum.utils.CommonUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import static java.lang.System.getProperty;

public class ReqResTest {

    private static final Logger logger = LoggerFactory.getLogger(ReqResEndpoints.class);
    private static Response response;

    @BeforeTest
    public void setUp() {
        String endpointURL = CommonUtils.readProperties(getProperty("REQRES_ENDPOINT"));
        RestAssured.baseURI = endpointURL;
    }

    @Test(dataProvider = "createData1")
    public void post_call(String name, String job)  {
        response = ReqResEndpoints.postCall(name,job);
        ReqResEndpoints.dataValidations(response);
        logger.info(name + "" + job);
    }
    /*@DataProvider
    public Object[][] employeeData() {
        return new Object[][] {
                new Object[] { "venkat", "software Engineer"},
        };
    }*/

    @DataProvider(name = "test1")
    public Object[][] createData1() {
        return new Object[][] {
                { "Venkat", "Software Engineer" },
        };
    }

    @Test
    public void getCall(){
        response = ReqResEndpoints.getCall();
        ReqResEndpoints.dataValidations(response);
    }


    @AfterTest
    public void tearDown() {
        logger.info("close()");
    }
}