package catering.businesslogic.shiftManagement;

import java.time.Duration;
import java.util.ArrayList;

import catering.businesslogic.user.User;


public class ShiftBoard {
    private ArrayList<Shift> shifts;

    public ShiftBoard() {
        this.shifts = new ArrayList<Shift>();
        shifts.addAll(Shift.getAllShifts());
    }

    public boolean isAssigned(User c, Shift s) {
        // implementation
    }

    public boolean isAvailable(User c, Shift sh, Duration tm) {
        // implementation
    }


}
