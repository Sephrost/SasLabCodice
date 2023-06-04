package catering.businesslogic.summarySheet;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.time.Duration;
import java.util.List;

import catering.businesslogic.kitchenJob.KitchenJob;
import catering.businesslogic.shiftManagement.Shift;
import catering.businesslogic.user.User;
import catering.persistence.PersistenceManager;
import catering.persistence.BatchUpdateHandler;
import catering.persistence.ResultHandler;
import javafx.collections.ObservableList;

public class Task {
		private static int lastId;

    private int id;
    private boolean completed;
    private String quantity;
    private Duration estimatedTime;
    private User cook;
    private KitchenJob kj;
    private Shift shift;

	public Task() {}

	public Task(KitchenJob kj) {
			this.kj = kj;
	}

	protected int getId(){
		return id;
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
        String query = "SELECT * FROM Tasks WHERE id = " + task_id;
        Task task = new Task();
        PersistenceManager.executeQuery(query, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                task.id = rs.getInt("id");
                task.completed = rs.getBoolean("completed");
                task.quantity = rs.getString("quantity");
                task.kj = KitchenJob.loadKitchenJobById(rs.getInt("kitchenjob_id"));
                task.cook = User.loadUserById(rs.getInt("cook_id"));
                task.estimatedTime = Duration.ofMinutes(rs.getInt("estimated_time"));
				task.shift = Shift.loadShift(rs.getInt("shift_id"));
			}
        });
        return task;
    }

	// protected static void loadLastId() {
	// 	String query = "SELECT MAX(id) as id_max FROM Tasks";
	// 	PersistenceManager.executeQuery(query, new ResultHandler() {
	// 		@Override
	// 		public void handle(ResultSet rs) throws SQLException {
	// 			Task.lastId = rs.getInt("id_max");
	// 		}
	// 	});
	// }

	// private static int getNewId() {
	// 	return ++Task.lastId;
	// }

	public static void saveAllTasks(List<Task> task) {
		String query = "INSERT INTO Tasks ( completed, quantity, estimated_time, cook_id, shift_id, kitchenjob_id) VALUES (?, ?, ?, ?, ?, ?)";
		PersistenceManager.executeBatchUpdate(query, task.size(), new BatchUpdateHandler() {
			@Override
			public void handleBatchItem(PreparedStatement ps, int i) throws SQLException {
				ps.setBoolean(1, task.get(i).completed);
				if (task.get(i).quantity == null) {
					ps.setNull(2, java.sql.Types.VARCHAR);
				} else {
					ps.setString(2, task.get(i).quantity);
				}
				ps.setString(2, task.get(i).quantity);
				if (task.get(i).estimatedTime == null) {
					ps.setNull(3, java.sql.Types.INTEGER);
				} else {
					ps.setLong(3, task.get(i).estimatedTime.toMinutes());
				}
				if (task.get(i).cook == null) {
					ps.setNull(4, java.sql.Types.INTEGER);
				} else {
					ps.setInt(4, task.get(i).cook.getId());
				}
				if (task.get(i).shift == null) {
					ps.setNull(5, java.sql.Types.INTEGER);
				} else {
					ps.setInt(5, task.get(i).shift.getId());
				}
				
				ps.setInt(6, task.get(i).kj.getId());
			}

			public void handleGeneratedIds(ResultSet rs, int i) throws SQLException {
				task.get(i).id = rs.getInt(1);
				System.out.println("Generated id: " + task.get(i).id);
			}
		});
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
