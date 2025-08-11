import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    private static final String RESOURCES = "src\\resources\\";

    public static void main(String[] args) throws IOException {
        final Path path = Paths.get(RESOURCES + "DADW.csv");
        final List<String> lines = Files.readAllLines(path);
        double total = 0d;
        for(final String line : lines){
            System.out.println(line);
            final String[] columns = line.split(",");
            final double amount = Double.parseDouble(columns[1]);
            total += amount;
            System.out.println("total = " + total);
        }

        System.out.println("The total for all transactions is " + total);
    }
}