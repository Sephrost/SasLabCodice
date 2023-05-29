package catering.businesslogic.summarySheet;

import java.util.ArrayList;
import java.util.List;

import catering.businesslogic.event.ServiceInfo;
import catering.businesslogic.kitchenJob.KitchenJob;
import catering.businesslogic.shiftManagement.Shift;
import catering.businesslogic.user.User;


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
}
