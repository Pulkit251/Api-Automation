package Day5;

import Day1.POJO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;

import java.util.Arrays;

//Serialization - POJOtoJSON
//Deserialization - JSONtoPOJO

public class SerializationDeserialization {

    @Test
    void convertPOJOtoJSON() throws JsonProcessingException {
        Student stud = new Student();
        stud.setName("Pulkit");
        stud.setLocation("Delhi");
        stud.setPhone("9980012345");
        String[] courseArr = {"Javascript","Java"};
        stud.setCourse(courseArr);

        ObjectMapper obj = new ObjectMapper();
        String jsonData = obj.writerWithDefaultPrettyPrinter().writeValueAsString(stud);

        System.out.println(jsonData);
    }

    @Test
    void convertJSONtoPOJO() throws JsonProcessingException {
        String jsonData = "{\n" +
                "  \"name\" : \"Pulkit\",\n" +
                "  \"location\" : \"Delhi\",\n" +
                "  \"phone\" : \"9980012345\",\n" +
                "  \"course\" : [ \"Javascript\", \"Java\" ]\n" +
                "}";

        ObjectMapper mapper = new ObjectMapper();

        Student student = mapper.readValue(jsonData, Student.class);
        System.out.println(student.getName());
        System.out.println(student.getLocation());
        System.out.println(student.getPhone());
        System.out.println(student.getCourse()[0]);
        System.out.println(student.getCourse()[1]);

    }

}
