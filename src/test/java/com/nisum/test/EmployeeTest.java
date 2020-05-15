package com.nisum.test;

import com.nisum.rest.EmployeeEndpoints;
import com.nisum.utils.CommonUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

public class EmployeeTest {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeTest.class);
    private static Response response;

    @BeforeTest
    public void setUp() throws Exception {
        String endpointURL = CommonUtils.readProperties().getProperty("Employee_path");
        RestAssured.baseURI = endpointURL;
    }

    @Test
//    @DataProvider
    public void post_call() throws Exception {
        response = EmployeeEndpoints.postCall("", "", 20);
        EmployeeEndpoints.dataValidations(response);
    }

    @Test
//    @DataProvider
    public void get_call() throws Exception {
        response = EmployeeEndpoints.getCall();
        EmployeeEndpoints.dataValidations_get(response);
    }

    @Test
    public void delete_call() throws Exception {
        response = EmployeeEndpoints.deleteCall();
        EmployeeEndpoints.dataValidations_delete(response);
    }

    @AfterTest
    public void tearDown() {
        logger.info("close()");
    }
}


