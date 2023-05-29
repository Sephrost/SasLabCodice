package catering.businesslogic.summarySheet;

import java.util.List;
import java.util.Map;
import catering.businesslogic.kitchenJob.KitchenJob;
import catering.businesslogic.service.Service;
import catering.businesslogic.shiftManagement.Shift;
import catering.businesslogic.user.User;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class SummarySheet {
	private ServiceInfo service;
	private List<Task> task;
	// TODO: controllare menù da aggiungere

	public SummarySheet(Service service) {
		this.service = service;
		this.task = new ArrayList<Task>();
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
}
