package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LocationPopupPage;
import pages.SearchResultPage;

public class SearchTest extends BasicTest {

	@Test(priority = 0)
	public void searchResultsTest() throws InterruptedException, IOException {
		SearchResultPage srp = new SearchResultPage(this.driver, this.wait, this.executor);
		LocationPopupPage lpp = new LocationPopupPage(this.driver, this.wait, this.executor);
		SoftAssert sa = new SoftAssert();

		this.driver.navigate().to(this.baseUrl + "meals/");
		lpp.closeDialog();
		Thread.sleep(2000);
		lpp.setLocation("City Center - Albany");

		File file = new File("data/Data.xlsx").getCanonicalFile();
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet1 = wb.getSheet("Meal Search Results");

		for (int i = 1; i <= sheet1.getLastRowNum(); i++) {
			String newTabScript = "window.open();";
			executor.executeScript(newTabScript);
			String mainWindowHandles = driver.getWindowHandle();
			String newMealWindowHandles = "";

			String location = sheet1.getRow(i).getCell(0).getStringCellValue();
			String url = sheet1.getRow(i).getCell(1).getStringCellValue();
			double numberOfResults = sheet1.getRow(i).getCell(2).getNumericCellValue();
			int numberOfResultsInt = (int) numberOfResults;

			ArrayList<String> wh = new ArrayList<String>(driver.getWindowHandles());
			wh.remove(mainWindowHandles);
			newMealWindowHandles = wh.get(0);

			driver.switchTo().window(newMealWindowHandles);
			driver.navigate().to(url);

			Thread.sleep(2000);

			lpp.setLocation(location);

			Thread.sleep(2000);

			Assert.assertEquals(srp.resultNumber(), numberOfResultsInt, "[ERROR] Meals Number Not Equal");

			for (int j = 3; j < 3 + numberOfResultsInt; j++) {
				sa.assertTrue(srp.mealNames().get(j - 3).contains(sheet1.getRow(i).getCell(j).getStringCellValue()),
						"[ERROR] Meals Does Not Present");
			}
		}

		sa.assertAll();
		wb.close();
		fis.close();
	}

}
