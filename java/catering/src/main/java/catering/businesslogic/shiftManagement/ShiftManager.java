package catering.businesslogic.shiftManagement;

import java.time.Duration;
import java.util.ArrayList;

public class ShiftManager {
    private ShiftBoard shiftBoard;

    public ShiftManager() {
        this.shiftBoard = new ShiftBoard();
    }

    public ShiftBoard getCurrentBoard() {
        return this.shiftBoard;
    }

    public boolean isAssigned(User c, Shift s) {
        // implementation
    }

    public ArrayList<Shift> getShifts() {
        // implementation
    }

    public void removeShift(User c, Shift sh) {
        // implementation
    }

    public boolean isAvailable(User c, Shift sh, Duration tm) {
        // implementation
    }
}