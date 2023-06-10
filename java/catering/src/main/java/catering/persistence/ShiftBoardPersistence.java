package catering.persistence;
import java.util.List;

import catering.businesslogic.shiftManagement.ShiftBoard;
import catering.businesslogic.summarySheet.SummarySheet;
import catering.businesslogic.summarySheet.SummarySheetReceiver;
import catering.businesslogic.summarySheet.Task;

public class ShiftBoardPersistence implements SummarySheetReceiver {


    @Override
    public void notifySummarySheetCreated(SummarySheet sheet) {}

    @Override
    public void notifySummarySheetSelected(SummarySheet sheet) {}

    @Override
    public void notifyKitchenJobAdded(SummarySheet s, Task t) {}

    @Override
    public void notifyKitchenJobRemoved(SummarySheet s, List<Task> tl) {}

    @Override
    public void notifyTaskOrderModified(SummarySheet s) {}

    @Override
    public void notifyTaskAssigned(Task t) {
        ShiftBoard.setUnavailable(t);
    }

    // @Override
    // public void notifyTaskRemoved(Task t) {
    //     // implementation goes here
    // }

    @Override
    public void notifyTaskCompleted(Task t) {}
}
