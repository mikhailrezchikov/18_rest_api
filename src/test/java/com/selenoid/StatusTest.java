package com.selenoid;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;


public class StatusTest {

     /*
    1. Make request to https://selenoid.autotests.cloud/status
    2. Get response { total: 20, used: 0, queued: 0, pending: 0, browsers: ...
    3. Check total is 20
     */

    @Test
    void CheckTotalMini() {
        get("https://selenoid.autotests.cloud/status")
                .then()
                .body("total", is(20));
    }

    @Test
    void CheckTotal() {
        given()
                .when()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .body("total", is(20));
    }
    @Test
    void CheckTotalWithLogs() {
        given()
                .log().uri()
                .log().method()
                .log().body()
                .when()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .log().status()
                .log().body()
                .body("total", is(20));
    }

    @Test
    void CheckTotalWithStatus() {
        given()
                .log().uri()
                .log().method()
                .log().body()
                .when()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("total", is(20));
    }

    @Test
    void CheckTotalWithChrome() {
        given()
                .log().uri()
                .log().method()
                .log().body()
                .when()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("total", is(20))
                .body("browsers.chrome", hasKey(100.0));
    }
}
