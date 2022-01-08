package skillacademy;

import io.restassured.response.Response;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseHelper {
    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {}

    public boolean validateStatusCode(Response response, int statusCode) {
        boolean result = response.statusCode() == statusCode;

        return result;
    }

    public boolean validateDataSize(Response response, String pathToValidate, int expectedValue) {
        int size = response.jsonPath().getList(pathToValidate).size();

        boolean result = size == expectedValue;

        return result;
    }

    public boolean validateResponseValue(Response response, String pathToValidate, Object expectedValue) {
        Object actualValue = response.path(pathToValidate);

        boolean result = actualValue.toString().equals(expectedValue.toString());

        return result;
    }

    public boolean validateTotalPageNumber(Response response, String pathPageSize, String pathTotalData,
                                           String pathTotalPage) {
        int pageSize = response.path(pathPageSize);
        int totalData = response.path(pathTotalData);
        int totalPage = response.path(pathTotalPage);

        int expectedTotalPage = totalData / pageSize;
        if(totalData % pageSize > 0)
            expectedTotalPage++;

        boolean result = expectedTotalPage == totalPage;

        return result;
    }

    public boolean validateResponseValueArray(Response response, String pathData, String pathToValidate,
                                      Object expectedValue) {
        int dataSize = response.jsonPath().getList(pathData).size();
        boolean result = true;

        for(int i = 0; i < dataSize; i++) {
            Object actualValue = response.path(pathData + "[" + i + "]." + pathToValidate);
            if(!actualValue.toString().contains(expectedValue.toString())) {
                result = false;
                break;
            }
        }

        return result;
    }

    public boolean validatePriceRange(Response response, String pathData, String pathPrice,
                                      int expectedMinPrice, int expectedMaxPrice) {
        int dataSize = response.jsonPath().getList(pathData).size();
        boolean result = true;

        for(int i = 0; i < dataSize; i++) {
            int actualPrice = response.path(pathData + "[" + i + "]." + pathPrice);
            if(actualPrice < expectedMinPrice || (expectedMaxPrice != 0 && actualPrice > expectedMaxPrice)) {
                result = false;
                break;
            }
        }

        return result;
    }

    public boolean validateSortByPrice(Response response, String pathData, String pathPrice) {
        int dataSize = response.jsonPath().getList(pathData).size();
        boolean result = true;
        int tmp = 0;

        for(int i = 0; i < dataSize; i++) {
            int price = response.path(pathData + "[" + i + "]." + pathPrice);

            if(i != 0 && price > tmp) {
                result = false;
                break;
            }

            tmp = price;
        }

        return result;
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {}
}