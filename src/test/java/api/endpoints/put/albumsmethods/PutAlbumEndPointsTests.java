package api.endpoints.put.albumsmethods;

import api.endpoints.Authorization;
import api.endpoints.Routes;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PutAlbumEndPointsTests {
    public static Response testAddAlbumsToAccount() {
        // Make the API call and return the response
        Authorization.extractTokenWithScope(Authorization.libraryScope);
        Response response =

                given()
                        .header("Authorization", "Bearer " + Authorization.getTokenWithScope())
                        .contentType(ContentType.JSON)
                        .queryParam("ids", "382ObEPsp2rxGrnsizN5TX%2C1A2GTWGtFfWp7KSQTwWOyo%2C2noRn2Aes5aoNVsU6iWThc")
                        .body("{\n" +
                                "    \"ids\": [\n" +
                                "        \"382ObEPsp2rxGrnsizN5TX%2C1A2GTWGtFfWp7KSQTwWOyo%2C2noRn2Aes5aoNVsU6iWThc\"\n" +
                                "    ]\n" +
                                "}")
                        .when()
                        .put(Routes.putAlbumsToAccount)
                        .then()
                        .extract()
                        .response();
        return response;
    }
}
