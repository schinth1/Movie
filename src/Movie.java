import java.io.IOException;
import java.util.*;

public class Movie {

    public static void main(String[] args) throws IOException{

        boolean hasWon = false;
        Game game = new Game();
        String movieSelected = game.readMovies();
//        movieSelected = "bird's nest";
//        char[] withSpaces = movieSelected.toCharArray();
//        movieSelected =  movieSelected.replaceAll("\\s","");
        char[] moviePicked = movieSelected.toCharArray();
        System.out.println(movieSelected);

        HashMap<String, List<Integer>> movieMap = new HashMap<>();

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

//        movieMap.forEach((key,value) -> System.out.println(key + " = " + value));


        String movieMasked = game.maskSelectedMovie(movieSelected);
//        System.out.println("Masked Movie" + ' ' + movieMasked);
        char[] guessMovie = movieMasked.toCharArray();
        System.out.println(guessMovie);

        System.out.println("I have randomly chosen a movie.");
        System.out.println("Try to guess it..");

        Scanner scanner = new Scanner(System.in);

//        while(!hasWon) {
        for(int i=20; i>0; i--) {
            System.out.println("You have " +  i + " guess(es) left. Choose again.");
            char c = scanner.next().charAt(0);

            if(movieMap.containsKey(Character.toString(c))) {
                Integer idx = movieMap.get(Character.toString(c)).remove(0);
                List<Integer>  idxList = (ArrayList<Integer>) movieMap.get(Character.toString(c));
                if(idxList.isEmpty()) {
                    movieMap.remove(Character.toString(c));
                }
                guessMovie[idx] = c;
                System.out.println(guessMovie);
            }
            else {
                System.out.println("Wrong character. Choose again..");
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
