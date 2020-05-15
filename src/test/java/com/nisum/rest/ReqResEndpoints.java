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
    public static Response postCall(String name, String job) throws Exception {
        Properties properties = CommonUtils.readProperties();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("", name);
        jsonObject.put("", job);

        response = RestAssured.given()
                .when()
                .contentType(ContentType.JSON)
                .body(jsonObject)
                .post(properties.getProperty("GET_PATH"));
        Assert.assertEquals(response.getStatusCode(), "201");
        return response;
    }

    // data validations for post call
    public static void dataValidations(Response response) {
        JSONObject expObj = CommonUtils.readJsonFile("ReqRes.json");
        JSONObject actObt = (JSONObject) response;
        Assert.assertEquals(expObj.getJSONObject("name"), actObt.getJSONObject("name"));
    }

    public static Response getCall() {
        response = RestAssured.given()
                .when().queryParam("page", "param")
                .get("GET_PATH");
        Assert.assertEquals(response.getStatusCode(), "200", "verifyCode");
        return response;
    }
}