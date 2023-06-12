package api.tests.album.postivetests;

import api.endpoints.get.albumsmethods.GetAlbumEndPointsTests;
import api.utilities.ReusableMethods;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class PositiveAlbumTests {
    //list of market from response body of request
   public static List<String> expectedMarkets = Arrays.asList("AD", "AE", "AG", "AL", "AM", "AO", "AR", "AT", "AU", "AZ", "BA", "BB",
            "BD", "BE", "BF", "BG", "BH", "BI", "BJ", "BN", "BO", "BR", "BS", "BT", "BW", "BY", "BZ", "CA", "CD", "CG",
            "CH", "CI", "CL", "CM", "CO", "CR", "CV", "CW", "CY", "CZ", "DE", "DJ", "DK", "DM", "DO", "DZ", "EC", "EE",
            "EG", "ES", "ET", "FI", "FJ", "FM", "FR", "GA", "GB", "GD", "GE", "GH", "GM", "GN", "GQ", "GR", "GT", "GW",
            "GY", "HK", "HN", "HR", "HT", "HU", "ID", "IE", "IL", "IN", "IQ", "IS", "IT", "JM", "JO", "KE", "KG", "KH",
            "KI", "KM", "KN", "KR", "KW", "KZ", "LA", "LB", "LC", "LI", "LK", "LR", "LS", "LT", "LU", "LV", "LY", "MA",
            "MC", "MD", "ME", "MG", "MH", "MK", "ML", "MN", "MO", "MR", "MT", "MU", "MV", "MW", "MX", "MY", "MZ", "NA",
            "NE", "NG", "NI", "NL", "NO", "NP", "NR", "NZ", "OM", "PA", "PE", "PG", "PH", "PK", "PL", "PS", "PT", "PW",
            "PY", "QA", "RO", "RS", "RW", "SA", "SB", "SC", "SE", "SG", "SI", "SK", "SL", "SM", "SN", "SR", "ST", "SV",
            "SZ", "TD", "TG", "TH", "TJ", "TL", "TN", "TO", "TR", "TT", "TV", "TW", "TZ", "UA", "UG", "US", "UY", "UZ",
            "VC", "VE", "VN", "VU", "WS", "XK", "ZA", "ZM", "ZW");

//get one album
    @Test
    public void getAnAlbum() {
        Response response = GetAlbumEndPointsTests.getAlbum();
        response.then()
                .assertThat().statusCode(200);
        //Asserting that response list contains at list one of the markets available
        JsonPath js = response.jsonPath();
        List<String> actualMarkets = js.getList("available_markets");
        boolean containsAnyMarket = !Collections.disjoint(actualMarkets, expectedMarkets);
        Assert.assertTrue(containsAnyMarket);
    }
    //get an album with market query param
    @Test
    public void getAlbumWithMarket() {
        String randomMarket = ReusableMethods.getRandomMarket(expectedMarkets);
        Response response = GetAlbumEndPointsTests.getAlbumWithMarket(randomMarket);
        response.then().assertThat().statusCode(200)
                .log().status().log().headers().log().cookies()
                .log().body();
        JsonPath js = response.jsonPath();
        String tracksHref = js.getString("tracks.href");
        Assert.assertTrue(tracksHref.contains(randomMarket));
    }
    //get multiple albums
    @Test
    public void testing() {
        Response response = GetAlbumEndPointsTests.getSeveralAlbums();
        Assert.assertEquals(response.statusCode(), 200);
        response.then().log().body()
                .log().status()
                .log().headers();
    }

}
