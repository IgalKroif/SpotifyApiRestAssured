package api.endpoints;

// API Documentation Location: https://developer.spotify.com/documentation/web-api/reference/#endpoint-
//API Artist URL: https://developer.spotify.com/documentation/web-api/reference/get-an-artist
//API Playlist URL: https://developer.spotify.com/documentation/web-api/reference/get-playlist
//API Album URL: https://developer.spotify.com/documentation/web-api/reference/get-an-album

public class Routes {
    //URL endpoints for SpotifyAPI requests
    static int version = 1;
    protected static final String apiVersion = "/v"+version+"/";
    protected static final String baseURL = "https://api.spotify.com" + apiVersion;
    protected static final String clientId = "21df2fd397254479b1ff5d2429c5ba95";
    protected static final String clientSecret = "99e298b2bc424a9d8d5cf0043007cd6c";

    protected static String accessTokenUrl = "https://accounts.spotify.com/api/token";

    //Artists Module
    //URL TO GET an artist
   public static String baseGetSingleArtistUrl = baseURL + "artists/" + "{id}";
    //URL TO GET several artists!
    public static String baseMultipleArtistUrl = baseURL + "artists";

    //URL TO GET an artists album : /artists/{id}/albums Where id is the artists ID!
    public static String baseArtistsAlbumsURL = baseURL + "albums";


    //Albums Module
    public static String baseAlbumUrl = "https://api.spotify.com/v1/albums";
   public static String getSingleAlbum = baseAlbumUrl;

   public static String getLatestReleases = "https://api.spotify.com/v1/browse/new-releases";

   public static String putAlbumsToAccount = "https://api.spotify.com/v1/me/albums";


    //Playlists Module



}
