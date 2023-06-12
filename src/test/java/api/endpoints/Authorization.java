package api.endpoints;

import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Authorization {
    public static final String libraryScope = "user-library-modify";
    public static final String privatePlaylistScope = "playlist-modify-private";
    public static final String publicPlaylistScope = "playlist-modify-public";
    private static String accessToken;
    private static String accessTokenWithScope;

    public static void extractToken() {
        //code to extract the access token
        String response = given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("grant_type", "client_credentials")
                .formParam("client_id", Routes.clientId)
                .formParam("client_secret", Routes.clientSecret)
                .when()
                .post(Routes.accessTokenUrl)
                .asString();

        JsonPath jsonPath = JsonPath.from(response);
        accessToken = jsonPath.getString("access_token");
        System.out.println(accessToken);
    }
    @Test
    public void extractTest() {
        extractToken();
    }
    public static String getToken() {
        return accessToken;
    }

    public static void extractTokenWithScope(String scope) {
        //code to extract the access token
        String response = given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("grant_type", "client_credentials")
                .formParam("client_id", Routes.clientId)
                .formParam("client_secret", Routes.clientSecret)
                .formParam("scope", scope)
                .when()
                .post(Routes.accessTokenUrl)
                .asString();

        JsonPath jsonPath = JsonPath.from(response);
        accessTokenWithScope = jsonPath.getString("access_token");
       String soutBody = jsonPath.getString("scope");
        System.out.println(soutBody);
        System.out.println(accessTokenWithScope);
    }

    public static String getTokenWithScope() {
        return accessTokenWithScope;
    }

    //@Test
    void testToken() {
        extractTokenWithScope(Authorization.libraryScope);
    }
}