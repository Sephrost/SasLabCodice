package catering.businesslogic.summarySheet;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import catering.businesslogic.event.ServiceInfo;

import catering.businesslogic.kitchenJob.KitchenJob;
import catering.businesslogic.menu.MenuItem;
import catering.businesslogic.shiftManagement.Shift;
import catering.businesslogic.user.User;
import catering.persistence.PersistenceManager;
import catering.persistence.ResultHandler;
import catering.persistence.BatchUpdateHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class SummarySheet {

	private final ServiceInfo service;
	private List<Task> tasks;
	private User chef;

	public SummarySheet(ServiceInfo service) {
		this.service = service;
		this.tasks = new ArrayList<Task>();
		this.chef = service.getEventInfo().getChef();

		for(MenuItem item : service.getMenu().getAllMenuItems()) {
			Task task = new Task(item.getKitchenJob());
			this.tasks.add(task);
		}
	}

	public ServiceInfo getService() {
		return service;
	}

	public User getChef() {
		return chef;
	}


	public Task addKitchenJob(KitchenJob kj) {
		Task task = new Task(kj);
		this.tasks.add(task);
		return task;
	}

	public List<Task> removeKitchenJob(KitchenJob kj) {
		// This is a little different from the DSD because
		// to avoid a ConcurrentModificationException
		List<Task> tasks = new ArrayList<Task>();
		for(Task task : this.tasks) {
			if(task.getKitchenJob().equals(kj)) {
				tasks.add(task);		
			}
		}
		this.tasks.removeAll(tasks);
		return tasks;
	}

	public void removeTask(Task task) {
		// implementation
	}

	public boolean hasTask(Task task) {
		for(Task t : this.tasks) {
			if(t.equals(task)) {
				return true;
			}
		}
		return false;
	}
	public List<Task> getTasks() {
		return this.tasks;
	}

	public void swapTaskPositions(Task task, int position) {
		Task t = this.tasks.get(position);
		int index = this.tasks.indexOf(task);
		this.tasks.remove(task);
		this.tasks.add(index, t);
		this.tasks.add(position + 1, task);
	}

	public Task assignTask(Task task, Shift shift, User cook, Duration expectedTime, String quantity) {
		if (shift != null) {
			task.setShift(shift);
		}
		if (cook != null) {
			task.setCook(cook);
		}
		if (expectedTime != null) {
			task.setExpectedTime(expectedTime);
		}
		if (quantity != null) {
			task.setQuantity(quantity);
		}
		return task;
	}

	public Task removeTaskAssignment(Task task) {
		task.setShift(null);
		task.setCook(null);
		task.setExpectedTime(null);
		task.setQuantity(null);
		return task;
	}

	public Task taskCompleted(Task task) {
		task.setCompleted();
		return task;
	}

	public void getCurrentTaskStatuses() {
		// implementation
	}

	// Static method for persistence
	public static ObservableList<SummarySheet> getAllSummarySheets() {
		String query = "SELECT * FROM SummarySheets ORDER BY service_id,position asc";
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
			summarySheet.tasks = taskList.get(service);
			summarySheet.chef = chefList.get(service);
			summarySheets.add(summarySheet);
		}
		
		return summarySheets;
	}
	

	public static void saveSheet(SummarySheet s){
		Task.saveAllTasks(s.getTasks());
		int serviceId = s.getService().getId();

		// make batch insert into summarysheet
		String query = "INSERT INTO SummarySheets (service_id, task_id, chef_id, position) VALUES (?, ?, ?, ?)";
		PersistenceManager.executeBatchUpdate(query, s.getTasks().size(), new BatchUpdateHandler() {
			@Override
			public void handleBatchItem(PreparedStatement ps, int i) throws SQLException {
				ps.setInt(1, serviceId);
				ps.setInt(2, s.getTasks().get(i).getId());
				ps.setInt(3, s.getChef().getId());
				ps.setInt(4, i + 1);
			}
			@Override
			public void handleGeneratedIds(ResultSet rs, int i) throws SQLException {
				return;
			}
		});
	}

	public static void removeEntry(SummarySheet s,Task t){
		String query = "DELETE FROM SummarySheets WHERE task_id = " + t.getId();
		PersistenceManager.executeUpdate(query);
	}

	public static void insertTaskIntoSummarySheet(SummarySheet s, Task t, int position){
		String query = "INSERT INTO SummarySheets (service_id, task_id, chef_id, position) VALUES (?, ?, ?, ?)";
		PersistenceManager.executeBatchUpdate(query, 1, new BatchUpdateHandler() {
			@Override
			public void handleBatchItem(PreparedStatement ps, int i) throws SQLException {
				ps.setInt(1, s.getService().getId());
				ps.setInt(2, t.getId());
				ps.setInt(3, s.getChef().getId());
				ps.setInt(4, position + 1);
			}
			@Override
			public void handleGeneratedIds(ResultSet rs, int i) throws SQLException {
				return;
			}
		});
	}

	public static void saveTaskOrder(SummarySheet s){
		String query = "UPDATE SummarySheets SET position = ? WHERE task_id = ?";
		PersistenceManager.executeBatchUpdate(query, s.getTasks().size(), new BatchUpdateHandler() {
			@Override
			public void handleBatchItem(PreparedStatement ps, int i) throws SQLException {
				ps.setInt(1, i);
				ps.setInt(2, s.getTasks().get(i).getId());
			}
			@Override
			public void handleGeneratedIds(ResultSet rs, int i) throws SQLException {
				return;
			}
		});
	}
}
