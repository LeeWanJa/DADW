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

        /*
            <기능>
            콤마로 열 분리 -> 금액 추출 -> 금액을 double로 파싱

            <한계>
            파일이 비어있다면?
            데이터에 문제가 있어 파싱하지 못한다면?
            행의 데이터가 완벽하지 않다면?
            ...
         */
    }
}