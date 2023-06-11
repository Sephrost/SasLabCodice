package catering.persistence;

import java.util.List;

import catering.businesslogic.kitchenJob.KitchenJob;
import catering.businesslogic.shiftManagement.Shift;
import catering.businesslogic.summarySheet.SummarySheet;
import catering.businesslogic.summarySheet.SummarySheetException;
import catering.businesslogic.summarySheet.SummarySheetReceiver;
import catering.businesslogic.summarySheet.Task;
import catering.businesslogic.user.User;

public class SummarySheetPersistence implements SummarySheetReceiver{

	@Override
	public void notifySummarySheetCreated(SummarySheet s) {
		SummarySheet.saveSheet(s);	
	}

	@Override
	public void notifySummarySheetSelected(SummarySheet s) {
		// TODO Auto-generated method stub
	}

	@Override
	public void notifyKitchenJobAdded(SummarySheet s, Task t) {
		Task.saveTask(t);
		SummarySheet.insertTaskIntoSummarySheet(s, t, s.getTasks().size() - 1);
	}

	@Override
	public void notifyKitchenJobRemoved(SummarySheet s, List<Task> tl) {
		for (Task t : tl) {
			Task.removeTask(t.getKitchenJob());
			SummarySheet.removeEntry(s, t);
		}
		
	}

	@Override
	public void notifyTaskOrderModified(SummarySheet s) {
		SummarySheet.saveTaskOrder(s);
	}

	@Override
	public void notifyTaskAssigned(Task t) {
		Task.updateTask(t);	
	}

	@Override
	public void notifyTaskCompleted(Task t) {
		Task.updateTask(t);
	}

	@Override
	public void notifyTaskAssignmentRemoved(Task task, Shift s, User u) {
		Task.removeTaskAssignment(task);
	}
	
}