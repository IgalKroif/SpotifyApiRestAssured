package api.utilities;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
public class ReusableMethods {
        public static Random random = new Random();
        public static String randMarket = random.toString();

        public static String getRandomMarket(List<String> markets) {
            if (markets.isEmpty()) {
                throw new IllegalArgumentException("The list of markets is empty.");
            }

            int index = random.nextInt(markets.size());
            return markets.get(index);
        }
}
