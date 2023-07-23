package in.reqres;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;

public class ReqresApiTests {

    @BeforeAll
    static public void setUp() {
        RestAssured.baseURI = "https://reqres.in";
    }

    @Test
    void createUserTest() {

        String createUserData = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}"; // BAD PRACTICE

        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(createUserData)
                .when()
                .post("/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("morpheus"))
                .body("job", is("leader"))
                .body("id", response -> equalTo(response.path("id")))
                .body("createdAt", response -> equalTo(response.path("createdAt")));
    }

    @Test
    void updateUserTest() {

        String updateUserData = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"zion resident\"\n" +
                "}"; // BAD PRACTICE

        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(updateUserData)
                .when()
                .put("/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is("morpheus"))
                .body("job", is("zion resident"))
                .body("updatedAt", response -> equalTo(response.path("updatedAt")));
    }

    @Test
    void getUsersListTest() {

        given()
                .log().uri()
                .log().method()
                .log().body()
                .when()
                .get("/api/users?page=2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.size()", is(6))
                .body("data.id", everyItem(notNullValue()));
    }

    @Test
    void deleteUserTest() {

        given()
                .log().uri()
                .log().method()
                .log().body()
                .when()
                .delete("/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(204);
    }

    @Test
    void singleUserNotFoundTest() {

        given()
                .log().uri()
                .log().method()
                .log().body()
                .when()
                .get("/api/users/23")
                .then()
                .log().status()
                .log().body()
                .statusCode(404);
    }
}
