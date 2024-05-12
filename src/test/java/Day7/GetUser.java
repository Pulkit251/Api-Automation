package Day7;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.ITest;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetUser {

    @Test
    void getUser(ITestContext context){

        int id = (int) context.getAttribute("user_id");

        String bearerToken = "751c015a631b8f13e6ee4c53bfaad593156067536b86dacb5e1b314cae3f98b2";
        Response res = given()
                .contentType(ContentType.JSON)
                .headers("Authorization","Bearer " + bearerToken)
                .when()
                .get("https://gorest.co.in/public/v2/users/{id}");

        String name = res.jsonPath().getString("name");
        System.out.println(name);



    }
}
