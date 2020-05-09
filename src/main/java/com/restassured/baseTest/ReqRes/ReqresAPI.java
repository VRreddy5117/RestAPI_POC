package com.restassured.baseTest.ReqRes;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class ReqresAPI {

    static Response postresponse = null;
    static JSONObject jsonObject;
    private static Logger log = Logger.getLogger(String.valueOf(ReqresAPI.class));
    protected static Properties prop = null;
    static JSONParser parser;



    static {

        try {
            callingJsonResponse();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            prop = PropertiesClass1.loader_properties();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void callingJsonResponse() throws IOException, ParseException {

         parser = new JSONParser();
        Object obj = parser.parse(new FileReader("src/main/resources/Files/create.json"));
        jsonObject = (JSONObject) obj;

        System.out.println("JSON Response :: " + jsonObject);

    }


    public void postResponse()  {

        postresponse = RestAssured.given()
                .when()
                .contentType(ContentType.JSON)
                .body(jsonObject.toString())
                .post("/users");

        Response response = postresponse;
        String str = response.getBody().asString();
        System.out.println(str);

        org.json.JSONObject result = new org.json.JSONObject(str);
        String createNumber = result.getString("id");
        System.out.println(createNumber);

        Assert.assertEquals(result.getString("name"),"Annem Reddy","comparing name here");
      //  Assert.assertEquals(result.getString("createdAt"),"2020-05-09T18:12:26.588Z","comparing name here");
      //  Assert.assertEquals(result.getString("id"),"12","comparing name here");
        Assert.assertEquals(result.getString("job"),"QA Engineer","comparing name here");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode,201);

    }
}
