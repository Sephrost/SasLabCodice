package catering.businesslogic.summarySheet;

import java.sql.ResultSet;
import java.sql.SQLException;

import catering.businesslogic.kitchenJob.KitchenJob;
import catering.businesslogic.shiftManagement.Shift;
import catering.businesslogic.user.User;
import catering.persistence.PersistenceManager;
import catering.persistence.ResultHandler;
import javafx.collections.ObservableList;

public class Task {
    private int id;
    private boolean completed;
    private String quantity;
    private String estimatedTime;
    private User cook;
    private KitchenJob kj;
    private Shift shift;

    public Task(KitchenJob kj) {
        this.kj = kj;
    }

    public KitchenJob getKitchenJob() {
        return kj;
    }

    public void setShift(Shift shift) {
        // implementation
    }

    public void setCook(User cook) {
        // implementation
    }

    public void setExpectedTime(String expectedTime) {
        // implementation
    }

    public void setQuantity(String quantity) {
        // implementation
    }

    public void setCompleted() {
        completed=true;
    }

    // TODO: fix this
    public static Task loadTask(int task_id) {
        String query = "SELECT * FROM Tasks WHERE id = ?";
        Task task = new Task();
         PersistenceManager.executeQuery(query, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                task.id = rs.getInt("id");
                task.completed = rs.getBoolean("completed");
                task.quantity = rs.getString("quantity");
                task.estimatedTime = rs.getString("estimated_time");
            }
        });
        return task;
    }
    
    // https://stackoverflow.com/questions/2265503/why-do-i-need-to-override-the-equals-and-hashcode-methods-in-java
	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		return prime * result + id ;
	}

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof Task))
            return false;
        Task task = (Task) obj;
        return task.id == this.id;
    }
    
}
