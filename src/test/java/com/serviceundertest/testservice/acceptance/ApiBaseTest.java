package com.serviceundertest.testservice.acceptance;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

@Tag("acceptanceTest")
public class ApiBaseTest {

    public String generateStringFromResource(String path) throws IOException {

        return new String(Files.readAllBytes(Paths.get(path)));

    }

    @BeforeAll
    public static void setup() {

        String port = System.getProperty("acceptanceTest.port");
        if (port == null) {
            RestAssured.port = 8080;
        }
        else{
            RestAssured.port = Integer.parseInt(port);
        }

        String basePath = System.getProperty("acceptanceTest.base");
        if(basePath==null){
            basePath = "/api/v1";
        }
        RestAssured.basePath = basePath;

        String baseHost = System.getProperty("acceptanceTest.host");
        if(baseHost==null){
            baseHost = "http://localhost";
        }
        RestAssured.baseURI = baseHost;

    }

    @Test
    public void thatACollectionIsCreatedOnPost() throws IOException {
        String jsonBody = generateStringFromResource("src/test/resources/TestUser.json");
        given()
                .with().body(jsonBody)
                .when()
                .header("Content-Type","application/json" )
                .header("Accept","application/json" )
                .request("POST", "/User")
                .then()
                .statusCode(201)
                .log().ifError();
    }



}



