package skillacademy.apidefinitions;

import io.restassured.response.Response;
import skillacademy.APIHelper;
import java.util.HashMap;
import static skillacademy.Constants.*;

public class DiscoveryAPI extends APIHelper {
    public Response searchSkill(String page, String pageSize, String searchQuery, String minPrice, String maxPrice,
                                String sortBy) {
        String url = BASE_URL + SEARCH_URL;

        HashMap<String, String> queryParams = new HashMap<String, String>();
        queryParams.put("page", page);
        queryParams.put("pageSize", pageSize);
        queryParams.put("searchQuery", searchQuery);
        queryParams.put("minPrice", minPrice);
        queryParams.put("maxPrice", maxPrice);
        queryParams.put("sortBy", sortBy);

        Response response = constructRequestSpecification(CONTENT_TYPE, queryParams).
                get(BASE_URL + SEARCH_URL);

        return response;
    }
}