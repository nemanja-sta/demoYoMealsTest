package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
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

		Assert.assertTrue(nsp.notificationMessage().contains("The Following Errors Occurred:"));

		nsp.toBeInvisible();

		lpp.setLocation("City Center - Albany");
		Thread.sleep(2000);

		mp.addMeal(2);

		Assert.assertTrue(nsp.notificationMessage().contains("Meal Added To Cart"));

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

		Assert.assertTrue(nsp.notificationMessage().contains("Please login first!"));

		this.driver.navigate().to(this.baseUrl + "/member/profile/");
		lp.login(this.email, this.password);

		this.driver.navigate().to(this.baseUrl + "meal/lobster-shrimp-chicken-quesadilla-combo/");

		mp.favouriteMeal();

		Thread.sleep(2000);

		Assert.assertTrue(nsp.notificationMessage().contains("Product has been added to your favorites."));

	}
}
