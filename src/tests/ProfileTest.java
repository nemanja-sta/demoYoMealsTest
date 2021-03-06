package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.AuthPage;
import pages.LocationPopupPage;
import pages.LoginPage;
import pages.NotificationSystemPage;
import pages.ProfilePage;

public class ProfileTest extends BasicTest {

	@Test(priority = 0)
	public void editProfileTest() throws InterruptedException, IOException {
		LocationPopupPage lpp = new LocationPopupPage(this.driver, this.wait, this.executor);
		LoginPage lp = new LoginPage(this.driver, this.wait, this.executor);
		NotificationSystemPage nsp = new NotificationSystemPage(this.driver, this.wait, this.executor);
		ProfilePage pp = new ProfilePage(this.driver, this.wait, this.executor);
		AuthPage ap = new AuthPage(this.driver, this.wait, this.executor);

		this.driver.navigate().to(this.baseUrl + "/guest-user/login-form/");

		lpp.closeDialog();
		lp.login(this.email, this.password);

		Assert.assertTrue(nsp.notificationMessage().equals("Login Successfull"));

		this.driver.navigate().to(this.baseUrl + "/member/profile/");

		pp.updateProfile("John", "Newton", 456, 123987, 75024, "United Kingdom", "Bristol", "Avon");

		Assert.assertTrue(nsp.notificationMessage().equals("Setup Successful"), "[ERROR] Not Saved");

		ap.logout();

		Assert.assertTrue(nsp.notificationMessage().equals("Logout Successfull!"), "[ERROR] Unexpected logout message");

	}

	@Test(priority = 5)
	public void changeProfileImageTest() throws InterruptedException, IOException {
		LocationPopupPage lpp = new LocationPopupPage(this.driver, this.wait, this.executor);
		LoginPage lp = new LoginPage(this.driver, this.wait, this.executor);
		NotificationSystemPage nsp = new NotificationSystemPage(this.driver, this.wait, this.executor);
		AuthPage ap = new AuthPage(this.driver, this.wait, this.executor);
		ProfilePage pp = new ProfilePage(this.driver, this.wait, this.executor);

		this.driver.navigate().to(this.baseUrl + "/guest-user/login-form");

		lpp.closeDialog();

		lp.login(this.email, this.password);

		Assert.assertTrue(nsp.notificationMessage().equals("Login Successfull"), "[ERROR] Not LoggedIn");

		this.driver.navigate().to(this.baseUrl + "/member/profile/");

		pp.uploadPhoto("images/slika.jpg");

		Thread.sleep(2000);

		Assert.assertTrue(nsp.notificationMessage().equals("Profile Image Uploaded Successfully"), "[ERROR] No Photo");

		pp.removePhoto();

		Assert.assertTrue(nsp.notificationMessage().equals("Profile Image Deleted Successfully"),
				"[ERROR] Photo Not Deleted");

		nsp.toBeInvisible();

		ap.logout();

		Assert.assertTrue(nsp.notificationMessage().equals("Logout Successfull!"), "[ERROR] Not Logged Out");
	}
}
