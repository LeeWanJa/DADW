import java.time.Month;
import java.util.List;

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
}