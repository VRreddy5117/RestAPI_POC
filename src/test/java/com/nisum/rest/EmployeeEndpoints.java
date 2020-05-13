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

public class EmployeeEndpoints {
    private static Logger log = Logger.getLogger(EmployeeEndpoints.class);
    static Response response = null;
   // static JSONObject jsonObject;
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
            Object obj = parser.parse(new FileReader("src/test/resources/data/employee.json"));

            org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) obj;
            System.out.println("File-1 JSON Response :: " + jsonObject);
            JSONObject res = new JSONObject(jsonObject.toString());

            response = RestAssured.given()
                    .when()
                    .contentType(ContentType.JSON)
                    .body(res)
                    .post(prop.getProperty("Employee_post"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public static Response getMethod() {

        response = RestAssured.given()
                .when()
                .get(prop.getProperty("Employee_get")); // read from prop file

        int statusCode = response.getStatusCode();
      //  Assert.assertEquals(statusCode, 200);
        return response;
    }
}

