package Day5;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class schemaValidation {

    @Test
    void validateSchema(){
        given()
                .when()
                .get("http://localhost:3000/store")
                .then()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("storeschema.json"));
    }
}
