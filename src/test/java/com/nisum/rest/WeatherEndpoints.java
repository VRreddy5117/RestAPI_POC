package com.nisum.rest;

import com.nisum.utils.CommonUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class WeatherEndpoints {

    private static Logger log = Logger.getLogger(WeatherEndpoints.class);
    static Response response = null;
    static org.json.JSONObject jsonObject;
    static String station_id;
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
            Object obj = parser.parse(new FileReader("src/test/resources/data/Weather.json"));

            org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) obj;
            System.out.println(" JSON Response is :: " + jsonObject);
            JSONObject res = new JSONObject(jsonObject.toString());

            System.out.println(res);
            response = RestAssured.given()
                    .when().queryParam("appid", prop.getProperty("APP_ID"))
                    .contentType(ContentType.JSON)
                    .body(res)
                    .post("weather_path");
            System.out.println(response);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }


    public static Response getResponse() {
        response = RestAssured.given()
                .when().queryParam("appid", prop.getProperty("APP_ID"))
                .get(prop.getProperty("weather_path"));
        System.out.println(response);
        return response;


    }
}

   /* RestAssured.baseURI ="http://api.openweathermap.org/data/3.0";

    Response postresponse = null;
    JSONParser parser = new JSONParser();
        try

    {

        Object obj = parser.parse(new FileReader("src/main/resources/ConfigureProperties/CreateJSON_file.json"));

        JSONObject jsonObject = (JSONObject) obj;

        String external_id = Random_numberGenerator.radomNumber();
        System.out.println(external_id);

        jsonObject.remove("external_id");
        jsonObject.put("external_id", external_id);

        System.out.println("JSON Response :: " + jsonObject);
        try {

            postresponse = RestAssured.given()
                    .when().queryParam("appid", prop.getProperty("APP_ID"))
                    .contentType(ContentType.JSON)
                    .body(jsonObject.toString())
                    .post("/stations");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Response response = postresponse;
        String str = response.getBody().asString();
        System.out.println(str);
    }*/
