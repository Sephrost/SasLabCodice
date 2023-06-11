package catering.businesslogic.summarySheet;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import catering.businesslogic.CatERing;
import catering.businesslogic.UseCaseLogicException;
import catering.businesslogic.event.*;
import catering.businesslogic.kitchenJob.KitchenJob;
import catering.businesslogic.shiftManagement.Shift;
import catering.businesslogic.shiftManagement.ShiftBoard;
import catering.businesslogic.user.User;

public class SummarySheetManager {
	private SummarySheet currentSummarySheet;
	private ArrayList<SummarySheetReceiver> receivers;

	public SummarySheetManager() {
		this.receivers = new ArrayList<>();
	}

	public SummarySheet createSummarySheet(EventInfo event, ServiceInfo service)
			throws UseCaseLogicException, EventException {
		User user = CatERing.getInstance().getUserManager().getCurrentUser();
		if (!user.isChef()) {
			throw new UseCaseLogicException("User is not chef");
		}
		if (event.getAssignedChef() != user) {
			throw new UseCaseLogicException("User is not assigned to the event");
		}

		for (SummarySheet s : SummarySheet.getAllSummarySheets()) {
			if (s.getService().equals(service)) {
				setCurrentSummarySheet(s);
				return s;
			}
		}

		SummarySheet s = new SummarySheet(service);

		this.setCurrentSummarySheet(s);
		this.notifySummarySheetCreated(s);

		return s;
	}

	public SummarySheet getCurrentSummarySheet() {
		return this.currentSummarySheet;
	}

	public Task addKitchenJob(KitchenJob kj) throws UseCaseLogicException {
		if (this.currentSummarySheet == null) {
			throw new UseCaseLogicException("No summary sheet in use");
		}
		Task t = this.currentSummarySheet.addKitchenJob(kj);
		this.notifyKitchenJobAdded(this.currentSummarySheet, t);
		return t;
	}

	public void removeKitchenJob(KitchenJob kj) throws UseCaseLogicException {
		if (this.currentSummarySheet == null) {
			throw new UseCaseLogicException("No summary sheet in use");
		}
		List<Task> tl = this.currentSummarySheet.removeKitchenJob(kj);
		this.notifyKitchenJobRemoved(this.currentSummarySheet, tl);
	}

	public void reorderTask(Task task, int position) throws UseCaseLogicException, SummarySheetException {
		if (this.currentSummarySheet == null) {
			throw new UseCaseLogicException("No summary sheet in use");
		}
		if (position < 0 || position > this.currentSummarySheet.getTasks().size()) {
			throw new UseCaseLogicException("Invalid position");
		}
		if (currentSummarySheet.hasTask(task) == false) {
			throw new UseCaseLogicException("Invalid task");
		}

		this.currentSummarySheet.swapTaskPositions(task, position);
		notifyTaskOrderModified(this.currentSummarySheet);
	}

	// TODO: to avoid high coupling with shift management, we should 
	// remove this
	// public ShiftBoard getCurrentBoard() {
	// 	return CatERing.getInstance().getShiftManager().getCurrentBoard();
	// }

	public SummarySheet chooseSummarySheet(EventInfo e, ServiceInfo ser)
			throws UseCaseLogicException, SummarySheetException {
		User user = CatERing.getInstance().getUserManager().getCurrentUser();
		if (!user.isChef()) {
			throw new UseCaseLogicException("User is not chef");
		}
		if (e.getOrganizer() != user) {
			throw new SummarySheetException("User is not assigned to the event");
		}
		if (!existsSummarySheet(ser)) {
			throw new SummarySheetException("SummarySheet does not exist for this service an event");
		}
		SummarySheet r = null;
		for (SummarySheet s : SummarySheet.getAllSummarySheets()) {
			if (s.getService().equals(ser)) {
				setCurrentSummarySheet(s);
				r = s;
			}
		}
		notifySummarySheetSelected(r); // TODO: this make no sense, remove it
		return r;
	}

	public Boolean existsSummarySheet(ServiceInfo ser) {
		for (SummarySheet s : SummarySheet.getAllSummarySheets()) {
			if (s.getService().equals(ser)) {
				return true;
			}
		}
		return false;
	}

	public Task assignTask(Task task, Optional<Shift> shift, Optional<User> cook, Optional<Duration> expectedTime, Optional<String> quantity) throws UseCaseLogicException {
		if (this.currentSummarySheet == null) {
			throw new UseCaseLogicException("No summary sheet in use");
		}
		if (shift.isPresent() && shift.get().getDate().before(Calendar.getInstance().getTime())) {
			throw new UseCaseLogicException("Cannot assign past shift");
		}
		if (shift.isPresent() && shift.get().getDate().after(this.currentSummarySheet.getService().getDate())) {
			throw new UseCaseLogicException("Date of shift is after the date of the service");
		}
		if (!this.currentSummarySheet.hasTask(task)) {
			throw new UseCaseLogicException("Task is not in the summary sheet, please add it first");
		}
		if (cook.isPresent() && !cook.get().isCook()) {
			throw new UseCaseLogicException("User is not a cook");
		}
		if (cook.isPresent() && shift.isPresent() && !ShiftBoard.getInstance().isAvailable(cook.get(), shift.get())) {
			throw new UseCaseLogicException("User is not available for the shift or already assigned");
		}

		Task t = currentSummarySheet.assignTask(task, shift.orElse(null), cook.orElse(null), expectedTime.orElse(null), quantity.orElse(null));
		notifyTaskAssigned(task);
		return t;
	}
	
	public Task removeTaskAssignment(Task task) throws UseCaseLogicException {
		if (this.currentSummarySheet == null) {
			throw new UseCaseLogicException("No summary sheet in use");
		}
		if (!this.currentSummarySheet.hasTask(task)) {
			throw new UseCaseLogicException("Task is not in the summary sheet, please add it first");
		}
		if(task.isCompleted()){
			throw new UseCaseLogicException("Task is already completed");
		}
		Shift s = task.getShift();
		User u = task.getCook();
		Task t = this.currentSummarySheet.removeTaskAssignment(task);
		notifyTaskAssignmentRemoved(t,s,u);
		return t;
	}

	public Task taskCompleted(Task task) {
		Task t = this.currentSummarySheet.taskCompleted(task);
		notifyTaskCompleted(t);
		return t;
	}

	public void getCurrentTaskStatuses() {
		// implementation
	}

	public void setCurrentSummarySheet(SummarySheet s) {
		this.currentSummarySheet = s;
	}

	public boolean checkSummarySheetExist() {
		return this.currentSummarySheet != null;
	}

	//
	// Receiver methods
	//

	public void addReceiver(SummarySheetReceiver r) {
		receivers.add(r);
	}

	public void removeReceiver(SummarySheetReceiver r) {
		receivers.remove(r);
	}

	public void notifySummarySheetCreated(SummarySheet s) {
		for (SummarySheetReceiver r : receivers) {
			r.notifySummarySheetCreated(s);
		}
	}

	public void notifySummarySheetSelected(SummarySheet s) {
		for (SummarySheetReceiver r : receivers) {
			r.notifySummarySheetSelected(s);
		}
	}

	public void notifyKitchenJobAdded(SummarySheet s, Task t) {
		for (SummarySheetReceiver r : receivers) {
			r.notifyKitchenJobAdded(s,t);
		}
	}

	public void notifyKitchenJobRemoved(SummarySheet s, List<Task> tl) {
		for (SummarySheetReceiver r : receivers) {
			r.notifyKitchenJobRemoved(s,tl);
		}
	}

	public void notifyTaskOrderModified(SummarySheet s) {
		for (SummarySheetReceiver r : receivers) {
			r.notifyTaskOrderModified(s);
		}
	}

	public void notifyTaskAssigned(Task task) {
		for (SummarySheetReceiver r : receivers) {
			r.notifyTaskAssigned(task);
		}
	}

	public void notifyTaskAssignmentRemoved(Task task, Shift s, User u) {
		for (SummarySheetReceiver r : receivers) {
			r.notifyTaskAssignmentRemoved(task,s,u);
		}
	}

	public void notifyTaskCompleted(Task task) {
		for (SummarySheetReceiver r : receivers) {
			r.notifyTaskCompleted(task);
		}
	}
}