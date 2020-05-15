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

public class EmployeeEndpoints {


    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeEndpoints.class);
    private static Response response = null;


    //post call
    public static Response postCall(String name, String salary, int age) throws Exception {
        Properties properties = CommonUtils.readProperties();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("", name);
        jsonObject.put("", salary);
        jsonObject.put("", age);
        response = RestAssured.given()
                .when()
                .contentType(ContentType.JSON)
                .body(jsonObject)
                .post(properties.getProperty("Employee_post"));
        Assert.assertEquals(response.getStatusCode(), "201");
        return response;
    }

    // data validations for post call
    public static void dataValidations(Response response) throws Exception {
        JSONObject expObj = CommonUtils.readJsonFile("employee.json");
        JSONObject actObt = (JSONObject) response;
        Assert.assertEquals(expObj.getJSONObject("name"), actObt.getJSONObject("name"));
    }

    public static Response getCall() {
        response = RestAssured.given()
                .when()
                .get("Employee_get");
        Assert.assertEquals(response.getStatusCode(), "201");
        return response;
    }

    public static void dataValidations_get(Response response) throws Exception {
        JSONObject expObj = CommonUtils.readJsonFile("employee.json");
        JSONObject actObt = (JSONObject) response;
        Assert.assertEquals(expObj.getJSONObject("id"), actObt.getJSONObject("id"));
    }

    public static Response deleteCall() {
        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .delete("Employee_delete");
        Assert.assertEquals(response.getStatusCode(), "200", "Did not get response");
        return response;
    }

    public static void dataValidations_delete(Response response) throws Exception {
        JSONObject expObj = CommonUtils.readJsonFile("employee.json");
        JSONObject actObt = (JSONObject) response;
        Assert.assertEquals(expObj.getJSONObject("id"), actObt.getJSONObject("id"));
    }
}

