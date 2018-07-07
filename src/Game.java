import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

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
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Array out of bounds exception");
        }
        return randomMovie;
    }

     public void guessMovie() {
        boolean hasWon = false;
        int chancesLeft = 10;
        Map<String, List<Integer>> movieMap = new HashMap<>();

        String selectedMovie = readMovies();
        String maskedMovie = selectedMovie.replaceAll("\\w", "-");
        char[] moviePicked = selectedMovie.toCharArray();
//        System.out.println(selectedMovie);

        for(int i=0; i<moviePicked.length; i++) {
            if(movieMap.containsKey(Character.toString(moviePicked[i]))) {
                movieMap.get(Character.toString(moviePicked[i])).add(i);
            }
            else{
                List<Integer> indexList = new ArrayList<>();
                indexList.add(i);
                movieMap.put(Character.toString(moviePicked[i]), indexList);
            }
        }

        char[] guessMovie = maskedMovie.toCharArray();
        System.out.println(guessMovie);

        System.out.println("I have randomly chosen a movie.");
        System.out.println("Try to guess it..");
        Scanner scanner = new Scanner(System.in);

        while(!hasWon) {
            char c = scanner.next().charAt(0);
            if(movieMap.containsKey(Character.toString(c))) {
                Integer index = movieMap.get(Character.toString(c)).remove(0);
                List<Integer>  indexList = (ArrayList<Integer>) movieMap.get(Character.toString(c));
                if(indexList.isEmpty()) {
                    movieMap.remove(Character.toString(c));
                }
                guessMovie[index] = c;
                System.out.println(guessMovie);
            }
            else {
                chancesLeft -= 1;
                if(chancesLeft == 0) {
                    hasWon = false;
                    break;
                }
                else {
                    System.out.println("Wrong character. You have " + chancesLeft + " guess(es) left. Choose again.");
                }
            }
            if(Arrays.equals(moviePicked, guessMovie)) {
                System.out.println("You won the game");
                hasWon = true;
                break;
            }
        }

        if(!hasWon) {
            System.out.println("You lost the game. Try again");
        }
    }
}
