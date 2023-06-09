package catering.businesslogic.shiftManagement;

import java.time.Duration;
import java.util.ArrayList;

import catering.businesslogic.user.User;


public class ShiftBoard {
    private static ShiftBoard instance;
    private ArrayList<Shift> shifts;

    private ShiftBoard() {
        this.shifts = new ArrayList<Shift>();
        shifts.addAll(Shift.getAllShifts());
    }

    public static ShiftBoard getInstance() {
        if (instance == null) {
            instance = new ShiftBoard();
        }
        return instance;
    }

    public boolean isAssigned(User c, Shift s) {
        
    }

    public boolean isAvailable(User c, Shift sh, Duration tm) {
        // implementation
    }

	public ArrayList<Shift> getShifts() {
		return shifts;
	}
}
