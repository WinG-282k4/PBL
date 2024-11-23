package Model;

import java.text.SimpleDateFormat;
import java.util.Date;

//Lớp ghi lại quá trình xác minh vấn đề
public class Acknowledge {
    private String message;
    private String clock;
    private String old_severity;
    private String new_severity;
    
    

    public Acknowledge(String message, String clock, String _old, String _new) {
        this.message = message;
        this.clock = clock;
        this.old_severity = _old;
        this.new_severity = _new;
    }

    public String getMessage() {
        return message;
    }

    public String getClock() {
        return clock;
    }
    
    public String get_old() {
    	return old_severity;
    	
    }
    
    public String get_new() {
    	return new_severity;
    }

}

