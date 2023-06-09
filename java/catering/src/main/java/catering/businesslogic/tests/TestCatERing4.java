package catering.businesslogic.tests;

import catering.businesslogic.CatERing;
import catering.businesslogic.user.User;
import catering.persistence.PersistenceManager;

public class TestCatERing4 {
	public static void main(String[] args) {
		if(!PersistenceManager.testDB()) {
			System.out.println("DB connection failed");
			return;
		}
	
		CatERing app = CatERing.getInstance();
		app.getUserManager().loginWithUsername("Lidia");// Chef have the h role in the db
		User user = app.getUserManager().getCurrentUser();
		System.out.println("Logged in as " + user.getUserName());

		// Try to get the shift board
		try {
			System.out.println("Trying to get the shift board...");
			app.getShiftManager().getCurrentBoard();
			System.out.println("Shift board correctly loaded");
		} catch (Exception e) {
			System.out.println("Error in getting the shift board");
			e.printStackTrace();
		}
	}
}
