package Day1;

//import com.aerospike.client.policy.Priority;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class httpRequests {

    int id;
    @Test(priority = 1)
    public void listUsers(){
        given()
                .when()
                    .get("https://reqres.in/api/users?page=2")
                .then()
                    .statusCode(200)
                    .body("page",equalTo(2))
                    .log().all();
    }

    @Test(priority = 2)
    public void createUser(){
        HashMap<String,String> map = new HashMap<>();
        map.put("name","pulkit");
        map.put("job","trainer");

        id=given()
            .contentType("application/json")
            .body(map)
        .when()
            .post("https://reqres.in/api/users")
            .jsonPath().getInt("id");

//        .then()
//            .statusCode(201).log().all();
    }

    @Test(priority = 3,dependsOnMethods = {"createUser"})
    public void updateUser(){
        HashMap<String,String> map = new HashMap<>();
        map.put("name","Kunal");
        map.put("job","zion resident");

        given()
                .contentType("application/json")
                .body(map)
                .when()
                .put("https://reqres.in/api/users/"+id)
                .then()
                .statusCode(200).log().all();

    }

    @Test(priority = 4)
    public void deleteUser(){
        given()
                .when().delete("https://reqres.in/api/users/"+id)
                .then()
                .statusCode(204);
    }
}
