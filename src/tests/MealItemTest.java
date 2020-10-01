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

import pages.CartSummaryPage;
import pages.LocationPopupPage;
import pages.LoginPage;
import pages.MealPage;
import pages.NotificationSystemPage;

public class MealItemTest extends BasicTest {

	@Test(priority = 0)
	public void addMealToCartTest() throws InterruptedException {
		LocationPopupPage lpp = new LocationPopupPage(this.driver, this.wait, this.executor);
		MealPage mp = new MealPage(this.driver, this.wait, this.executor);
		NotificationSystemPage nsp = new NotificationSystemPage(this.driver, this.wait, this.executor);

		this.driver.navigate().to(this.baseUrl + "meal/lobster-shrimp-chicken-quesadilla-combo/");

		lpp.closeDialog();

		mp.addMeal(3);

		Assert.assertTrue(nsp.notificationMessage().contains("The Following Errors Occurred:"), "[ERROR] No Location");
		Assert.assertTrue(nsp.notificationMessage().contains("Please Select Location"), "[ERROR] No Location");

		nsp.toBeInvisible();

		lpp.setLocation("City Center - Albany");
		Thread.sleep(2000);

		mp.addMeal(2);

		Assert.assertTrue(nsp.notificationMessage().contains("Meal Added To Cart"), "[ERROR] Meal Not Added");

	}

	@Test(priority = 5)
	public void addMealToFavoriteTest() throws InterruptedException {
		LocationPopupPage lpp = new LocationPopupPage(this.driver, this.wait, this.executor);
		MealPage mp = new MealPage(this.driver, this.wait, this.executor);
		NotificationSystemPage nsp = new NotificationSystemPage(this.driver, this.wait, this.executor);
		LoginPage lp = new LoginPage(this.driver, this.wait, this.executor);

		this.driver.navigate().to(this.baseUrl + "meal/lobster-shrimp-chicken-quesadilla-combo/");

		lpp.closeDialog();

		mp.favouriteMeal();

		Assert.assertTrue(nsp.notificationMessage().contains("Please login first!"), "[ERROR] Logged In");

		this.driver.navigate().to(this.baseUrl + "/member/profile/");
		lp.login(this.email, this.password);

		this.driver.navigate().to(this.baseUrl + "meal/lobster-shrimp-chicken-quesadilla-combo/");

		mp.favouriteMeal();

		Thread.sleep(2000);

		Assert.assertTrue(nsp.notificationMessage().contains("Product has been added to your favorites."),
				"[ERROR] No Favourite");

	}

	@Test(priority = 10)
	public void clearCartTest() throws InterruptedException, IOException {
		LocationPopupPage lpp = new LocationPopupPage(this.driver, this.wait, this.executor);
		MealPage mp = new MealPage(this.driver, this.wait, this.executor);
		CartSummaryPage csp = new CartSummaryPage(this.driver, this.wait, this.executor);
		NotificationSystemPage nsp = new NotificationSystemPage(this.driver, this.wait, this.executor);
		SoftAssert sa = new SoftAssert();

		this.driver.navigate().to(this.baseUrl + "meals/");
		lpp.closeDialog();
		Thread.sleep(2000);
		lpp.setLocation("City Center - Albany");

		File file = new File("data/Data.xlsx").getCanonicalFile();
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet1 = wb.getSheet("Meals");

		for (int i = 1; i <= sheet1.getLastRowNum(); i++) {
			String newTabScript = "window.open();";
			executor.executeScript(newTabScript);
			String mainWindowHandles = driver.getWindowHandle();
			String newMealWindowHandles = "";

			String url = sheet1.getRow(i).getCell(0).getStringCellValue();
			double quantityDouble = sheet1.getRow(i).getCell(1).getNumericCellValue();

			int quantityInt = (int) quantityDouble;

			ArrayList<String> wh = new ArrayList<String>(driver.getWindowHandles());
			wh.remove(mainWindowHandles);
			newMealWindowHandles = wh.get(0);

			driver.switchTo().window(newMealWindowHandles);
			driver.navigate().to(url);

			mp.addMeal(quantityInt);
			Thread.sleep(1000);
			sa.assertTrue(nsp.notificationMessage().contains("Meal Added To Cart"), "[ERROR] Meal Not Added");

			nsp.toBeInvisible();
		}

		sa.assertAll();
		wb.close();
		fis.close();
		csp.clearAll();

		Assert.assertTrue(nsp.notificationMessage().contains("All meals removed from Cart successfully"),
				"[ERROR] Meals Not Removed");
	}
}
