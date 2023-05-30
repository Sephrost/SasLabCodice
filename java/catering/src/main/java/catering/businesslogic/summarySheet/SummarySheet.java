package catering.businesslogic.summarySheet;

import java.security.Provider.Service;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import catering.businesslogic.event.ServiceInfo;
import catering.businesslogic.event.ServiceInfo.State;
import catering.businesslogic.kitchenJob.KitchenJob;
import catering.businesslogic.shiftManagement.Shift;
import catering.businesslogic.user.User;
import catering.persistence.PersistenceManager;
import catering.persistence.ResultHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;


public class SummarySheet {
	private final ServiceInfo service;
	private List<Task> task;
	private User chef;
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
		return true;
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
		String query = "SELECT * FROM SummarySheets";
		ObservableList<SummarySheet> summarySheets = FXCollections.observableArrayList();
		HashMap<ServiceInfo, List<Task>> taskList = new HashMap<ServiceInfo, List<Task>>();
		HashMap<ServiceInfo, User> chefList = new HashMap<ServiceInfo, User>();
		
			PersistenceManager.executeQuery(query, new ResultHandler() {
				@Override
				public void handle(ResultSet rs) throws SQLException {
					while(rs.next()) {
						ServiceInfo service = ServiceInfo.loadServiceInfo(rs.getInt("service_id"));
						Task task = Task.loadTask(rs.getInt("task_id"));
						User chef = User.loadUserById(rs.getInt("chef_id"));

						// Append task to taskList
						if(taskList.containsKey(service)) {
							taskList.get(service).add(task);
						} else {
							List<Task> tasks = new ArrayList<Task>();
							tasks.add(task);
							taskList.put(service, tasks);
						}
						chefList.put(service, chef);
					}
				}
			});
		
		for(ServiceInfo service : taskList.keySet()) {
			SummarySheet summarySheet = new SummarySheet(service);
			summarySheet.task = taskList.get(service);
			summarySheet.chef = chefList.get(service);
			summarySheets.add(summarySheet);
		}
		
		return summarySheets;
	}
}
