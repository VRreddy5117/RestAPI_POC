package com.restassured.baseTest.TestFile;


import io.restassured.RestAssured;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;


public class ReqresTest extends com.restassured.baseTest.ReqRes.ReqresAPI {


    @BeforeTest(description = "This is first test for this package")
    public void callingBaseURI() {
        RestAssured.baseURI = prop.getProperty("URI");
    }

    @BeforeTest(description = "This method should run after callingBaseURI")
    public static void reponseJson() throws IOException, ParseException {
        callingJsonResponse();
    }

    @Test
    public void postrequest() {
        postResponse();
    }

}


