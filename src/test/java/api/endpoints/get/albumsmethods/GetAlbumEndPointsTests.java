package api.endpoints.get.albumsmethods;

import api.endpoints.Routes;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lombok.Data;
import org.testng.annotations.Test;
import api.endpoints.Authorization;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class GetAlbumEndPointsTests {
    public static Response getAlbum() {
        Authorization.extractToken();
        String albumId = "1HimPrGurKic1hNOSidwF2";
        Response response = given()
                .header("Authorization", "Bearer " + Authorization.getToken())
                .when()
                .get(Routes.getSingleAlbum + "/" + albumId);
        return response;
    }

    public static Response getAlbumWithMarket(String marketID) {
        Authorization.extractToken();
        String albumId = "1HimPrGurKic1hNOSidwF2";
        Response response = given()
                .header("Authorization", "Bearer " + Authorization.getToken())
                .queryParam("market", marketID)
                .when()
                .get(Routes.getSingleAlbum + "/" + albumId);
        return response;
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

    //NOT YET IMPLEMENTED : GET TRACKS, GET SAVED ALBUMS, SAVE ALBUM
}