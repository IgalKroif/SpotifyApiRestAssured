package api.tests.album.postivetests;

import api.endpoints.AlbumEndPointsTests;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PositiveAlbumTests {

    @Test
    public void getAlbumWithMarket() {
        Response response = AlbumEndPointsTests.getAlbumWithMarket();
        response.then().assertThat().statusCode(200)
                .log().status().log().headers().log().cookies();
        JsonPath js = response.jsonPath();
        js.getString("tracks.href");
        Assert.assertTrue(js.getString("tracks.href").contains("market=IL"));
    }



    @Test
    public void testing() {
        //AlbumEndPointsTests myAlbumTests = new AlbumEndPointsTests(); is static so no need to create a new instance
        Response response = AlbumEndPointsTests.getSeveralAlbums();
        Assert.assertEquals(response.statusCode(), 200);
        response.then().log().body()
                .log().status()
                .log().headers();
    }

}
