package catering.businesslogic.shiftManagement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Date;

import catering.businesslogic.user.User;
import catering.persistence.PersistenceManager;
import catering.persistence.ResultHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Shift {
    int id;
    private Date date;
    private LocalTime startTime;
    private LocalTime endTime;

    public void decreaseAvailableTime(User c, int t) {
        // implementation
    }

    public void increaseAvailableTime(User c, int t) {
        // implementation
    }

    public boolean isAssigned(User c) {
        // implementation
    }

    public boolean isAvailable(User c, Duration tm) {
        // implementation
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
}