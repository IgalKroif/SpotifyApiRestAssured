package api.endpoints.get.albumsmethods;

import api.endpoints.Authorization;
import api.endpoints.Routes;
import api.tests.album.postivetests.PositiveAlbumTests;
import api.utilities.ReusableMethods;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetAlbumEndPointsTests {
    //gets an album
    public static Response getAlbum() {
        Authorization.extractToken();
        String albumId = "1HimPrGurKic1hNOSidwF2";
        Response response = given()
                .header("Authorization", "Bearer " + Authorization.getToken())
                .when()
                .get(Routes.getSingleAlbum + "/" + albumId);
        return response;
    }
    //requests an album with specified market.

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

    //requests several albums from server
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

    //Gets all tracks off of a specific album
    public static Response getAlbumTracks() {
        String albumId = "1HimPrGurKic1hNOSidwF2";
        Authorization.extractToken();
        Response response =
                given().header("Authorization", "Bearer " + Authorization.getToken())
                        .queryParam("market", ReusableMethods.getRandomMarket(PositiveAlbumTests.expectedMarkets))
                        .when()
                        .get(Routes.getSingleAlbum + "/" + albumId + "/tracks");
        return response;
    }

    //GET LATEST RELEASES
    public static Response getLatestReleases() {
        Authorization.extractToken();
        Response response =
                given()
                        .header("Authorization", "Bearer " + Authorization.getToken())
                        .queryParam("market", ReusableMethods.getRandomMarket(PositiveAlbumTests.expectedMarkets))
                        .when()
                        .get(Routes.getLatestReleases);

        return response;
    }

    public static Response getNewReleasesWithLimitAndSkip(Integer limit, Integer skip) throws NullPointerException {
        Authorization.extractToken();
        if (limit == null || skip == null) {
            Response response =

                    given()
                            .header("Authorization", "Bearer " + Authorization.getToken())
                            .queryParam("market", ReusableMethods.getRandomMarket(PositiveAlbumTests.expectedMarkets))
                            .when()
                            .get(Routes.getLatestReleases);

            return response;

        } else {
            Response response =

                    given()
                            .header("Authorization", "Bearer " + Authorization.getToken())
                            .queryParam("market", ReusableMethods.getRandomMarket(PositiveAlbumTests.expectedMarkets))
                            .queryParam("limit", limit)
                            .queryParam("skip", skip)
                            .when()
                            .get(Routes.getLatestReleases);
            return response;
        }
    }

    public static Response getNewReleasesWithLimitAndSkip() {
        return getNewReleasesWithLimitAndSkip(1, 0);
    }
}