package com.nisum.rest;

import com.nisum.utils.CommonUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

public class WeatherEndpoints {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherEndpoints.class);
    private static Response response = null;


    //post call
    public static Response postCall(String external_id, String name, int latitude, int longitude, int altitude)  {
       // Properties properties = CommonUtils.readProperties();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("", external_id);
        jsonObject.put("", name);
        jsonObject.put("", latitude);
        jsonObject.put("", longitude);
        jsonObject.put("", altitude);


        response = RestAssured.given()
                .when().queryParam("app_id", "APP_ID")
                .contentType(ContentType.JSON)
                .body(jsonObject)
               // .post(properties.getProperty("weather_path"));
                  .post(CommonUtils.readProperties("weather_path"));
        Assert.assertEquals(response.getStatusCode(), "201");
        return response;
    }

    // data validations for post call
    public static void dataValidations(Response response) {
        JSONObject expObj = CommonUtils.readJsonFile("Weather.json");
        JSONObject actObt = (JSONObject) response;
        Assert.assertEquals(expObj.getJSONObject("name"), actObt.getJSONObject("name"));
    }

    public static Response getcall( String appid) {
        response = RestAssured.given()
                .when().queryParam("appid", "API_ID")
                .contentType(ContentType.JSON)
               // .get("weather_path" + "station_id");
              .get(CommonUtils.readProperties("weather_path" + "station_id"));
        Assert.assertEquals(response.getStatusCode(), "200");
        return response;
    }

    public static void dataValidations_get(Response response) throws Exception {
        JSONObject expObj = CommonUtils.readJsonFile("Weather.json");
        JSONObject actObt = (JSONObject) response;
        Assert.assertEquals(expObj.getJSONObject("external_id"), actObt.getJSONObject("external_id"));
        Assert.assertEquals(expObj.getJSONObject("Name"), actObt.getJSONObject("Name"));
        Assert.assertEquals(expObj.getJSONObject("Latitude"), actObt.getJSONObject("Latitude"));
        Assert.assertEquals(expObj.getJSONObject("Longitude"), actObt.getJSONObject("Longitude"));
        Assert.assertEquals(expObj.getJSONObject("Altitude"), actObt.getJSONObject("Altitude"));

    }


    public static Response deletecall(String app_id) {
        response = RestAssured.given()
                .when().queryParam("appid", "API_ID")
               // .delete("weather_path");
                .delete(CommonUtils.readProperties("weather_path"));
        Assert.assertEquals(response.getStatusCode(), "404", "Did not get response");
        return response;
    }

    public static void dataValidations_delete(Response response) throws Exception {
        JSONObject expObj = CommonUtils.readJsonFile("Weather.json");
        JSONObject actObt = (JSONObject) response;
        Assert.assertEquals(expObj.getJSONObject("station_id"), actObt.getJSONObject("station_id"));
    }
}