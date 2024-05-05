package Day1;

import org.json.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class postReqBody {
    String id;
//    @Test
    void RequestBodyUsingHashmap() {
        HashMap<String,Object> map = new HashMap<>();
        map.put("name", "Shiva");
        map.put("location", "Delhi");
        map.put("phone", "9818190000");
        String[] courseArr = {"Javascript", "Csharp"};
        map.put("courses",courseArr);


        id=given()
                .contentType("application/json")
                .body(map)
        .when()
                .post("http://localhost:3000/students")
                .jsonPath().getString("id");

//        .then()
//                .statusCode(201)
//                .body("name",equalTo("Shiva"))
//                .body("courses[0]",equalTo("Javascript"))
//                .log().all();
    }

    @Test(priority = 1)
    void RequestBodyUsingOrgJson() {
        JSONObject map = new JSONObject();
        map.put("name", "Shiva");
        map.put("location", "Delhi");
        map.put("phone", "9818190000");
        String[] courseArr = {"Javascript", "Csharp"};
        map.put("courses",courseArr);


        id=given()
                .contentType("application/json")
                .body(map.toString())
                .when()
                .post("http://localhost:3000/students")
                .jsonPath().getString("id");

    }

    @Test(priority = 2)
    void ValidateUserDetails(){
        given()
                .contentType("application/json")
                .when()
                .get("http://localhost:3000/students/" + id)
                .then()
                .statusCode(200)
                .body("name",equalTo("Shiva"))
                .body("courses[0]",equalTo("Javascript"))
                .log().all();

    }



    @Test(priority = 3)
    void TestDelete(){
        given().when()
                .delete("http://localhost:3000/students/" + id)
                .then()
                .statusCode(200);
    }
}
