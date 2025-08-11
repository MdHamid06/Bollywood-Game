import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Guess2 {
    static HashMap<Character, HashSet<Integer>> idxMap;
    static Scanner sc;
    static HashMap<Integer, Character> guessedChars;
    static HashSet<Character> incorrectGuess;
    public static void main(String[] args) {
        // Movie: DIL DEEWANA
        sc = new Scanner(System.in);
        guessedChars = new HashMap<>();
        incorrectGuess = new HashSet<>();
        String movie = "DIL DEEWANA";    //name of the movie

        fillMap(movie); // func to fill map with char and their indices

        for(int i = 0; i < movie.length(); i++){
            if(isVowel(movie.charAt(i)))    guessedChars.put(i,movie.charAt(i));
        }

        printRemaining(movie); 
        attempt(movie);
    }

    private static void attempt(String movie) {
        int i = 9;
        while(i > 0){
            System.out.println(i + " chances remaining!");
            System.out.println();
            String s = sc.next();
            if(s.length() > 1){
                System.out.println("Kindly enter just one letter");
            }
            else{
                char input = s.toUpperCase().charAt(0);
                isRepeated(input);
                if(idxMap.containsKey(input) && !isVowel(input)){
                    HashSet<Integer> guessedIndexSet = idxMap.get(input);
                    for(int idx : guessedIndexSet){
                        if(guessedChars.containsKey(idx))   {
                            System.out.println(input + " already used");
                            break;
                        }
                        else    guessedChars.put(idx, input);
                    }   
                }
                else if(!incorrectGuess.contains(input) && !isVowel(input)){
                    System.out.println("Incorrect guess!");
                    incorrectGuess.add(input);
                        i--;
                    }
                
            }
            printRemaining(movie); 
            if(guessedChars.size() == movie.length()){
                System.out.println("Hurrah you Won!!");
                return;
            }
        }
        System.out.println("\nYou Lost, the movie was " + movie);
    }

    private static void printRemaining(String movie){
        for(int i = 0; i < movie.length(); i++){
            if(movie.charAt(i) == ' ')  System.out.print("  ");
            else if(guessedChars.containsKey(i)) System.out.print(guessedChars.get(i) + " ");
            else System.out.print("_ ");
        }
        System.out.println();
    }

    private static void fillMap(String movie) {
        idxMap = new HashMap<>();
        for(int i = 0; i < movie.length(); i++){
            char ch = movie.charAt(i);
            if(ch == ' ')   guessedChars.put(i, ' ');
            else if(idxMap.containsKey(ch))  idxMap.get(ch).add(i);
            else{
                HashSet<Integer> idxSet = new HashSet<>();
                idxSet.add(i);
                idxMap.put(ch,idxSet);
            }
        }
    }

    public static void isRepeated(char input){
        if(isVowel(input)) System.out.println(input + " is a vowel!");
        else if(incorrectGuess.contains(input)) System.out.println(input + " was wrongly guesssed earlier ");
    }

    public static boolean isVowel(char c){
        if(c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U')    return true;
            return false;
    }
}
