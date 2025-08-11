import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

// CSV 파싱 로직
// CSV 파일을 파싱한다! 라는 하나의 책임만을 가짐 -> 단일 책임의 원칙
public class BankStatementCSVParser {
    // 해당 클래스는 오직 데이터 형식이 바뀌었을때만 수정
    // ex) dd-MM-yyyy -> yyyy-MM-dd
    private static final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    // 아래의 메서드는 바뀔 필요가 없다! 핵심적인 파싱 로직!
    private BankTransaction parseFromCSV(final String line){
        final String[] columns = line.split(",");

        final LocalDate date = LocalDate.parse(columns[0], DATE_PATTERN);
        final double amount = Double.parseDouble(columns[1]);
        final String description = columns[2];

        return new BankTransaction(date, amount, description);
    }

    public List<BankTransaction> parseLinesFromCSV(final List<String> lines){
        final List<BankTransaction> bankTransactions = new ArrayList<>();
        for(final String line : lines){
            bankTransactions.add(parseFromCSV(line));
        }
        return bankTransactions;
    }
}
