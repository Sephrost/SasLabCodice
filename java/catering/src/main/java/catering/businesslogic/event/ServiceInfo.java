package catering.businesslogic.event;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import catering.persistence.PersistenceManager;
import catering.persistence.ResultHandler;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class ServiceInfo implements EventItemInfo {
	public static enum State {NOT_STARTED, IN_PROGRESS, COMPLETED, CANCELLED};
	private int id;
	private String name;
	private String notes;
	private String location;
	private State state;
	private Date date;
	private Time startHour;
	private Time endHour;
	private int proposedMenu;
	private int approvedMenu;
	private int expected_participants;
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

	public Time getStartHour() {
		return startHour;
	}

	public Time getEndHour() {
		return endHour;
	}
	//getter for menu
	public int getMenu() {
		if(approvedMenu != 0)
			return approvedMenu;
		else
			return proposedMenu;
	}

	public int getExpectedParticipants() {
		return expected_participants;
	}
	public EventInfo getEventId() {
		return EventInfo.loadEventInfoById(EventId); //TODO: controllare
	}

	// tostring method override
	public String toString() {
		return name + ": " + date + ", " + startHour + "-" + endHour + ", " + expected_participants + " pp.";
	}

	// STATIC METHODS FOR PERSISTENCE
	// TODO: cambiare query e mettere i campi giusti
	public static ObservableList<ServiceInfo> loadServiceInfoForEvent(int event_id) {
		ObservableList<ServiceInfo> result = FXCollections.observableArrayList();
		String query = "SELECT *FROM Services WHERE event_id = " + event_id;
		PersistenceManager.executeQuery(query, new ResultHandler() {
			@Override
			public void handle(ResultSet rs) throws SQLException {
				String s = rs.getString("name");
				ServiceInfo serv = new ServiceInfo(s);
				serv.id = rs.getInt("id");
				serv.date = rs.getDate("service_date");
				serv.name = rs.getString("name");
				serv.notes = rs.getString("notes");
				serv.location = rs.getString("location");
				serv.state = State.valueOf(rs.getString("state"));
				serv.proposedMenu = rs.getInt("proposed_menu_id");
				serv.approvedMenu = rs.getInt("approved_menu_id");
				serv.date = rs.getDate("service_date");
				serv.startHour = rs.getTime("time_start");
				serv.endHour = rs.getTime("time_end");
				serv.expected_participants = rs.getInt("expected_participants");
				result.add(serv);
			}
		});
		return result;
	}
	public static ServiceInfo loadServiceInfo(int service_id) {
        ObservableList<ServiceInfo> result = FXCollections.observableArrayList();
        String query =
                "SELECT event_id, name, service_date, time_start, time_end, expected_participants, approved_menu_id "
                        + "FROM Services WHERE id = "
                        + service_id
                        + ";";
        PersistenceManager.executeQuery(
                query,
                rs -> {
                    String s = rs.getString("name");
                    ServiceInfo serv = new ServiceInfo(s);
                    serv.id = rs.getInt("id");
				serv.date = rs.getDate("service_date");
				serv.name = rs.getString("name");
				serv.notes = rs.getString("notes");
				serv.location = rs.getString("location");
				serv.state = State.valueOf(rs.getString("state"));
				serv.proposedMenu = rs.getInt("proposed_menu_id");
				serv.approvedMenu = rs.getInt("approved_menu_id");
				serv.date = rs.getDate("service_date");
				serv.startHour = rs.getTime("time_start");
				serv.endHour = rs.getTime("time_end");
				serv.expected_participants = rs.getInt("expected_participants");
                result.add(serv);
                });
        return result.get(0);
    }
}
