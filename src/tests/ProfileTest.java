package tests;

import org.testng.annotations.Test;

import pages.LocationPopupPage;

public class ProfileTest extends BasicTest {

	@Test
	public void editProfileTest() throws InterruptedException {
		this.driver.navigate().to(this.baseUrl + "/guest-user/login-form/");

		LocationPopupPage lpp = new LocationPopupPage(this.driver, this.wait, this.executor);

		lpp.closeDialog();

	}
}

//U okviru edit profile testa potrebno je izvršiti sledeće korake:
//●	učitajte stranicu http://demo.yo-meals.com/guest-user/login-form
//●	ugasite lokacioni iskačući dijalog
//●	prijavite se na aplikaciju preko demo naloga
//●	verifikujte da je prikazana poruka sa tekstom "Login Successfull"
//●	učitajte stranicu http://demo.yo-meals.com/member/profile
//●	zamenite sve osnovne informacije korisnika
//●	verifikujte da je prikazana poruka sa tekstom "Setup Successful"
//●	odjavite se sa sajta
//●	verifikujte da je prikazana poruka sa tekstom "Logout Successfull!"
