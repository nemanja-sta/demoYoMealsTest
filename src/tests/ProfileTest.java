package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LocationPopupPage;
import pages.LoginPage;
import pages.NotificationSystemPage;
import pages.ProfilePage;

public class ProfileTest extends BasicTest {

	@Test
	public void editProfileTest() throws InterruptedException {
		this.driver.navigate().to(this.baseUrl + "/guest-user/login-form/");

		LocationPopupPage lpp = new LocationPopupPage(this.driver, this.wait, this.executor);
		LoginPage lp = new LoginPage(this.driver, this.wait, this.executor);
		NotificationSystemPage nsp = new NotificationSystemPage(this.driver, this.wait, this.executor);
		ProfilePage pp = new ProfilePage(this.driver, this.wait, this.executor);

		lpp.closeDialog();
		lp.login(this.email, this.password);

		Assert.assertTrue(nsp.notificationMessage().equals("Login Successfull"));

		this.driver.navigate().to(this.baseUrl + "/member/profile/");

		pp.updateProfile("John", "Newton", 456, 123987, 75024, "United Kingdom", "Bristol", "Avon");

		Assert.assertTrue(nsp.notificationMessage().equals("Setup Successful"));

		pp.logout();

		Assert.assertTrue(nsp.notificationMessage().equals("Logout Successfull!"));

	}
}
