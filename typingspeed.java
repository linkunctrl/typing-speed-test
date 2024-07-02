import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class typingspeed {
    public static void main(String[] args) {
        System.out.println("Type the following text:\n");
        String[] textToType = generateRandomText();

        for (String sentence : textToType) {
            System.out.println(sentence);
        }

        // Prompt the user to start typing
        System.out.println("\nStart typing below (press Enter after each line and twice for results):");

        // Capture user input and measure time
        Scanner scanner = new Scanner(System.in);
        long startTime = System.currentTimeMillis();
        String typedText1 = scanner.nextLine();
        String typedText2 = scanner.nextLine();
        long endTime = System.currentTimeMillis();

        // Combine the two lines of typed text
        String typedText = typedText1 + " " + typedText2;

        // Combine the provided text
        String providedText = String.join(" ", textToType);

        // Calculate typing speed
        long timeTaken = endTime - startTime;
        double timeInSeconds = timeTaken / 1000.0;
        int wordsTyped = typedText.split("\\s+").length;
        int typingSpeed = (int) (wordsTyped / (timeInSeconds / 60.0));

        // Calculate errors
        List<Integer> errorIndices = calculateErrors(providedText, typedText);

        // Display results
        System.out.println("\nYour typing speed: " + typingSpeed + " words per minute");
        System.out.println("Number of errors: " + errorIndices.size());
        if (!errorIndices.isEmpty()) {
            System.out.println("\nErrors occurred at:");
            for (int index : errorIndices) {
                System.out.println("- Expected: " + providedText.split("\\s+")[index]);
                System.out.println("  Actual:   " + typedText.split("\\s+")[index]);
            }
        }

        scanner.close();
    }

    private static String[] generateRandomText() {
        String[][] paragraphs = {
            {
                "Apple banana cherry date eggplant fig grape honeydew iceberg jackfruit kiwi lemon mango nectarine orange papaya quince raspberry strawberry tangerine ugli fruit vanilla watermelon xigua yam zucchini."
            },
            {
                "Zebra yellow xylophone waltz volcano unicorn tiger snake rabbit quail penguin octopus narwhal monkey lion kangaroo jellyfish iguana horse giraffe flamingo elephant duck coyote bear antelope."
            },
            {
                "As she reached the top of the hill, she saw a small cottage in the distance. It looked abandoned, but something about it drew her in. Curiosity piqued, she decided to explore the old building and uncover its secrets."
            },
            {
                "She packed her bag with supplies and set off at dawn. The forest was thick and dark, but Lara was determined. She followed the clues from the old map she found in her grandmother's attic. Hours turned into days, and just when she was about to give up, she stumbled upon a hidden cave."
            },
            {
                "Developers often use Java for building enterprise-level applications. It provides a robust and secure platform for development."
            }
        };

        Random random = new Random();
        int randomIndex = random.nextInt(paragraphs.length);

        return paragraphs[randomIndex];
    }

    private static List<Integer> calculateErrors(String providedText, String typedText) {
        String[] providedWords = providedText.split("\\s+");
        String[] typedWords = typedText.split("\\s+");
        List<Integer> errorIndices = new ArrayList<>();

        for (int i = 0; i < Math.min(providedWords.length, typedWords.length); i++) {
            if (!providedWords[i].equals(typedWords[i])) {
                errorIndices.add(i);
            }
        }

        // If lengths don't match, mark the extra words in typedText as errors
        for (int i = Math.min(providedWords.length, typedWords.length); i < typedWords.length; i++) {
            errorIndices.add(i);
        }

        return errorIndices;
    }
}
