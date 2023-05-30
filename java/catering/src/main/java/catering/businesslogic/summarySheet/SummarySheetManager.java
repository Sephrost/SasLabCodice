package catering.businesslogic.summarySheet;

import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import catering.businesslogic.CatERing;
import catering.businesslogic.UseCaseLogicException;
import catering.businesslogic.event.*;
import catering.businesslogic.event.ServiceInfo.State;
import catering.businesslogic.kitchenJob.KitchenJob;
import catering.businesslogic.menu.Menu;
import catering.businesslogic.service.Service;
import catering.businesslogic.shiftManagement.Shift;
import catering.businesslogic.user.User;
import catering.persistence.PersistenceManager;
import catering.persistence.ResultHandler;
import javafx.event.Event;

public class SummarySheetManager {
	private SummarySheet currentSummarySheet;
	private ArrayList<SummarySheetReceiver> receivers;

	public SummarySheet createSummarySheet(EventInfo event, ServiceInfo service) throws UseCaseLogicException,EventException{
        User user = CatERing.getInstance().getUserManager().getCurrentUser();
        if(!user.isChef()){
            throw new UseCaseLogicException("User is not chef");
        }
        if(event.getAssignedChef() != user){
            throw new UseCaseLogicException("User is not assigned to the event");
        }
       
        for(SummarySheet s : SummarySheet.getAllSummarySheets()){
            if(s.getService().equals(service)){
                setCurrentSummarySheet(s);
                return s;
            }
        }

        SummarySheet s = new SummarySheet(service);
        this.setCurrentSummarySheet(s);
        this.notifySummarySheetCreated(s);

		return s;
    }


	public void addKitchenJob(KitchenJob kj) {
		// implementation
	}

	public void removeKitchenJob(KitchenJob kj) {
		// implementation
	}

	public void reorderTaskPosition(Task task, int position) {
		// implementation
	}

	public SummarySheet getCurrentBoard() {

	}

	public void chooseSummarySheet(Event e, Service ser) {
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

	public void setCurrentSummarySheet(SummarySheet s) {
		// implementation
	}

	public boolean checkSummarySheetExist() {
		// implementation
	}

	public void notifySummarySheetCreated(SummarySheet s) {
		// implementation
	}

	public void notifyKitchenJobRemoved(KitchenJob kj) {
		// implementation
	}

	public void notifyTaskOrderModified() {
		// implementation
	}

	public void notifyTaskAssigned(Task task) {
		// implementation
	}

	public void notifyTaskRemoved(Task task) {
		// implementation
	}

	public void notifyTaskCompleted(Task task) {
		// implementation
	}
}