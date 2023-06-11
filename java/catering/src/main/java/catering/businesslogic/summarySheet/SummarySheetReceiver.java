package catering.businesslogic.summarySheet;

import java.util.List;

import catering.businesslogic.kitchenJob.KitchenJob;
import catering.businesslogic.shiftManagement.Shift;
import catering.businesslogic.user.User;

public interface SummarySheetReceiver {


    void notifySummarySheetCreated(SummarySheet sheet);
    void notifySummarySheetSelected(SummarySheet sheet);
    void notifyKitchenJobAdded(SummarySheet s, Task t);
    void notifyKitchenJobRemoved(SummarySheet s, List<Task> tl);
    void notifyTaskOrderModified(SummarySheet s);
    void notifyTaskAssigned(Task t);
    void notifyTaskAssignmentRemoved(Task task, Shift s, User u);
    void notifyTaskCompleted(Task t);
}
