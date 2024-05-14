package Day7;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UpdateUser {

    @Test
    void updateUser(ITestContext context){
        int id = (int) context.getAttribute("user_id");
        Faker faker = new Faker();
        JSONObject obj = new JSONObject();
        obj.put("name",faker.name().fullName());
        obj.put("gender","female");
        obj.put("email",faker.internet().emailAddress());
        obj.put("status","active");

        String bearerToken = "751c015a631b8f13e6ee4c53bfaad593156067536b86dacb5e1b314cae3f98b2";
        Response res = given()
                .contentType(ContentType.JSON)
                .headers("Authorization","Bearer " + bearerToken)
                .pathParam("id",id)
                .when()
                .patch("https://gorest.co.in/public/v2/users/{id}");

        String name = res.jsonPath().get("name");
        System.out.println(name);
    }
}
