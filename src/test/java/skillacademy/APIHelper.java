package skillacademy;

import io.restassured.specification.RequestSpecification;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static skillacademy.Constants.*;

public class APIHelper {
    public RequestSpecification constructRequestSpecification(String contentType, HashMap<String, String> queryParam) {
        RequestSpecification requestSpecification = given().contentType(contentType);

        for(Map.Entry<String, String> entries : queryParam.entrySet()) {
            if(!entries.getValue().equals(EMPTY_STRING))
                requestSpecification.param(entries.getKey(), entries.getValue());
        }

        return requestSpecification;
    }
}