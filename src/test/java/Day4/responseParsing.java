package Day4;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class responseParsing {
    @Test
    void Approach1(){
        //Approach 1
        given().
                contentType(ContentType.JSON)
                .when()
                .get("http://localhost:3000/store")
                .then()
                .body("book[0].author",equalTo("MC Crawling"));

    }


    @Test
    void Approach2(){
        baseURI = "http://localhost:3000/store";
        Response res = given()
                .contentType(ContentType.JSON)
                .when()
                .get(baseURI);

        String author = res.jsonPath().get("book[0].author").toString();
        Assert.assertEquals(author,"MC Crawling");

    }

    @Test
    void findParticularObjectElement(){
        baseURI = "http://localhost:3000/store";
        Response res = given()
                .contentType("application/json")
                .when()
                .get(baseURI);

        JSONObject obj = new JSONObject(res.asString());
        HashSet<String> set = new HashSet<>();
        for(int i = 0;i < obj.getJSONArray("book").length();i++){
           String titleObj = obj.getJSONArray("book").getJSONObject(i).get("title").toString();
           set.add(titleObj);
        }

        Assert.assertTrue(set.contains("Smooth waves"));
    }
}
