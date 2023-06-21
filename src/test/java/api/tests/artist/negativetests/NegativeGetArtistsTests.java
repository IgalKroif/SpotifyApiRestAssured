package api.tests.artist.negativetests;

import api.endpoints.get.albumsmethods.GetAlbumEndPointsTests;
import api.endpoints.get.artistmethods.GetArtistEndPointsTests;
import api.payload.ArtistData;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasKey;

public class NegativeGetArtistsTests {
    @Test
    public static void getSeveralArtistsWithoutIdTest() {
        Response response = GetArtistEndPointsTests.getSeveralArtists();
        response.then().log().all();
        switch (response.statusCode()) {

            case 400:
                response.then().assertThat().body("error.status", equalTo(400))
                        .statusCode(400)
                        .body("error.message", anyOf(
                                equalTo("Invalid id"),
                                equalTo("invalid id"),
                                equalTo("INVALID ID"),
                                equalTo("Invalid Id"),
                                containsString("Invalid Id")))
                        .body("error", hasKey("status"))
                        .body("error", hasKey("message"));
                break;
            case 500:
                response.then().assertThat().body("error.status", equalTo(500))
                        .statusCode(500)
                        .body("error.message", anyOf(
                                containsString("Server error."),
                                equalTo("Internal Server error"),
                                containsString("error"), containsString("ERROR")))
                        .body("error", hasKey("status"))
                        .body("error", hasKey("message"));
                break;

            default:
                if (response.statusCode() > 500 || response.statusCode() < 400) {
                    response.then().log().status().and().log().body();
                }
        }

    }
    @Test
    public static void getSeveralArtistsWithOverValidIdsAmountTest() {
        Response response = GetArtistEndPointsTests.getSeveralArtists(ArtistData.fiftyOneIds);
        response.then().log().all();
        switch (response.statusCode()) {

            case 400:
                response.then().assertThat().body("error.status", equalTo(400))
                        .statusCode(400)
                        .body("error.message", anyOf(
                                equalTo("too many ids requested"),
                                equalTo("Too many ids requested"),
                                equalTo("Too Many Ids Requested"),
                                equalTo("TOO MANY IDS REQUESTED"),
                                containsString("too many requested ids")))
                        .body("error", hasKey("status"))
                        .body("error", hasKey("message"));
                break;
            case 500:
                response.then().assertThat().body("error.status", equalTo(500))
                        .statusCode(500)
                        .body("error.message", anyOf(
                                containsString("Server error."),
                                equalTo("Internal Server error"),
                                containsString("error"), containsString("ERROR")))
                        .body("error", hasKey("status"))
                        .body("error", hasKey("message"));
                break;

            default:
                if (response.statusCode() > 500 || response.statusCode() < 400) {
                    response.then().log().status().and().log().body();
                }
        }


    }
}