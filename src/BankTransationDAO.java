import java.time.LocalDate;

// 같은 데이터나 도메인 객체를 처리하는 메서드를 그룹화
// BankTransaction 객체를 만들고, 읽고, 갱신하고, 삭제하는 기능(CRUD) 수행
// 테이블이나 특정 도메인 객체를 저장하는 데이터베이스와 상호작용할 때 흔히 볼 수 있음
// 이 패턴을 보통 데이터 접근 객체(data access object, DAO)라 부르고 객체를 식별하는 일종의 ID가 필요
// 기본적으로 DAO는 영구 저장 데이터베이스나 인메모리 데이터베이스 같은 데이터 소스로의 접근을 추상화
public class BankTransationDAO extends RuntimeException {
    public BankTransaction create(final LocalDate date, final double amount, final String description){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public BankTransaction read(final long id){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public BankTransaction update(final long id){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public BankTransaction delete(final BankTransaction bankTransaction){
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
