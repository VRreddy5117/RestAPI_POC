package com.nisum.rest;

import com.nisum.utils.CommonUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.Properties;

public class ReqResEndpoints {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReqResEndpoints.class);
    private static Response response = null;


    //post call
    public static Response postCall(String name, String job)  {
      // Properties properties = CommonUtils.readProperties();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("job", job);

        response = RestAssured.given()
                .when()
                .contentType(ContentType.JSON)
                .body(jsonObject)
                .post(CommonUtils.readProperties("Employee_get"));

      //  Assert.assertEquals(response.getStatusCode(), "201");
        return response;
    }

    // data validations for post call
  /*  @DataProvider(name = "data-provider")
    public Object[][] dataProviderMethod() {
        return new Object[][] { { "postcall_data" }, { "Postcall_url" } };
    }*/

    public static void dataValidations(Response response) {
        JSONObject expObj = CommonUtils.readJsonFile("ReqRes.json");
        JSONObject actObt = (JSONObject) response;
        LOGGER.info("print resoonse : " +response);
        Assert.assertEquals(expObj.getJSONObject("name"), actObt.getJSONObject("name"));
        Assert.assertEquals(expObj.getJSONObject("job"), actObt.getJSONObject("job"));
    }

    public static Response getCall() {
        response = RestAssured.given()
                .when().queryParam("page", "param")
              //  .get("GET_PATH");
        .get(CommonUtils.readProperties("GET_PATH"));
       // Assert.assertEquals(response.getStatusCode(), "200", "verifyCode");
        return response;
    }
}