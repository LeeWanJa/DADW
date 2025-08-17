import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;

public class BankStatementAnalyzer {
    private static final String RESOURCES = "src\\resources\\";

    public void analyze(final String fileName, final BankStatementParser bankStatementParser)
        throws IOException {
            final Path path = Paths.get(RESOURCES + fileName);
            final List<String> lines = Files.readAllLines(path);

            final List<BankTransaction> bankTransactions = bankStatementParser.parseLinesFrom(lines);
            final BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);

            collectSummary(bankStatementProcessor);
    }

    private void collectSummary(final BankStatementProcessor bankStatementProcessor){
        System.out.println("The total for all transactions is "
                + bankStatementProcessor.calculateTotalAmount());

        System.out.println("The total for transactions in January is "
                + bankStatementProcessor.calculateTotalInMonth(Month.JANUARY));

        System.out.println("The total for transactions in February is "
                + bankStatementProcessor.calculateTotalInMonth(Month.FEBRUARY));

        System.out.println("The total salary received is "
                + bankStatementProcessor.calculateTotalForCategory("Salary"));

        // 특정 날짜 범위에서 최대, 최소 거래 내역을 찾는 다양한 집계 연산
        System.out.println("Maximum and minimum deposit and withdrawal details from January to February = "
                + bankStatementProcessor.findMaxMinTransactionDetails(Month.JANUARY, Month.FEBRUARY));
    }
}