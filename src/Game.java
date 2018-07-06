import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;


public class Game {

    String maskMovie = null;

    public String readMovies() {
        File file = new File("movies.txt");
        String randomMovie = null;
        try {
            Scanner fileScanner = new Scanner(file);
            List<String> movieList = new ArrayList<String>();

            while (fileScanner.hasNextLine()) {
                String movie = fileScanner.nextLine();
                movieList.add(movie);
            }

            int movieCount = movieList.size();
            Random rand = new Random();
            int randInt = rand.nextInt(movieCount - 1);
            randomMovie = movieList.get(randInt);
//            System.out.println(randomMovie);
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Array out of bounds exception");
        }
        return randomMovie;
    }

    public String maskSelectedMovie(String movie) {
        String maskedMovie = movie.replaceAll("\\w", "-");
        return maskedMovie;
    }
}
