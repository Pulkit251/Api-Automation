package Day7;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CreateUser {

    @Test
    void create(ITestContext context){
        Faker faker = new Faker();
        JSONObject obj = new JSONObject();
        obj.put("name",faker.name().fullName());
        obj.put("gender","male");
        obj.put("email",faker.internet().emailAddress());
        obj.put("status","inactive");

        String bearerToken = "751c015a631b8f13e6ee4c53bfaad593156067536b86dacb5e1b314cae3f98b2";

        Response res = given()
                .headers("Authorization","Bearer " + bearerToken)
                .contentType(ContentType.JSON)
                .body(obj.toString())
                .when()
                .post("https://gorest.co.in/public/v2/users");

        int id = res.jsonPath().getInt("id");
        System.out.println(id);

        context.setAttribute("user_id",id);
    }
}
