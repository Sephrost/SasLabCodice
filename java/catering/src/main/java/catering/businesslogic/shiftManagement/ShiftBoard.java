package catering.businesslogic.shiftManagement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import catering.businesslogic.summarySheet.Task;
import catering.businesslogic.user.User;
import catering.persistence.PersistenceManager;
import catering.persistence.ResultHandler;


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

    public boolean isAvailable(User c, Shift sh) {
        String query = "SELECT * FROM Shiftboard WHERE shift_id='"+sh.getId()+"' AND user_id='"+c.getId()+"'";
        boolean res[] = {false};
        PersistenceManager.executeQuery(query, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                if(PersistenceManager.getQueryRowCount(query) > 0)
                    res[0] = rs.getString("state").equals("available");    
            }
        });
        return res[0];
    }

	public ArrayList<Shift> getShifts() {
		return shifts;
	}

    public static void setUnavailable(Task t){
        if (t.getShift() == null || t.getCook() == null)
            return;
        String query = "UPDATE Shiftboard SET state='occupied' WHERE shift_id='"+t.getShift().getId()+"' AND user_id='"+t.getCook().getId()+"'";
        PersistenceManager.executeUpdate(query);
    }

    public static void setAvailable(User c, Shift sh){
        String query = "UPDATE Shiftboard SET state='available' WHERE shift_id='"+sh.getId()+"' AND user_id='"+c.getId()+"'";
        PersistenceManager.executeUpdate(query);
    }

}
