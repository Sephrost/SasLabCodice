package catering.businesslogic.summarySheet;

import java.util.List;

import catering.businesslogic.kitchenJob.KitchenJob;

public interface SummarySheetReceiver {


    void notifySummarySheetCreated(SummarySheet sheet);
    void notifySummarySheetSelected(SummarySheet sheet);
    void notifyKitchenJobAdded(SummarySheet s, Task t);
    void notifyKitchenJobRemoved(SummarySheet s, List<Task> tl);
    void notifyTaskOrderModified();

}
