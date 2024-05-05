package Test;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Lecture1 {

    @Test
    void createUser(){
        given()
                .contentType("application/json")
                .when()
                .get("https://reqres.in/api/user?page=2")
                .then()
                .body("page",equalTo("2"))
                .statusCode(200).log().all();
    }
}
