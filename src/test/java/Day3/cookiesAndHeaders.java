package Day3;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class cookiesAndHeaders {

    @Test
    void getCookie(){
        Response res = given()
                .when()
                .get("https://www.google.com");

        String cookies = res.getCookie("AEC");
        System.out.println(cookies);
    }

    @Test
    void getCookies(){
        Response response = given()
                .when()
                .get("https://www.google.com");

        Map<String,String> map =  response.getCookies();
        for(Map.Entry<String,String> entry : map.entrySet()){
            String cookieValue = entry.getValue();
            System.out.println(entry.getKey() + " " + cookieValue);
        }
    }

    @Test
    void getHeaderInfo(){
       given()
                .when()
                .get("https://www.google.com")
               .then()
               .header("P3P","CP=\"This is not a P3P policy! See g.co/p3phelp for more info.\"")
               .and()
               .header("Content-Encoding","gzip");


//       String header = res.getHeader("P3P");
//        System.out.println(header);
    }

    @Test
    void getHeaders(){
        Response res = given()
        .when()
                .get("https://www.google.com");

        Headers header = res.getHeaders();
        for(Header head:header){
            System.out.println(head.getName() + " " + head.getValue());
        }
    }

    @Test
    void logging(){
        given()
                .when()
                .get("https://www.google.com")
                .then()
                .log().headers().and().log().cookies();
    }
}


