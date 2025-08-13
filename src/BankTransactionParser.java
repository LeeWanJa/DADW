// 권장하지 않는 방법
// 서로 파싱이라는 키워드로 뭉쳐있지만 정작 네 메서드는 서로 관련이 없다.
// 또한 이 클래스는 네 가지 책임을 가지기 때문에 단일 책임 원칙에 위배되기도 한다.
public class BankTransactionParser {
    public BankTransaction parseFromCSV(final String line){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public BankTransaction parseFromJSON(final String line){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public BankTransaction parseFromXML(final String line){
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
