package catering.businesslogic.shiftManagement;

import java.time.Duration;
import java.util.ArrayList;

import catering.businesslogic.UseCaseLogicException;
import catering.businesslogic.user.User;

public class ShiftManager {
    private ShiftBoard shiftBoard;

    public ShiftManager() {
        this.shiftBoard = ShiftBoard.getInstance();
    }

    public ShiftBoard getCurrentBoard() {
        return this.shiftBoard;
    }

    public boolean isAvailable(User c, Shift s) throws UseCaseLogicException {
        if (shiftBoard == null) {
			throw new UseCaseLogicException("ShiftBoard not initialized correctly");
		}
		if (c == null) {
			throw new UseCaseLogicException("User cannot be null");
		}
		if (s == null) {
			throw new UseCaseLogicException("Shift cannot be null");
		}
		
		return this.shiftBoard.isAvailable(c, s);
    }

    public ArrayList<Shift> getShifts() {
        return this.shiftBoard.getShifts();
    }

    public void removeShift(User c, Shift sh) {
        // implementation
    }

}