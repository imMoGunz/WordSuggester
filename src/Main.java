import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        WordSuggester wordSuggester = new WordSuggester(3);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter sentences to train the predictor (enter 'q' to quit):");
        while (true) {
            String sentence = scanner.nextLine().toLowerCase();
            if (sentence.equals("q")) {
                break;
            }
            wordSuggester.addData(sentence);
        }

        System.out.println("\nEnter word for prediction (enter 'q' to quit):");
        while (true) {
            String wordToPredict = scanner.nextLine().toLowerCase();
            if (wordToPredict.equals("q")) {
                break;
            }
            String prediction = wordSuggester.getPredictions(wordToPredict);
            System.out.println("Prediction: " + prediction);

        }
            scanner.close();
    }
}