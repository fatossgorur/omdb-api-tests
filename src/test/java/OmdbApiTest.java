import org.testng.Assert;
import org.testng.annotations.Test;
import response.MovieResponse;

public class OmdbApiTest {


    @Test
    public void searchMovie() {
        String title = "Harry Potter and the Sorcerer's Stone";
        String year = "2001";
        String released = "16 Nov 2001";

        HelperMethods helperMethods = new HelperMethods();
        MovieResponse movieResult = helperMethods.BySearchMovieTitle();
        Assert.assertNotNull(movieResult);

        MovieResponse idResult = helperMethods.SearchById(movieResult);
        Assert.assertEquals(idResult.getTitle(), title);
        Assert.assertEquals(idResult.getYear(), year);
        Assert.assertEquals(idResult.getReleased(), released);

    }
}