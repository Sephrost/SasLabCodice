package catering.businesslogic.summarySheet;

import java.util.List;

import catering.businesslogic.kitchenJob.KitchenJob;
import catering.businesslogic.service.Service;
import catering.businesslogic.shiftManagement.Shift;
import catering.businesslogic.user.User;

public class SummarySheet {
    private Service service;
    private List<Task> task;

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
        // implementation
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
