package com.serviceundertest.testservice.acceptance;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.serviceundertest.testservice.utility.ResourceReader.readFileToString;
import static io.restassured.RestAssured.given;

@Tag("acceptanceTest")
public class ExampleTest extends ApiBaseTest {
    private static final String POST_REQUEST_BODY =
            readFileToString("system.test.data/post.request.body.json");
    private static final String INVALID_POST_REQUEST_BODY =
            readFileToString("system.test.data/post.request.body_invalid.json");
    private static final String POST_REQUEST_BODY_DUPLICATE_COLLECTION_ID_DIFFERENT_CAPABILITY =
            readFileToString("system.test.data/post.request.body_duplicate.json");


    @Test
    public void thatACollectionIsCreatedOnPost() throws IOException {
        given()
                .with().body(POST_REQUEST_BODY)
                .when()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .post()
                .then()
                .statusCode(201)
                .log().ifValidationFails();
    }

    @Test
    public void thatADuplicateCollectionIsNotCreatedOnPost() throws IOException {

        given()
                .with().body(POST_REQUEST_BODY_DUPLICATE_COLLECTION_ID_DIFFERENT_CAPABILITY)
                .when()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .post()
                .then()
                .statusCode(409)
                .log().ifValidationFails();


    }

    @Test
    public void thatACollectionIsNotCreatedWithInvalidPost() {
        given()
                .with().body(INVALID_POST_REQUEST_BODY)
                .when()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .post()
                .then()
                .statusCode(400)
                .log().ifValidationFails();

    }
}
