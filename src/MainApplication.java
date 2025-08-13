import java.io.IOException;

public class MainApplication {
    public static void main(String[] args) throws IOException {
        final BankStatementAnalyzer bankstatmentAnalyzer = new BankStatementAnalyzer();

        // csv로 파서
        // 이후 구현만 한다면 new BankStatementJSONParser()와 같은 BankStatementParser를 구현한 아무 클래스 객체로 갈아기면 됌
        final BankStatementParser bankStatementParser = new BankStatementCSVParser();

        bankstatmentAnalyzer.analyze("DADW.csv", bankStatementParser);
    }
}
