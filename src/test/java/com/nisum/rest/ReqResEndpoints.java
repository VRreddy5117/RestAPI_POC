package com.nisum.rest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.json.JSONObject;

public class ReqResEndpoints {
    private static Logger log = Logger.getLogger(ReqResEndpoints.class);
    static Response response = null;
    static JSONObject jsonObject;

    public static Response postMethod() {
        response = RestAssured.given()
                .when()
                .contentType(ContentType.JSON)
                .body(jsonObject.toString())
                .post("GET_PATH");  // Read from properties file
        log.warn("");
        return response;
    }

    public static Response getMethod() {
        response = RestAssured.given()
                    .when().queryParam("page", "2")
                .get("/users"); // read from prop file
        // logs
        log.warn("");
        return response;
    }
}
