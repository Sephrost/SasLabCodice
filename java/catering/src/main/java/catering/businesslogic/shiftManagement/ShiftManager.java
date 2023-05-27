package catering.businesslogic.shiftManagement;

import java.time.Duration;
import java.util.ArrayList;

public class ShiftManager {
    private static ShiftManager instance;
    private ShiftBoard shiftBoard;

    private ShiftManager() {
    }

    public static ShiftManager getInstance() {
        if (instance == null) {
            instance = new ShiftManager();
        }
        return instance;
    }

    public ShiftBoard getCurrentBoard() {
        // implementation
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