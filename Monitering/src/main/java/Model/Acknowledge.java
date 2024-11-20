package Model;

import java.text.SimpleDateFormat;
import java.util.Date;

//Lớp ghi lại quá trình xác minh vấn đề
public class Acknowledge {
    private String message;
    private long clock;
    private String user;

    public Acknowledge(String message, long clock, String user) {
        this.message = message;
        this.clock = clock;
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public long getClock() {
        return clock;
    }

    public String getUser() {
        return user;
    }

    public String getFormattedTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date(clock * 1000));
    }
}

