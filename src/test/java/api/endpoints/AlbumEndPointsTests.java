package api.endpoints;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import api.endpoints.Authorization;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class AlbumEndPointsTests {
    @Test
    public void getAlbum() {
        Authorization.extractToken();
        String albumId = "1HimPrGurKic1hNOSidwF2";
        Response response = given()
                .header("Authorization", "Bearer " + Authorization.getToken())
                .when()
                .get(Routes.getSingleAlbum + "/" + albumId);
        response.then().log().all();
    }
    @Test
    public void getAlbumWithMarket() {
        String market = "IL";
        Authorization.extractToken();
        String albumId = "1HimPrGurKic1hNOSidwF2";
        Response response = given()
                .header("Authorization", "Bearer " + Authorization.getToken())
                .queryParam("market", market)
                .when()
                .get(Routes.getSingleAlbum + "/" + albumId);
        response.then().log().all();
    }

    public static Response getSeveralAlbums() {
        String[] fewAlbums = {"1HimPrGurKic1hNOSidwF2", "3qzrNVuUyOJxfzMYRCh5qN", "0UWT0SwOzXkR9IVbz0GNuo"};
        Authorization.extractToken();
        //String albumId = "1HimPrGurKic1hNOSidwF2";
        StringBuilder queryParamBuilder = new StringBuilder();
        String queryParams = null;
        for (String albumId : fewAlbums) {
            if (queryParamBuilder.length() > 0) {
                queryParamBuilder.append(",");
            }
            queryParamBuilder.append(albumId);
            queryParams = queryParamBuilder.toString();
        }
        Response response = given()
                .header("Authorization", "Bearer " + Authorization.getToken())
                .queryParam("ids", queryParams)
                .queryParam("market", "IL")
                .when()
                .get(Routes.getSingleAlbum + "/");
        return response;
    }
}