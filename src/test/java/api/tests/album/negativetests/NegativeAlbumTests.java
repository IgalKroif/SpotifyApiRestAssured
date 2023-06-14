package api.tests.album.negativetests;

import api.endpoints.put.albumsmethods.PutAlbumEndPointsTests;
import api.tests.album.postivetests.PositiveAlbumTests;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static api.endpoints.get.albumsmethods.GetAlbumEndPointsTests.getNewReleasesWithLimitAndSkip;
import static org.hamcrest.Matchers.*;

public class NegativeAlbumTests {
    @Test
    public void AssertErrorCodeTest() {
        Response response = PutAlbumEndPointsTests.testAddAlbumsToAccount();
        response.then().log().body();
        Assert.assertNotEquals(response.statusCode(), 200);
        response.then().assertThat()
                .body("error", hasKey("status"))
                .body("error", hasKey("message"));

        switch (response.statusCode()) {
            case 400:
                response.then().assertThat().body("error.status", equalTo(400))
                        .body("error.message", equalTo("Bad request."))
                        .log().status().and().log().body();
                break;
            case 401:
                response.then().assertThat().body("error.status", equalTo(401))
                        .body("error.message", equalTo("Invalid access token"))
                        .log().status().and().log().body();
                break;
            case 500:
                response.then().assertThat().body("error.status", equalTo(500))
                        .body("error.message", equalTo("Server error."))
                        .log().status().and().log().body();
                break;
            default:
                if (response.statusCode() > 500 || response.statusCode() < 400) {
                    response.then().log().status().and().log().body();
                }

        }
    }
    /*
    /GETS latest releases with skip parameter and limit parameter
    @param skip parameter: skips number of tracks shown to the user
    @param limit parameter: limit number of tracks shown from overall response if 0 will return bad request
    WILL RETURN LIMIT 1 if not parameter is specified for limit otherwise skip = 0;
    ** WILL RUN AUTOMATICALLY THROUGH POSITIVE TEST IF AN ERROR OCCURS. **
     */
    //@Test
    public static void getNewReleasesWithLimitAndSkipNegativeTest() {
        Response response = getNewReleasesWithLimitAndSkip(500,1);
        response.then().log().body().and().log().status();
        switch (response.statusCode()) {

            case 400:
                response.then().assertThat().body("error.status", equalTo(400))
                        .statusCode(400)
                        .body("error.message", anyOf(
                                equalTo("Invalid limit"),
                                equalTo("Bad limit, limit must be larger than 0")))
                        .body("error", hasKey("status"))
                        .body("error", hasKey("message"));
                break;
            case 500:
                response.then().assertThat().body("error.status", equalTo(500))
                        .statusCode(500)
                        .body("error.message", anyOf(
                                equalTo("Server error."),
                                equalTo("Internal Server error"),
                                containsString("error"),containsString("ERROR")))
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
