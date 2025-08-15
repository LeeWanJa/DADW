import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

public class BankStatementCSVParserTest {
    private final BankStatementParser statementParser = new BankStatementCSVParser();

    // @Test는 해당 메서드 유닛 테스트의 실행 대상임을 지정
    @Test
    public void shouldParseOneCorrectLine() throws Exception {
        // Given-When-Then 공식
        /*
            1. 테스트의 콘텍스트를 설정한다. 예제에서는 파싱할 행을 설정한다.
            2. 동작을 실행한다. 예제에서는 입력 행을 파싱한다.
            3. 예상된 경과를 어셔션으로 지정한다. 예제에서는 날짜, 금액, 설명이 제대로 파싱되었는지 설명한다.
         */

        // 특정 입력을 제대로 처리하는지 테스트
        final String line = "30-01-2017,-50,Tesco";

        final BankTransaction result = statementParser.parseFrom(line);

        final BankTransaction expected =
                new BankTransaction(LocalDate.of(2017, Month.JANUARY, 30), -50, "Tesco");
        final double tolerance = 0.0d;
        
        Assert.assertEquals(expected.getDate(), result.getDate());
        Assert.assertEquals(expected.getAmount(), result.getAmount(), tolerance);
        Assert.assertEquals(expected.getDescription(), result.getDescription());
        
        /*
            Assert.fail(msg) - 메서드 실행 결과를 실패보 만듦, 테스트 코드를 구현하기 전 플레이스홀더로 유연하게 활용 가능
            Assert.assertEquals(expected, actual) - 두 값이 같은지 테스트
            Assert.assertEquals(expected, actual, delta) - 두 float 이나 double이 delta 범위에서 같은지 테스트
            Assert.assertNotNull(object) - 객체가 null 이 아닌지 테스트
         */
    }
}
