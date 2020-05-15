package com.nisum.test;

import com.nisum.rest.EmployeeEndpoints;
import com.nisum.rest.ReqResEndpoints;
import com.nisum.utils.CommonUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

public class ReqResTest {

    private static final Logger logger = LoggerFactory.getLogger(ReqResEndpoints.class);
    private static Response response;

    @BeforeTest
    public void setUp() throws Exception {
        String endpointURL = CommonUtils.readProperties().getProperty("REQRES_ENDPOINT");
        RestAssured.baseURI = endpointURL;
    }

    @Test
//    @DataProvider
    public void post_call() throws Exception {
        response = ReqResEndpoints.postCall("", "");
        ReqResEndpoints.dataValidations(response);
    }

    @Test
    public void getCall() throws Exception {
        response = ReqResEndpoints.getCall();
        ReqResEndpoints.dataValidations(response);
    }



    @AfterTest
    public void tearDown() {
        logger.info("close()");
    }
}