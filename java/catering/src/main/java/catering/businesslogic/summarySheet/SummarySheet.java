package catering.businesslogic.summarySheet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import catering.businesslogic.event.ServiceInfo;
import catering.businesslogic.event.ServiceInfo.State;
import catering.businesslogic.kitchenJob.KitchenJob;
import catering.businesslogic.shiftManagement.Shift;
import catering.businesslogic.user.User;
import catering.persistence.PersistenceManager;
import catering.persistence.ResultHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class SummarySheet {
	private final ServiceInfo service;
	private List<Task> task;
	// TODO: controllare menù da aggiungere

	public SummarySheet(ServiceInfo service) {
		this.service = service;
		this.task = new ArrayList<Task>();
	}

	public ServiceInfo getService() {
		return service;
	}

	public void addAssignment(KitchenJob kj) {
		// implementation
	}

	public void createTask(KitchenJob kj) {
		// implementation
	}

	public void addKitchenJob(KitchenJob kj) {
		// implementation
	}

	public void removeKitchenJob(KitchenJob kj) {
		// implementation
	}

	public void removeTask(Task task) {
		// implementation
	}

	public boolean hasTask() {
		// implementation
	}

	public void swapTaskPositions(Task task, int position) {
		// implementation
	}

	public void assignTask(Task task, Shift shift, User cook, String expectedTime, String quantity) {

	}

	public void removeTaskAssignment(Task task) {
		// implementation
	}

	public void taskCompleted(Task task) {
		// implementation
	}

	public void getCurrentTaskStatuses() {
		// implementation
	}

	// Static method for persistence
	public static ObservableList<SummarySheet> getAllSummarySheets() {
		String query = "SELECT * FROM SummarySheets join Services on SummarySheets.service_id = Services.id group by SummarySheets.id";
		List<SummarySheet> summarySheets = new ArrayList<SummarySheet>();
		
		PersistenceManager.executeQuery(query, new ResultHandler() {
			@Override
			public void handle(ResultSet rs) throws SQLException {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String notes = rs.getString("notes");
				String location = rs.getString("location");
				State state = State.valueOf(rs.getString("state"));
				Date date = rs.getDate("service_date");
				LocalTime startHour = rs.getTime("time_start").toLocalTime();
				LocalTime endHour = rs.getTime("time_end").toLocalTime();
				int expected_participants = rs.getInt("expected_participants");
				int EventId = rs.getInt("event_id");
				ServiceInfo service = new ServiceInfo(name, notes, location, state, date, startHour, endHour, proposedMenu, approvedMenu, expected_participants, EventId);
				SummarySheet summarySheet = new SummarySheet(service);
				summarySheets.add(summarySheet);
			}
		});
			

		
		return FXCollections.observableList(summarySheets);
	}
}
