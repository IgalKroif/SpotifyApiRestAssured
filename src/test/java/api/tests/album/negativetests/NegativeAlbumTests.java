package api.tests.album.negativetests;

import api.endpoints.put.albumsmethods.PutAlbumEndPointsTests;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

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
            case 1:
                response.then().assertThat().body("error.status", equalTo(400))
                        .body("error.message", equalTo("Bad request."))
                        .log().status().and().log().body();
                break;
            case 2:
                response.then().assertThat().body("error.status", equalTo(401))
                        .body("error.message", equalTo("Invalid access token"))
                        .log().status().and().log().body();
                break;
            case 3:
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
}
