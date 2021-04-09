import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import response.MovieResponse;
import response.SearchResponse;

import static io.restassured.RestAssured.given;

public class HelperMethods {
    private String URL = "http://www.omdbapi.com";
    private String apiKey = "52cd129d";
    private String title = "Harry Potter";
    private String fullTitle = "Harry Potter and the Sorcerer's Stone";

    public MovieResponse BySearchMovieTitle() {
        ValidatableResponse response = given()
                .param("s", title)
                .param("apikey", apiKey)
                .when()
                .get(URL)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);

        SearchResponse searchResult = response.extract().response().as(SearchResponse.class);
        for (MovieResponse movieResult : searchResult.getSearch()) {
            if (movieResult.getTitle().equals(fullTitle)) {
                return movieResult;
            }
        }
        return null;
    }

    public MovieResponse SearchById(MovieResponse movieResult) {
        ValidatableResponse response = given()
                .param("i", movieResult.getImdbID())
                .param("apikey", apiKey)
                .when()
                .get(URL)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);

        return response.extract().response().as(MovieResponse.class);

    }
}
