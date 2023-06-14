package api.endpoints.get.artistmethods;

import api.endpoints.Authorization;
import api.endpoints.Routes;
import api.payload.ArtistData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetArtistEndPointsTests {
    //returns info about an artist of given ID
    public static Response getAnArtist() {
        Authorization.extractToken();
        String artistId = "0DCw6lHkzh9t7f8Hb4Z0Sx";
        Response response =
                given()
                        .header("Authorization", "Bearer " + Authorization.getToken())
                        .contentType("application/json")
                        .pathParam("id", artistId)  // Use pathParam() instead of queryParam()
                        .when().get(Routes.baseGetSingleArtistUrl);

        return response;
    }
    //@Test
    public static Response getSeveralArtists(String queryParams) {
        Authorization.extractToken();
        Response response =

                given()
                        .header("Authorization", "Bearer " + Authorization.getToken())
                        .contentType(ContentType.JSON)
                        .queryParam("ids", queryParams)
                        .when().get(Routes.baseMultipleArtistUrl);
        if(queryParams == "") {
            response =
                    given()
                            .header("Authorization", "Bearer " + Authorization.getToken())
                            .contentType(ContentType.JSON)
                            .log().params().queryParam("ids", queryParams)
                            .when().get(Routes.baseMultipleArtistUrl);
        }
        return response;
    }
public static Response getSeveralArtists() {
    Authorization.extractToken();
    return given()
            .header("Authorization", "Bearer " + Authorization.getToken())
            .contentType(ContentType.JSON)
            .queryParam("ids", (Object) null)
            .when().get(Routes.baseMultipleArtistUrl);
   }

}



