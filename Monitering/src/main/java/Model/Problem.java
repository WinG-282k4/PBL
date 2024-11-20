package Model;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Problem {
    private String eventId;
    private String name;
    private int severity; // mức độ nghiêm trọng
    private long clock;  // Thời gian xảy ra sự kiện
    private long ackClock;  // Thời gian khi sự kiện được ghi nhận
    private boolean acknowledged; //Đã xác nhận chưa
    private String message;

    // Constructor
    public Problem(String eventId, String name, int severity, long clock, long ackClock, boolean acknowledged, String message) {
        this.eventId = eventId;
        this.name = name;
        this.severity = severity;
        this.clock = clock;
        this.ackClock = ackClock;  // Lưu thời gian ghi nhận
        this.acknowledged = acknowledged;
        this.message = message;
    }

    // Chuyển epoch time (giây) thành định dạng ngày tháng
    public String convertEpochToDate(long epochTime) {
        Instant instant = Instant.ofEpochSecond(epochTime);
        LocalDateTime dateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }

    // Getter cho time string
    public String getClockAsDate() {
        return convertEpochToDate(clock);  // Đổi clock thành ngày tháng
    }

    public String getAckClockAsDate() {
        return convertEpochToDate(ackClock);  // Đổi ackClock thành ngày tháng
    }

    // Getters và Setters
    public String getEventId() {
        return eventId;
    }

    public String getName() {
        return name;
    }

    public int getSeverity() {
        return severity;
    }

    public long getClock() {
        return clock;
    }

    public long getAckClock() {
        return ackClock;
    }

    public boolean isAcknowledged() {
        return acknowledged;
    }

    public String getMessage() {
        return message;
    }
    
    public void Display() {
    	 System.out.println("Problem ID: " + getEventId());
    	    System.out.println("Name: " + getName());
    	    System.out.println("Severity: " + getSeverity());
    	    System.out.println("Time: " + getClockAsDate());
    	    System.out.print("Time xác nhận: " + getAckClockAsDate());
    	    System.out.println("Acknowledged: " + isAcknowledged());
    	    System.out.println("Message: " + getMessage());
    }
}
