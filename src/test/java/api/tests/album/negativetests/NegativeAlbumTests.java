package api.tests.album.negativetests;

import api.endpoints.get.albumsmethods.GetAlbumEndPointsTests;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

public class NegativeAlbumTests {
    @Test
    public void firstNegativeTest() {
        Response response = GetAlbumEndPointsTests.testAddAlbumsToAccount();
        response.then().log().body();
        Assert.assertNotEquals(response.statusCode(),200);
        response.then().assertThat()
                .body("error.status", equalTo(400))
                .body("error.message", equalTo("Bad request."))
                .body("error", hasKey("status"))
                .body("error", hasKey("message"));

    }
}
