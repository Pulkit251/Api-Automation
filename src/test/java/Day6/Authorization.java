package Day6;

//Authorization - Access related
//Authentication - Valid or Not

//Types of Authentication
//1.Basic
//2.Digest
//3.Preemptive

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Authorization {

    @Test(priority = 1)
    void testBasicAuthentication(){
        given()
                .auth().basic("postman","password")
                .when()
                .get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .body("authenticated",equalTo(true))
                .log().all();
    }

    @Test(priority = 2)
    void testDigestAuthentication(){
        given()
                .auth().digest("postman","password")
                .when()
                .get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .body("authenticated",equalTo(true))
                .log().all();
    }

    @Test(priority = 3)
    void testPreemptiveAuthentication(){
        given()
                .auth().preemptive().basic("postman","password")
                .when()
                .get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .body("authenticated",equalTo(true))
                .log().all();
    }

    @Test
    void testBearerTokenAuthentication(){
        String bearerToken="ghp_bbn6pV03RwqXGOnH4S5E9Apu9uVYaC0xTiY";

        given()
                .headers("Authorization","Bearer " + bearerToken)
                .when()
                .get("https://api.github.com/user/repos")
                .then()
                .statusCode(401)
                .body("message",equalTo("Bad credentials"))
                .log().all();
    }

    void OAuth1authentication(){
        given()
                .auth().oauth("consumerKey","consumerSecret","accessToken","tokenSecret")
                .when()
                .get("url")
                .then()
                .statusCode(200);
    }

    @Test
    void ApikeyUsage(){
        Map<String,String> m = new HashMap<>();
        m.put("id","524901");
        m.put("appid","9bd223911d23eaedeecdb4a5a6a32255");
        given()
                .pathParam("mypath","data/2.5/forecast/")
                .queryParams(m)
                .when()
                .get("http://api.openweathermap.org/{mypath}")
                .then()
                .statusCode(200)
                .log().all();

    }

}
