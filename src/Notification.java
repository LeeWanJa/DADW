import java.util.ArrayList;
import java.util.List;

// 많은 미확인 예외를 사용하는 상황에서 적합한 해결책이 될 노티피케이션 패턴
// 도메인 클래스로 오류 수집
// 해당 클래스는 오류를 수집하기 위한 목적을 지님
public class Notification {
    private final List<String> errors = new ArrayList<>();

    public void addError(final String msg) {
        errors.add(msg);
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public String errorMessage() {
        return errors.toString();
    }

    public List<String> getErrors() {
        return this.errors;
    }
}
