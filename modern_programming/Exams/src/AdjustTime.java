import java.sql.Date;
import java.time.format.DateTimeFormatter;

public class AdjustTime extends TimeClass{

    public void incrementSecond() {
        super.setSecond(super.getSecond() + 1);
        return;
    }

    public void incrementMinute() {
        super.setMinute(super.getMinute() + 1);
        return;
    }

    public void incrementHour() {
        super.setHour(super.getHour() + 1);
        return;
    }

    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("H:MM:SS aa");
        String timeStr = String.format("%d:%d:%d", super.getHour(), super.getMinute(), super.getSecond());
        Date date = (Date)dtf.parse(timeStr);
        String universalStr = date.toString();
        return universalStr;
    }

    public String toUniversalString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:MM:SS");
        String timeStr = String.format("%d:%d:%d", super.getHour(), super.getMinute(), super.getSecond());
        Date date = (Date)dtf.parse(timeStr);
        String universalStr = date.toString();
        return universalStr;
    }

}
