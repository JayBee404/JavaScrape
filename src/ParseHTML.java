import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;

public class ParseHTML {
    public static void main(String[] args) {

        String fileName = ""; // input filename here
        String fileName2 = ""; // output filename here

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName));
             FileWriter fileWriter = new FileWriter(fileName2)) {
            String line;
            while ((line = reader.readLine()) != null) {

                String pattern = "style=\"text-align:left\">\"(.*?)\"<"; // adjust regex pattern as necessary

                Pattern regexPattern = Pattern.compile(pattern);

                Matcher matcher = regexPattern.matcher(line);

                while (matcher.find()) {
                    String title = matcher.group(1);
                    fileWriter.write(title.toLowerCase() + "\n");
                }
            }

        } catch (IOException e) {
            System.err.println("Error reading from the file or writing to the file: " + e.getMessage());
        }
    }
}
