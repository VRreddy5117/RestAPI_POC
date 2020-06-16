package com.nisum.rest;

import com.nisum.utils.CommonUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import static java.lang.System.getProperty;

public class EmployeeEndpoints {


    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeEndpoints.class);

    String endpointURL = CommonUtils.readProperties(getProperty("Employee_path"));

    //s RestAssured.baseURI = endpointURL;
    //post call
    public static Response postemployee(String name, int salary, String age) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("salary", salary);
        jsonObject.put("age", age);
        Response response = RestAssured.given()
                .when()
                .contentType(ContentType.JSON)
                .body(jsonObject)
                .post(RestAssured.baseURI + CommonUtils.readProperties("Employee_post"));
        Assert.assertEquals(response.getStatusCode(), 201);
        return response;
    }

    // data validations for post call
    public static void dataValidations(Response response) {
        JSONObject expObj = CommonUtils.readJsonFile("employee.json");
        JSONObject actObt = new JSONObject(response.getBody().asString());
        Assert.assertEquals(expObj.getJSONObject("name"), actObt.getJSONObject("name"));
    }

    public static Response getEmployees() {
        Response response = RestAssured.given()
                .when()
                .get(CommonUtils.readProperties("Employee_get"));
        Assert.assertEquals(response.getStatusCode(), 200);
        return response;
    }

    public static void dataValidations_get(Response response) throws Exception {
        JSONObject expObj = CommonUtils.readJsonFile("employee.json");
        JSONObject actObt = (JSONObject) response;
        Assert.assertEquals(expObj.getJSONObject("name"), actObt.getJSONObject("name"));
        Assert.assertEquals(expObj.getJSONObject("age"), actObt.getJSONObject("age"));
        Assert.assertEquals(expObj.getJSONObject("salary"), actObt.getJSONObject("salary"));
    }

    public static Response deleteEmployee(int age) {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                //  .delete("Employee_delete");
                .delete(CommonUtils.readProperties("Employee_delete +"));
        Assert.assertEquals(response.getStatusCode(), "200", "Did not get response");
        return response;
    }

    public static void dataValidations_delete(Response response) throws Exception {
        JSONObject expObj = CommonUtils.readJsonFile("employee.json");
        JSONObject actObt = (JSONObject) response;
        // System.out.println(response);
        LOGGER.info("print response : " + response);
        Assert.assertEquals(expObj.getJSONObject("age"), actObt.getJSONObject("age"));
        Assert.assertEquals(expObj.getJSONObject("name"), actObt.getJSONObject("name"));
        Assert.assertEquals(expObj.getJSONObject("salary"), actObt.getJSONObject("salary"));
    }

    public static void validatePatchEmployees(Response response) {
    }

/*    public static void validatePatchEmployees(Response response) {
    }

    public static Response updateEmployee(String name, int salary, int age) {
    }*/

}



