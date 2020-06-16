package com.nisum.test;

import com.nisum.rest.EmployeeEndpoints;
import com.nisum.utils.CommonUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import java.lang.reflect.Method;

import static java.lang.System.getProperty;

public class EmployeeTest {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeTest.class);
    private static Response response;

   /* @BeforeTest
    public void setUp()  {
        String endpointURL = CommonUtils.readProperties(getProperty("Employee_path"));
        RestAssured.baseURI = endpointURL;
    }*/

    @BeforeClass(description = "Initializes URI",enabled = true)
    public void setUp(){
        RestAssured.baseURI = CommonUtils.readProperties(getProperty("Employee_path"));
    }

    @Test(dataProvider = "employeeData")
    public void post_call(String name,int salary, String age) {
        response = EmployeeEndpoints.postemployee(name, salary,age);
        EmployeeEndpoints.dataValidations(response);
    }

    @Parameters({ "name" })
    @Test
    public void testSingleString(String Name) {
        System.out.println("Invoked value " + Name);
        assert "Ramana".equals(Name);
    }

   /* @DataProvider
    public Object[][] employeeData() {
        return new Object[] {{"Ramana",12000,"29"}};
    }*/

    @DataProvider
    public Object[][] employeeData() {
        return new Object[][] {
                new Object[] { "Ramana", 12000, "25" },
        };
    }

    //This method will provide data to any test method that declares that its Data Provider
//is named "test1"
    @DataProvider(name = "test1")
    public Object[][] createData1() {
        return new Object[][] {
                { "Ramana", "12000", "25"},
        };
    }

    @Test(dataProvider = "test1")
    public void verifyData1(String name, String age, String salary) {

        logger.info("print json: " +name);
        logger.info("print json: " +salary);
        logger.info("print json: " +age);
    }

    @DataProvider(name = "dp")
    public Object[][] createData(Method postcall) {
        logger.info("printName : " +postcall.getName() );
        return new Object[][] { new Object[] { "Ramana"}};
    }

    @Test(dataProvider = "dp")
    public void test1(String salary) {
    }

    @Test(dataProvider = "dp")
    public void test2(String name) {
    }

    @Test(dataProvider = "dp")
    public void test3(String age) {
    }

    @Test
//    @DataProvider
    public void get_call() throws Exception {
        response = EmployeeEndpoints.getEmployees();
        EmployeeEndpoints.dataValidations_get(response);
    }

    @Test(dataProvider = "deleteEmployee")
    public void delete_call(String id) throws Exception {
        response = EmployeeEndpoints.deleteEmployee(id);
        EmployeeEndpoints.dataValidations_delete(response);
    }

    @DataProvider
    public Object[][] deleteEmployee() {
        return new Object[][] {
                new Object[] {"88"},
        };
    }

    @AfterTest
    public void tearDown() {
        logger.info("close()");
    }
}


