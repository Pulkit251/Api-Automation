package Day2;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class pathQueryParams {
    @Test
    void testQueryAndParams(){
        given()
                .pathParam("mypath","users")
                .queryParam("page",2)
                .queryParam("id",5)
                .when()
                .get("https://reqres.in/api/{mypath}")
                .then()
                .statusCode(200)
                .log().all();
    }


}
