import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class BankStatementValidator {

    private String description;
    private String date;
    private String amount;

    public BankStatementValidator(String description, String date, String amount) {
        // Objects.requireNonNull = null check를 위한 메서드, null이 들어오면 NullPointerException이 발생
        // 바로바로 예외가 발생해서 디버깅 시 편리함 + 가독성 goods
        this.description = Objects.requireNonNull(description);
        this.date = Objects.requireNonNull(date);
        this.amount = Objects.requireNonNull(amount);
    }

//    // 과도하게 세밀한 예외처리
//    public boolean validate() throws DescriptionTooLongException, InvalidDateFormat, DateInTheFutureException, InvalidAmountException{
//        if(this.description.length() < 100){
//            throw new DescriptionTooLongException();
//        }
//
//        final LocalDate parsedDate;
//        try {
//            parsedDate = LocalDate.parse(this.date);
//        }
//        catch (DateTimeParseException e) {
//            throw new InvalidDateFormat();
//        }
//        if(parsedDate.isAfter(LocalDate.now())) {
//            throw new DateInTheFutureException();
//        }
//
//        try {
//            Double.parseDouble(amount);
//        }
//        catch (NumberFormatException e) {
//            throw new InvalidAmountException();
//        }
//        return true;
//    }


//    // 과도하게 덤덤한 예외처리 메서드
//    public boolean validate() {
//        if(this.description.length() > 100)
//            throw new IllegalArgumentException("Description is too long");
//
//        final LocalDate parsedDate;
//        try {
//            parsedDate = LocalDate.parse(this.date);
//        }
//        catch (DateTimeParseException e) {
//            throw new IllegalArgumentException("Invalid format for date", e);
//        }
//        if(parsedDate.isAfter(LocalDate.now())) {
//            throw new IllegalArgumentException("date cannot be in the future!");
//        }
//
//        try {
//            Double.parseDouble(amount);
//        }
//        catch (NumberFormatException e) {
//            throw new IllegalArgumentException("Invalid format for amount", e);
//        }
//        return true;
//    }
    
    
    // 노티피케이션 패턴
    public Notification validate(){
        final Notification notification = new Notification();
        if(this.description.length() > 100)
            notification.addError("The description is too long");

        final LocalDate parsedDate;
        try {
            parsedDate = LocalDate.parse(this.date);
            if(parsedDate.isAfter(LocalDate.now()))
                notification.addError("The date is in the future");
        }
        catch(DateTimeParseException e){
            notification.addError("Invalid date format");
        }

        final double amount;
        try {
            amount = Double.parseDouble(this.amount);
        }
        catch (NumberFormatException e){
            notification.addError("Invalid format for amount");
        }

        return notification;
    }
}
