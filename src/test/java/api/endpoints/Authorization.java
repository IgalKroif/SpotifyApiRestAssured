package api.endpoints;

import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

public class Authorization {

    private static String accessToken;

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

    public static String getToken() {
        return accessToken;
    }

}
