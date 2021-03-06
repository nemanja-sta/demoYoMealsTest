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
		// wait for dialog to be closed and to load locations
		Thread.sleep(2000);
		lpp.setLocation("City Center - Albany");

		File file = new File("data/Data.xlsx").getCanonicalFile();
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("Meal Search Results");

		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			String newTabScript = "window.open();";
			executor.executeScript(newTabScript);
			String mainWindowHandles = driver.getWindowHandle();
			String newMealWindowHandles = "";

			String location = sheet.getRow(i).getCell(0).getStringCellValue();
			String url = sheet.getRow(i).getCell(1).getStringCellValue();
			double numberOfResults = sheet.getRow(i).getCell(2).getNumericCellValue();
			int numberOfResultsInt = (int) numberOfResults;

			ArrayList<String> wh = new ArrayList<String>(driver.getWindowHandles());
			wh.remove(mainWindowHandles);
			newMealWindowHandles = wh.get(0);

			driver.switchTo().window(newMealWindowHandles);
			driver.navigate().to(url);

			lpp.setLocation(location);

			Assert.assertEquals(srp.resultNumber(), numberOfResultsInt, "[ERROR] Meals Number Not Equal");

			for (int j = 3; j < 3 + numberOfResultsInt; j++) {
				String expectedMealName = srp.mealNames().get(j - 3);
				String actualMealName = sheet.getRow(i).getCell(j).getStringCellValue();
				sa.assertTrue(expectedMealName.contains(actualMealName), "[ERROR] Meals Does Not Present");
			}
		}

		sa.assertAll();
		wb.close();
		fis.close();
	}

}
