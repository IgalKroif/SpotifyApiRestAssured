package api.tests.album.negativetests;

import api.endpoints.put.albumsmethods.PutAlbumEndPointsTests;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class NegativeAlbumTests {
    @Test
    public void firstNegativeTest() {
        Response response = PutAlbumEndPointsTests.testAddAlbumsToAccount();
        response.then().log().body();
        Assert.assertNotEquals(response.statusCode(), 200);
        response.then().assertThat()
                .body("error.status", anyOf(equalTo(400), equalTo(401), equalTo(403),
                        equalTo(409), equalTo(429), equalTo(500), equalTo(501),
                        equalTo(502), equalTo(503)))
                .body("error.message", equalTo("Bad request."))
                .body("error", hasKey("status"))
                .body("error", hasKey("message"));
    }
}
