import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class BankStatementCSVParser implements BankStatementParser {
    private static final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    // BankStatementParser의 추상 메서드 parseFrom을 구체적으로 구현했으므로 private 접근 제어자는 쓸 수 없음
    public BankTransaction parseFrom(final String line)
            throws DateTimeParseException, NumberFormatException, ArrayIndexOutOfBoundsException{
        final String[] columns = line.split(",");

        BankStatementValidator validator = new BankStatementValidator(columns[2], columns[0], columns[1]);
        Notification notification = validator.validate();

        // 오류가 존재한다면 빈 객체 반환
        if(notification.hasErrors()){
            System.out.println(notification.getErrors());
            return new BankTransaction(LocalDate.now(), 0.0, "");
        }

        final LocalDate date = LocalDate.parse(columns[0], DATE_PATTERN);
        final double amount = Double.parseDouble(columns[1]);
        final String description = columns[2];

        return new BankTransaction(date, amount, description);
    }

    public List<BankTransaction> parseLinesFrom(final List<String> lines){
        final List<BankTransaction> bankTransactions = new ArrayList<>();
        for(final String line : lines){
            if(line.isEmpty())
                continue;

            bankTransactions.add(parseFrom(line));
        }
        return bankTransactions;
    }
}
