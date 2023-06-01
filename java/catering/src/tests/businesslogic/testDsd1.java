import catering.businesslogic.CatERing;
import catering.businesslogic.summarySheet.SummarySheetManager;

import static org.junit.*;

/**
 * testDsd1
 */
public class testDsd1 {

    // Test SummarySheet.getAllSummarySheets() to thest if it returns an empty list when there are no summary sheets
    @test
    public void testGetAllSummarySheets() {
        SummarySheetManager summarySheetMgr = app.getSummarySheetManager();
        List<SummarySheet> summarySheets = summarySheetMgr.getAllSummarySheets();
        assertTrue(summarySheets.isEmpty());
    }
    

    

    // public static void main(String[] args) {
    //     app = CatERing.getInstance();
    //     app.getUserManager().fakeLogin("admin");

    //     SummarySheetManager summarySheetMgr = app.getSummarySheetManager();
        
    // }
}