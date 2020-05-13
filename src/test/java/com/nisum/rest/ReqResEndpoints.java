package com.nisum.rest;

import com.nisum.utils.CommonUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ReqResEndpoints {
    private static Logger log = Logger.getLogger(ReqResEndpoints.class);
    static Response response = null;
    static JSONObject jsonObject;
    protected static Properties prop = null;

    static {
        try {
            prop = CommonUtils.readProperties();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Response postMethod() {
        JSONParser parser = new JSONParser();
        try {

            // File -1 Reading
            Object obj = parser.parse(new FileReader("src/test/resources/data/Reqres.json"));

            org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) obj;
            System.out.println("File-1 JSON Response :: " + jsonObject);
            JSONObject res = new JSONObject(jsonObject.toString());

            response = RestAssured.given()
                    .when()
                    .contentType(ContentType.JSON)
                    .body(res)
                    .post(prop.getProperty("GET_PATH"));  // Read from properties file


        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public static Response getMethod() {
        response = RestAssured.given()
                .when().queryParam("page", prop.getProperty("param"))
                .get(prop.getProperty("GET_PATH")); // read from prop file

        int statusCode = response.getStatusCode();
       // Assert.assertEquals(statusCode, 200);
        // logs
        // log.warn("");
        return response;
    }
}
