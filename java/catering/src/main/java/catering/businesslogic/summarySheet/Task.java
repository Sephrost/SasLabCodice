package catering.businesslogic.summarySheet;

import catering.businesslogic.kitchenJob.KitchenJob;
import catering.businesslogic.shiftManagement.Shift;
import catering.businesslogic.user.User;

public class Task {
    private int id;
    private boolean completed;
    private String quantity;
    private String estimatedTime;
    private User cook;
    private KitchenJob kj;
    private Shift shift;

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

}
