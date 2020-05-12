package com.nisum.rest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.util.Properties;

public class EmployeeEndpoints {
    private static Logger log = Logger.getLogger(EmployeeEndpoints.class);
    static Response response = null;
    static JSONObject jsonObject;
    protected static Properties prop = null;
    static  String station_id;

    public static Response postMethod() {

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(jsonObject.toString())
                .post("/create");
        return response;
    }

    public static Response getMethod() {
        response = RestAssured.given()
                .when().queryParam("appid", prop.getProperty("APP_ID"))
                .get("/stations/" + station_id);
        return response;
    }
}
