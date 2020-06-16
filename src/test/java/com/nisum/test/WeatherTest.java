package com.nisum.test;

import com.nisum.rest.WeatherEndpoints;
import com.nisum.utils.CommonUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import java.lang.reflect.Method;

import static java.lang.System.getProperty;

public class WeatherTest {

    private static final Logger logger = LoggerFactory.getLogger(WeatherEndpoints.class);
    private static Response response;

    @BeforeTest
    public void setUp() {
        String endpointURL = CommonUtils.readProperties(getProperty("URI"));
        RestAssured.baseURI = endpointURL;
    }

    @Test(dataProvider = "employeeData")
    public void post_call(String external_id, String name, int latitude, int longitude, int altitude)  {
        response = WeatherEndpoints.postCall(external_id,name,latitude,longitude,altitude);
        WeatherEndpoints.dataValidations(response);
    }

    @DataProvider
    public Object[][] employeeData() {
        return new Object[][] {
                new Object[] { "KUR_001", "Kurnool Test Station", 17,78,542 },
        };
    }
    @AfterTest
    public void tearDown() {
        logger.info("close()");
    }

    @Test(dataProvider = "getData")
    public void get_call(String app_id) throws Exception {
        response = WeatherEndpoints.getcall(app_id);
        WeatherEndpoints.dataValidations_get(response);

    }




    @DataProvider
    public Object[][] getData() {
        return new Object[][] {
                new Object[] { "8d210e35e278338658ea419462e7490d"},
        };
    }



    @Test(dataProvider = "deleteData")
    public void delete_call(String app_id) throws Exception {
        response = WeatherEndpoints.deletecall(app_id);
        WeatherEndpoints.dataValidations_delete(response);

    }
    @DataProvider
    public Object[][] deleteData() {
        return new Object[][] {
                new Object[] { "8d210e35e278338658ea419462e7490d"},
        };
    }


    @AfterTest
    public static void closeTest() {
        logger.info(" this is closing test");
    }

}

