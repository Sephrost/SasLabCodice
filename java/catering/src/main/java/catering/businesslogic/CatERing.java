package catering.businesslogic;

import catering.businesslogic.event.EventManager;
import catering.businesslogic.menu.MenuManager;
import catering.businesslogic.kitchenJob.*;
import catering.businesslogic.summarySheet.SummarySheetManager;
import catering.businesslogic.user.UserManager;
import catering.persistence.MenuPersistence;

public class CatERing {
	private static CatERing singleInstance;

	public static CatERing getInstance() {
		if (singleInstance == null) {
			singleInstance = new CatERing();
		}
		return singleInstance;
	}

	private MenuManager menuMgr;
	private KitchenJobManager kitchenJobMgr;
	private UserManager userMgr;
	private EventManager eventMgr;
	private SummarySheetManager summarySheetMgr;

	private MenuPersistence menuPersistence;

	private CatERing() {
		menuMgr = new MenuManager();
		kitchenJobMgr = new KitchenJobManager();
		userMgr = new UserManager();
		eventMgr = new EventManager();
		menuPersistence = new MenuPersistence();
		summarySheetMgr = new SummarySheetManager();
		menuMgr.addEventReceiver(menuPersistence);

	}

	public MenuManager getMenuManager() {
		return menuMgr;
	}

	public KitchenJobManager getKitchenJobManager() {
		return kitchenJobMgr;
	}

	public UserManager getUserManager() {
		return userMgr;
	}

	public EventManager getEventManager() {
		return eventMgr;
	}

}
