package catering.businesslogic.shiftManagement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.Date;

import catering.persistence.PersistenceManager;
import catering.persistence.ResultHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Shift {
    private int id;
    private Date date;
    private LocalTime startTime;
    private LocalTime endTime;

	public int getId(){
		return id;
	}

    public Date getDate(){
        return date;
    }

    public static ObservableList<Shift> getAllShifts() {
        ObservableList<Shift> shifts = FXCollections.observableArrayList();
        String query = "SELECT * FROM Shifts";
        PersistenceManager.executeQuery(query, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                Shift shift = new Shift();
                shift.id = rs.getInt("id");
                shift.date = rs.getDate("date");
                shift.startTime = rs.getTime("starttime").toLocalTime();
                shift.endTime = rs.getTime("endtime").toLocalTime();
                shifts.add(shift);
            }
        });
        return shifts;
    }

	public static Shift loadShift(int id) {
		String query = "SELECT * FROM Shifts WHERE id = " + id;
		Shift shift = new Shift();
		PersistenceManager.executeQuery(query, new ResultHandler() {
			@Override
			public void handle(ResultSet rs) throws SQLException {
				shift.id = rs.getInt("id");
				shift.date = rs.getDate("date");
				shift.startTime = rs.getTime("starttime").toLocalTime();
				shift.endTime = rs.getTime("endtime").toLocalTime();
			}
		});
		return shift;
	}
}