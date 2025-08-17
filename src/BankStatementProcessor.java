import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankStatementProcessor {
    private final List<BankTransaction> bankTransactions;

    public BankStatementProcessor(List<BankTransaction> bankTransactions) {
        this.bankTransactions = bankTransactions;
    }

    public double calculateTotalAmount(){
        double total = 0;
        for(final BankTransaction bankTransaction : bankTransactions){
            total += bankTransaction.getAmount();
        }
        return total;
    }

    public double calculateTotalInMonth(final Month month){
        double total = 0;
        for(final BankTransaction bankTransaction : bankTransactions){
            if(bankTransaction.getDate().getMonth() == month)
                total += bankTransaction.getAmount();
        }
        return total;
    }

    public double calculateTotalForCategory(final String category){
        double total = 0;
        for(final BankTransaction bankTransaction : bankTransactions){
            if(bankTransaction.getDescription().equals(category))
                total += bankTransaction.getAmount();
        }
        return total;
    }
    
    // 특정 날짜 범위에서 최대, 최소 거래 내역을 찾는 다양한 집계 연산 추가
    // 반복문과 stream 둘 다로 구현해보자
    public String findMaxMinTransactionDetails(Month from, Month to){
        double max = Double.MIN_VALUE;
        double min = Double.MAX_VALUE;

        for(final BankTransaction bankTransaction : bankTransactions){
            Month month = bankTransaction.getDate().getMonth();

            if(!(from.compareTo(month) <= 0 && to.compareTo(month) >= 0))
                continue;

            double amount = bankTransaction.getAmount();

            if(max < amount)
                max = amount;

            if(min > amount)
                min = amount;
        }

        if(max == Double.MIN_VALUE && min == Double.MAX_VALUE)
            return "no transactions";

        return "max : " + max + " | min : " + min;
    }

    // 월별, 설명별 지출 그룹화 히스토그램
    public Map<Month, Double> MonthHistogram(){
        Map<Month, Double> monthMap = new HashMap<Month, Double>();

        for(BankTransaction bankTransaction :  bankTransactions){
            if(bankTransaction.getAmount() >= 0)
                continue;

            Month month = bankTransaction.getDate().getMonth();

            // 키가 존재하는지 검사 후 존재한다면 기존 값에 덮어씌우기
            // 존재하지 않는다면 새로운 객체를 만든 뒤 add
//            if(monthMap.containsKey(month))
//                monthMap.put(month, monthMap.get(month) + bankTransaction.getAmount());
//            else
//                monthMap.put(month, bankTransaction.getAmount());

            // merge 사용
            monthMap.merge(month, bankTransaction.getAmount(), (v1, v2) -> v1 + v2);
        }

        return monthMap;
    }

    public Map<String, Double> DescriptionHistogram(){
        Map<String, Double> desMap = new HashMap<String, Double>();

        for(BankTransaction bankTransaction :  bankTransactions){
            if(bankTransaction.getAmount() >= 0)
                continue;

            String description = bankTransaction.getDescription();

//            if(desMap.containsKey(description))
//                desMap.put(description, desMap.get(description) + bankTransaction.getAmount());
//            else
//                desMap.put(description, bankTransaction.getAmount());

            desMap.merge(description, bankTransaction.getAmount(), Double::sum);
        }

        return desMap;
    }


    // 특정 금액 이상의 모든 입출력 내역을 검색하는 메서드
    public List<BankTransaction> findTransactionsGreaterThanEquals(final int amount){
        final List<BankTransaction> result = new ArrayList<>();
        for(final BankTransaction bankTransaction : bankTransactions){
            if(bankTransaction.getAmount() >= amount)
                result.add(bankTransaction);
        }
        return result;
    }

    // 특정 월을 검색하는 메서드
    // 이런식의 작성은 중복을 야기함
    public List<BankTransaction> findTransactionsInMonth(final Month month){
        final List<BankTransaction> result = new ArrayList<>();
        for(final BankTransaction bankTransaction : bankTransactions){
            if(bankTransaction.getDate().getMonth() == month)
                result.add(bankTransaction);
        }
        return result;
    }
}