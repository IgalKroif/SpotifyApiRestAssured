package api.endpoints;

// API Documentation Location: https://developer.spotify.com/documentation/web-api/reference/#endpoint-
//API Artist URL: https://developer.spotify.com/documentation/web-api/reference/get-an-artist
//API Playlist URL: https://developer.spotify.com/documentation/web-api/reference/get-playlist
//API Album URL: https://developer.spotify.com/documentation/web-api/reference/get-an-album

import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.request;

public class Routes {
    //URL endpoints for SpotifyAPI requests
    protected static final String clientId = "21df2fd397254479b1ff5d2429c5ba95";
    protected static final String clientSecret = "99e298b2bc424a9d8d5cf0043007cd6c";

    protected  static String accessTokenUrl = "https://accounts.spotify.com/api/token";

    //Artists Module
   public static String baseArtistUrl = "https://api.spotify.com/v1/artists/";


   // public static String getArtists = baseArtistUrl + "{ArtistId}";


    //Albums Module
    public static String baseAlbumUrl = "https://api.spotify.com/v1/albums";
   public static String getSingleAlbum = baseAlbumUrl;

   public static String getLatestReleases = baseAlbumUrl;


    //Playlists Module



}
