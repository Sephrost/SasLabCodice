package catering.businesslogic.event;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import catering.businesslogic.menu.Menu;
import catering.persistence.PersistenceManager;
import catering.persistence.ResultHandler;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

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
	private Time startHour;
	private Time endHour;
	private Menu proposedMenu;
	private Menu approvedMenu;
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
	
	public Menu getMenu() {
		if(approvedMenu != null)
			return approvedMenu;
		else
			return proposedMenu;
	}

	public int getExpectedParticipants() {
		return expected_participants;
	}

	public EventInfo getEventInfo() {
		return EventInfo.loadEventInfoById(EventId);
	}

	// tostring method override
	public String toString() {
		return name + ": " + date + ", " + startHour + "-" + endHour + ", " + expected_participants + " pp.";
	}

	// STATIC METHODS FOR PERSISTENCE
	public static ObservableList<ServiceInfo> loadServiceInfoForEvent(int event_id) {
		ObservableList<ServiceInfo> result = FXCollections.observableArrayList();
		String query = "SELECT * FROM Services WHERE event_id = " + event_id;
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
				serv.date = rs.getDate("service_date");
				serv.startHour = rs.getTime("time_start");
				serv.endHour = rs.getTime("time_end");
				serv.expected_participants = rs.getInt("expected_participants");
				serv.EventId = event_id;
				
				int proposedMenuId = rs.getInt("proposed_menu_id");
				int approvedMenuId = rs.getInt("approved_menu_id");
				// Menu.loadAllMenus().forEach(menu -> {
				// 	if (menu.getId() == proposedMenuId) {
				// 		serv.proposedMenu = menu;
				// 	}
				// 	if (menu.getId() == approvedMenuId) {
				// 		serv.approvedMenu = menu;
				// 	}
				// });
				if (proposedMenuId != 0) {
					serv.proposedMenu = Menu.loadMenu(proposedMenuId);
				}
				if (approvedMenuId != 0) {
					serv.approvedMenu = Menu.loadMenu(approvedMenuId);
				}
				
				result.add(serv);
			}
		});
		return result;
	}
	public static ServiceInfo loadServiceInfo(int service_id) {
        ObservableList<ServiceInfo> result = FXCollections.observableArrayList();
        String query =
                "SELECT * FROM Services WHERE id = "
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
				int proposedMenu = rs.getInt("proposed_menu_id");
				int approvedMenu = rs.getInt("approved_menu_id");

				Menu.loadAllMenus().forEach(menu -> {
					if (menu.getId() == proposedMenu) {
						serv.proposedMenu = menu;
					}
					if (menu.getId() == approvedMenu) {
						serv.approvedMenu = menu;
					}
				});
				serv.date = rs.getDate("service_date");
				serv.startHour = rs.getTime("time_start");
				serv.endHour = rs.getTime("time_end");
				serv.expected_participants = rs.getInt("expected_participants");
                result.add(serv);
                });
        return result.get(0);
    }


	// https://stackoverflow.com/questions/2265503/why-do-i-need-to-override-the-equals-and-hashcode-methods-in-java
	@Override
	public int hashCode(){
		final int prime = 31;
    	int result = 1;
    	return prime * result + id;
	}

	@Override
	public boolean equals(Object obj){
		if (this == obj) return true;
		if (obj == null) return false;
		if (!(obj instanceof ServiceInfo)) return false;
		ServiceInfo other = (ServiceInfo) obj;
		return id == other.id;
	}
}
