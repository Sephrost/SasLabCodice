package catering.businesslogic.service;

import java.time.LocalTime;

import catering.businesslogic.summarySheet.SummarySheet;
import catering.businesslogic.user.User;

public class Service {
    private String name;
    private Type type;
    private String notes;
    private String location;
    private State state;
    private LocalTime startHour;
    private LocalTime endHour;
    private SummarySheet sheet;

    public boolean isInCharge(User u) {
        // implementation
    }

    public enum Type {
        // implementation
    }

    public enum State {
        // implementation
    }
}