package com.nisum.rest;

import com.nisum.utils.CommonUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ReqResEndpoints extends CommonUtils {
    // private static Logger log = Logger.getLogger(ReqResEndpoints.class);
    // static JSONObject jsonObject;
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ReqResEndpoints.class);
    static Response response = null;

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

    public static Response postMethod() throws IOException, ParseException {
        Object obj;
        JSONParser parser = new JSONParser();
        obj = parser.parse(new FileReader("src/test/resources/data/Reqres.json"));
        readJson();
        response = RestAssured.given()
                .when()
                .contentType(ContentType.JSON)
                .body(res)
                .post(prop.getProperty("GET_PATH"));

        return response;
    }

    public static Response getMethod() {
        response = RestAssured.given()
                .when().queryParam("page", prop.getProperty("param"))
                .get(prop.getProperty("GET_PATH"));

        //   int statusCode = response.getStatusCode();
        return response;
    }
}
