package com.restassured.baseTest.TestFile;


import com.restassured.baseTest.ReqRes.ReqresAPI;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;


public class ReqresTest extends com.restassured.baseTest.ReqRes.ReqresAPI {


    ReqresAPI validation = new ReqresAPI();

    @BeforeTest(description = "This is first test for this package")
    public void callingBaseURI() {
        RestAssured.baseURI = prop.getProperty("URI");
    }

    @BeforeTest(description = "This method should run after callingBaseURI")
    public static void reponseJson() throws IOException, ParseException {
        callingJsonResponse();
    }

    @Test(description = "using reqres api creating new user" , priority = 2)
    public void postrequest() {
        postResponse();
        Response response = validation.postResponse();
        String str = response.getBody().asString();
        org.json.JSONObject result = new org.json.JSONObject(str);


        Assert.assertEquals(result.getString("name"), "Annem Reddy", "comparing name here");
        //  Assert.assertEquals(result.getString("createdAt"),"2020-05-09T18:12:26.588Z","comparing name here");
        //  Assert.assertEquals(result.getString("id"),"12","comparing name here");
        Assert.assertEquals(result.getString("job"), "QA Engineer", "comparing job here");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 201);
    }
    @Test(description = "using reqres API getting all existing users data", priority = 1)
    public void getrequest() {
        getresponse();
        Response response = validation.postResponse();
        String str = response.getBody().asString();
        org.json.JSONObject result = new org.json.JSONObject(str);


        Assert.assertEquals(result.getString("email"), "byron.fields@reqres.in", "comparing name here");
        //  Assert.assertEquals(result.getString("createdAt"),"2020-05-09T18:12:26.588Z","comparing name here");
        //  Assert.assertEquals(result.getString("id"),"12","comparing name here");
      //  Assert.assertEquals(result.getString("job"), "QA Engineer", "comparing job here");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

}


