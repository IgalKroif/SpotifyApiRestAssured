package api.tests;

import api.endpoints.AlbumEndPointsTests;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PositiveAlbumTests {

    @Test
    public void testing() {
        AlbumEndPointsTests myAlbumTests = new AlbumEndPointsTests();
        Response response = AlbumEndPointsTests.getSeveralAlbums();
        Assert.assertEquals(response.statusCode(), 200);
        response.then().log().body();
    }
}
