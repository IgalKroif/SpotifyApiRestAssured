package api.utilities;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
public class ReusableMethods {
        public static Random random = new Random();
        public static String randMarket = random.toString();

        //RANDOMIZES A MARKET FROM AVAILABLE LIST
        public static String getRandomMarket(List<String> markets) {
            if (markets.isEmpty()) {
                throw new IllegalArgumentException("The list of markets is empty.");
            }

            int index = random.nextInt(markets.size());
            return markets.get(index);
        }

        //RANDOMIZES LIMIT QUERY PARAM
            public static int generateRandomNumber() {
                Random random = new Random();
                return random.nextInt(101); // Generates a random number between 0 and 100 (inclusive)
        }
}
