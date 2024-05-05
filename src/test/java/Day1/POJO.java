package Day1;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class POJO {
    String name;
    String location;
    String phone;
    String[] courseArr;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String[] getCourseArr() {
        return courseArr;
    }

    public void setCourseArr(String[] courseArr) {
        this.courseArr = courseArr;
    }

    String id;
//    @Test
    void TestUsingPOJO() {
        POJO data = new POJO();
        String[] courses = {"Javascript", "Csharp"};
        data.setName("Shiva");
        data.setLocation("Delhi");
        data.setPhone("9999912345");
        data.setCourseArr(courses);

        id=given()
                .contentType("application/json")
                .body(data)
                .when()
                .post("http://localhost:3000/students")
                .jsonPath().getString("id");

    }

    @Test
    void ExternalJsonFile() throws FileNotFoundException {
        File file = new File("practice.json");
        FileReader fr = new FileReader(file);
        JSONTokener jt = new JSONTokener(fr);
        JSONObject map = new JSONObject(jt);
        map.put("name", "Shiva");
        map.put("location", "Delhi");
        map.put("phone", "9818190000");
        String[] courseArr = {"Javascript", "Csharp"};
        map.put("courses",courseArr);

        id=given()
                .contentType("application/json")
                .body(map.toString())
                .when()
                .post("http://localhost:3000/students/")
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