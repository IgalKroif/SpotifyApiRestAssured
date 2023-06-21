package api.tests.artist.positivetests;

import api.endpoints.get.artistmethods.GetArtistEndPointsTests;
import api.payload.ArtistData;
import api.tests.album.negativetests.NegativeGetAlbumTests;
import api.tests.artist.negativetests.NegativeGetArtistsTests;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static api.endpoints.get.artistmethods.GetArtistEndPointsTests.getSeveralArtists;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PositiveGetArtistsTests {

    @Test
    public void getOneArtistTest() {
        Response response = GetArtistEndPointsTests.getAnArtist();
        response.then().log().status();
    }
    @Test
    public void GetSeveralArtistsWithValidDataFortyNineTest() {
        Response response = getSeveralArtists(ArtistData.fortyNineIds);
        switch (response.statusCode()) {
            case 200:
                // Get the JSON response as a string
                String jsonResponse = response.getBody().asString();
                // Create a JsonPath object
                JsonPath jsonPath = new JsonPath(jsonResponse);
                System.out.println("The total number asserted: " + jsonPath.getString("artists[0].followers.total"));
                String total = jsonPath.getString("artists[0].followers.total");
                List<String> genres = jsonPath.getList("artists[0].genres");
                // Perform assertion using contains for all expected genres
                assertThat(genres, Matchers.containsInAnyOrder(
                        "alternative metal",
                        "death metal",
                        "melodic death metal",
                        "metal",
                        "nu metal",
                        "power metal",
                        "speed metal",
                        "swedish death metal",
                        "swedish melodeath",
                        "swedish metal",
                        "symphonic metal"
                ));
                System.out.println("Asserted Genres: " + genres);
                assertThat(total, greaterThanOrEqualTo("914377"));

                System.out.println("Data of forty nine values: ");
                response.then().log().status();
                System.out.println("\n");
                Assert.assertEquals(response.statusCode(), 200);
                break;
            case 202:
                response.then().log().status();
                System.out.println("Request is processing...");
                break;
            case 203:
                response.then().log().status();
                System.out.println("the server successfully processed the request " +
                                   "but provided information may come from a third-party source.");
                break;
            case 204:
                System.out.println("NO CONTENT: EMPTY BODY!");
                response.then().log().status().and().log().body();
                break;
            case 400:
                try {
                    NegativeGetArtistsTests.getSeveralArtistsWithoutIdTest();
                } catch (Exception e) {
                    try {
                        NegativeGetArtistsTests.getSeveralArtistsWithOverValidIdsAmountTest();
                    } catch (Exception ex) {
                        // Handle the exception from the second try block if needed
                        return;
                    }
                }
            default:
            case 401:
                System.out.println("No Authorization found");
                response.then().log().status().and().log();
                break;
        }
    }
        @Test
        public void GetSeveralArtistsWithValidDataFiftyTest() {
            Response response = getSeveralArtists(ArtistData.fiftyIds);
            response.then().log().body();
            switch (response.statusCode()) {
                case 200:
                    // Get the JSON response as a string
                    String jsonResponse = response.getBody().asString();
                    // Create a JsonPath object
                    JsonPath jsonPath = new JsonPath(jsonResponse);
                    System.out.println("The total number asserted: " + jsonPath.getString("artists[0].followers.total"));
                    String total = jsonPath.getString("artists[0].followers.total");
                    List<String> genres = jsonPath.getList("artists[1].genres");
                    // Perform assertion using contains for all expected genres
                    assertThat(genres, Matchers.containsInAnyOrder(
                                    "death metal",
                                    "doom metal",
                                    "epic doom",
                                    "metal",
                                    "opera metal",
                                    "speed metal",
                                    "stoner metal",
                                    "swedish doom metal",
                                    "swedish metal"
                            ));
                    System.out.println("Asserted Genres: " + genres);
                    assertThat(total, greaterThanOrEqualTo("914377"));

                    System.out.println("Data of fifty values: ");
                    response.then().log().status();
                    System.out.println("\n");
                    Assert.assertEquals(response.statusCode(), 200);
                    break;
                case 202:
                    response.then().log().status();
                    System.out.println("Request is processing...");
                    break;
                case 203:
                    response.then().log().status();
                    System.out.println("the server successfully processed the request " +
                            "but provided information may come from a third-party source.");
                    break;
                case 204:
                    System.out.println("NO CONTENT: EMPTY RESPONSE BODY!");
                    response.then().log().status().and().log().body();
                    break;
                case 400:
                    try {
                        NegativeGetArtistsTests.getSeveralArtistsWithoutIdTest();
                    } catch (Exception e) {
                        try {
                            NegativeGetArtistsTests.getSeveralArtistsWithOverValidIdsAmountTest();
                        } catch (Exception ex) {
                            // Handle the exception from the second try block if needed
                            return;
                        }
                    }
                    break;

                    }

            }
        }