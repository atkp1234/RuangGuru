package skillacademy.discovery;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import skillacademy.BaseHelper;
import skillacademy.apidefinitions.DiscoveryAPI;
import static skillacademy.Constants.*;
import org.testng.Assert;
import org.apache.http.HttpStatus;

public class SearchTest extends BaseHelper {
    private DiscoveryAPI discoveryAPI = new DiscoveryAPI();

    @Test(groups = { "sanity", "regression" }, priority = 1)
    public void verifySearchSkill() {
        Response response = discoveryAPI.searchSkill(EMPTY_STRING, EMPTY_STRING, EMPTY_STRING, EMPTY_STRING,
                EMPTY_STRING, EMPTY_STRING);
        Assert.assertTrue(validateStatusCode(response, HttpStatus.SC_OK));
        Assert.assertTrue(validateDataSize(response, SEARCH_DATA_PATH, DEFAULT_PAGE_SIZE));
        Assert.assertTrue(validateResponseValue(response, SEARCH_PAGE_PATH, DEFAULT_PAGE));
        Assert.assertTrue(validateResponseValue(response, SEARCH_PAGE_SIZE_PATH, DEFAULT_PAGE_SIZE));
        Assert.assertTrue(validateTotalPageNumber(response, SEARCH_PAGE_SIZE_PATH, SEARCH_TOTAL_COURSE_PATH,
                SEARCH_TOTAL_PAGE_PATH));
        Assert.assertTrue(validateResponseValue(response, SEARCH_STATUS_PATH, MESSAGE_SUCCESS));
        Assert.assertTrue(validateResponseValue(response, SEARCH_MESSAGE_PATH, MESSAGE_SUCCESS));
    }

    @Test(groups = { "regression" }, priority = 2)
    public void verifySearchSkillWithSpecificPage() {
        Response response = discoveryAPI.searchSkill(PAGE, Integer.toString(DEFAULT_PAGE_SIZE), EMPTY_STRING,
                EMPTY_STRING, EMPTY_STRING, EMPTY_STRING);
        Assert.assertTrue(validateStatusCode(response, HttpStatus.SC_OK));
        Assert.assertTrue(validateDataSize(response, SEARCH_DATA_PATH, DEFAULT_PAGE_SIZE));
        Assert.assertTrue(validateResponseValue(response, SEARCH_PAGE_PATH, PAGE));
        Assert.assertTrue(validateResponseValue(response, SEARCH_PAGE_SIZE_PATH, DEFAULT_PAGE_SIZE));
        Assert.assertTrue(validateTotalPageNumber(response, SEARCH_PAGE_SIZE_PATH, SEARCH_TOTAL_COURSE_PATH,
                SEARCH_TOTAL_PAGE_PATH));
        Assert.assertTrue(validateResponseValue(response, SEARCH_STATUS_PATH, MESSAGE_SUCCESS));
        Assert.assertTrue(validateResponseValue(response, SEARCH_MESSAGE_PATH, MESSAGE_SUCCESS));
    }

    @Test(groups = { "regression" }, priority = 3)
    public void verifySearchSkillWithSpecificPageSize() {
        Response response = discoveryAPI.searchSkill(DEFAULT_PAGE, PAGE_SIZE, EMPTY_STRING, EMPTY_STRING,
                EMPTY_STRING, EMPTY_STRING);
        Assert.assertTrue(validateStatusCode(response, HttpStatus.SC_OK));
        Assert.assertTrue(validateDataSize(response, SEARCH_DATA_PATH, Integer.parseInt(PAGE_SIZE)));
        Assert.assertTrue(validateResponseValue(response, SEARCH_PAGE_PATH, DEFAULT_PAGE));
        Assert.assertTrue(validateResponseValue(response, SEARCH_PAGE_SIZE_PATH, PAGE_SIZE));
        Assert.assertTrue(validateTotalPageNumber(response, SEARCH_PAGE_SIZE_PATH, SEARCH_TOTAL_COURSE_PATH,
                SEARCH_TOTAL_PAGE_PATH));
        Assert.assertTrue(validateResponseValue(response, SEARCH_STATUS_PATH, MESSAGE_SUCCESS));
        Assert.assertTrue(validateResponseValue(response, SEARCH_MESSAGE_PATH, MESSAGE_SUCCESS));
    }

    @Test(groups = { "regression" }, priority = 4)
    public void verifySearchSkillWithSpecificKeyword() {
        Response response = discoveryAPI.searchSkill(EMPTY_STRING, EMPTY_STRING, SEARCH_QUERY, EMPTY_STRING,
                EMPTY_STRING, EMPTY_STRING);
        Assert.assertTrue(validateStatusCode(response, HttpStatus.SC_OK));
        Assert.assertTrue(validateResponseValueArray(response, SEARCH_DATA_PATH, SEARCH_COURSE_DESCRIPTION_PATH,
                SEARCH_QUERY));
        Assert.assertTrue(validateResponseValue(response, SEARCH_PAGE_PATH, DEFAULT_PAGE));
        Assert.assertTrue(validateTotalPageNumber(response, SEARCH_PAGE_SIZE_PATH, SEARCH_TOTAL_COURSE_PATH,
                SEARCH_TOTAL_PAGE_PATH));
        Assert.assertTrue(validateResponseValue(response, SEARCH_STATUS_PATH, MESSAGE_SUCCESS));
        Assert.assertTrue(validateResponseValue(response, SEARCH_MESSAGE_PATH, MESSAGE_SUCCESS));
    }

    @Test(groups = { "regression" }, priority = 5)
    public void verifySearchSkillWithSpecificMinPrice() {
        Response response = discoveryAPI.searchSkill(EMPTY_STRING, EMPTY_STRING, EMPTY_STRING, MIN_PRICE,
                EMPTY_STRING, EMPTY_STRING);
        Assert.assertTrue(validateStatusCode(response, HttpStatus.SC_OK));
        Assert.assertTrue(validatePriceRange(response, SEARCH_DATA_PATH, SEARCH_PRICE_PATH,
                Integer.parseInt(MIN_PRICE), NUMBER_ZERO));
        Assert.assertTrue(validateResponseValue(response, SEARCH_PAGE_PATH, DEFAULT_PAGE));
        Assert.assertTrue(validateTotalPageNumber(response, SEARCH_PAGE_SIZE_PATH, SEARCH_TOTAL_COURSE_PATH,
                SEARCH_TOTAL_PAGE_PATH));
        Assert.assertTrue(validateResponseValue(response, SEARCH_STATUS_PATH, MESSAGE_SUCCESS));
        Assert.assertTrue(validateResponseValue(response, SEARCH_MESSAGE_PATH, MESSAGE_SUCCESS));
    }

    @Test(groups = { "regression" }, priority = 6)
    public void verifySearchSkillWithSpecificMaxPrice() {
        Response response = discoveryAPI.searchSkill(EMPTY_STRING, EMPTY_STRING, EMPTY_STRING, EMPTY_STRING,
                MAX_PRICE, EMPTY_STRING);
        Assert.assertTrue(validateStatusCode(response, HttpStatus.SC_OK));
        Assert.assertTrue(validatePriceRange(response, SEARCH_DATA_PATH, SEARCH_PRICE_PATH, NUMBER_ZERO,
                Integer.parseInt(MAX_PRICE)));
        Assert.assertTrue(validateResponseValue(response, SEARCH_PAGE_PATH, DEFAULT_PAGE));
        Assert.assertTrue(validateTotalPageNumber(response, SEARCH_PAGE_SIZE_PATH, SEARCH_TOTAL_COURSE_PATH,
                SEARCH_TOTAL_PAGE_PATH));
        Assert.assertTrue(validateResponseValue(response, SEARCH_STATUS_PATH, MESSAGE_SUCCESS));
        Assert.assertTrue(validateResponseValue(response, SEARCH_MESSAGE_PATH, MESSAGE_SUCCESS));
    }

    @Test(groups = { "regression" }, priority = 7)
    public void verifySearchSkillSortByPrice() {
        Response response = discoveryAPI.searchSkill(EMPTY_STRING, EMPTY_STRING, EMPTY_STRING, EMPTY_STRING,
                EMPTY_STRING, SORT_BY_PRICE);
        Assert.assertTrue(validateStatusCode(response, HttpStatus.SC_OK));
        Assert.assertTrue(validateSortByPrice(response, SEARCH_DATA_PATH, SEARCH_PRICE_PATH));
        Assert.assertTrue(validateResponseValue(response, SEARCH_PAGE_PATH, DEFAULT_PAGE));
        Assert.assertTrue(validateTotalPageNumber(response, SEARCH_PAGE_SIZE_PATH, SEARCH_TOTAL_COURSE_PATH,
                SEARCH_TOTAL_PAGE_PATH));
        Assert.assertTrue(validateResponseValue(response, SEARCH_STATUS_PATH, MESSAGE_SUCCESS));
        Assert.assertTrue(validateResponseValue(response, SEARCH_MESSAGE_PATH, MESSAGE_SUCCESS));
    }
}