package catering.businesslogic.event;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import catering.businesslogic.summarySheet.SummarySheet;
import catering.persistence.PersistenceManager;
import catering.persistence.ResultHandler;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;

public class ServiceInfo implements EventItemInfo {

	public enum State {
		NOT_STARTED,
		IN_PROGRESS,
		COMPLETED,
		CANCELLED
	}

	private int id;
	private String name;	
	private String notes;
	private String location;
	private State state;
	private Date date;
	private LocalTime startHour;
	private LocalTime endHour;
	private SummarySheet sheet; // TODO: controllare
	private int EventId;

	public ServiceInfo(String name) {
		this.name = name;
	}

	// getter
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Type getType() {
		return type;
	}

	public String getNotes() {
		return notes;
	}

	public String getLocation() {
		return location;
	}

	public State getState() {
		return state;
	}

	public Date getDate() {
		return date;
	}

	public LocalTime getStartHour() {
		return startHour;
	}

	public LocalTime getEndHour() {
		return endHour;
	}

	public SummarySheet getSheet() {
		return sheet;
	}

	// tostring
	@Override
	public String toString() {
		return "ServiceInfo{" +
				"id=" + id +
				", name='" + name + '\'' +
				", type=" + type +
				", notes='" + notes + '\'' +
				", location='" + location + '\'' +
				", state=" + state +
				", date=" + date +
				", startHour=" + startHour +
				", endHour=" + endHour +
				", sheet=" + sheet +
				'}';
	}

	// STATIC METHODS FOR PERSISTENCE

	public static ObservableList<ServiceInfo> loadServiceInfoForEvent(int event_id) {
		ObservableList<ServiceInfo> result = FXCollections.observableArrayList();
		String query = "SELECT id, name, service_date, time_start, time_end, expected_participants " +
				"FROM Services WHERE event_id = " + event_id;
		PersistenceManager.executeQuery(query, new ResultHandler() {
			@Override
			public void handle(ResultSet rs) throws SQLException {
				String s = rs.getString("name");
				ServiceInfo serv = new ServiceInfo(s);
				serv.id = rs.getInt("id");
				serv.date = rs.getDate("service_date");
				serv.timeStart = rs.getTime("time_start");
				serv.timeEnd = rs.getTime("time_end");
				serv.participants = rs.getInt("expected_participants");
				result.add(serv);
			}
		});

		return result;
	}
}
