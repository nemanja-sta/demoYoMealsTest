package pages;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage extends BasicPage {

	public ProfilePage(WebDriver driver, WebDriverWait wait, JavascriptExecutor executor) {
		super(driver, wait, executor);
	}

	public WebElement getFNameInput() {
		return this.driver.findElement(By.xpath("//*[@name='user_first_name']"));
	}

	public WebElement getLNameInput() {
		return this.driver.findElement(By.xpath("//*[@name='user_last_name']"));
	}

	public WebElement getAddressInput() {
		return this.driver.findElement(By.xpath("//*[@name='user_address']"));
	}

	public WebElement getPhoneInput() {
		return this.driver.findElement(By.xpath("//*[@name='user_phone']"));
	}

	public WebElement getZipInput() {
		return this.driver.findElement(By.xpath("//*[@name='user_zip']"));
	}

	public void countrySelect(String country) {
		WebElement gcs = this.driver.findElement(By.xpath("//*[@id='user_country_id']"));
		Select select = new Select(gcs);
		select.selectByVisibleText(country);
	}

	public void stateSelect(String state) {
		WebElement gss = this.driver.findElement(By.xpath("//*[@id='user_state_id']"));
		Select select = new Select(gss);
		select.selectByVisibleText(state);
	}

	public void citySelect(String city) {
		WebElement gcitys = this.driver.findElement(By.xpath("//*[@id='user_city']"));
		Select select = new Select(gcitys);
		select.selectByVisibleText(city);
	}

	public void uploadPhoto() throws IOException {
		WebElement btn = this.driver.findElement(By.className("upload"));

		JavascriptExecutor js = (JavascriptExecutor) this.driver;
		js.executeScript("arguments[0].click();", btn);

		String imgPath = new File("images/slika.jpg").getCanonicalPath();
		this.driver.findElement(By.xpath("//input[@type='file']")).sendKeys(imgPath);
	}

	public void removePhoto() {
		WebElement btn = this.driver.findElement(By.className("remove"));

		JavascriptExecutor js = (JavascriptExecutor) this.driver;
		js.executeScript("arguments[0].click();", btn);
	}

	public WebElement getSaveButton() {
		return this.driver.findElement(By.xpath("//*[@id=\"profileInfoFrm\"]/div[5]/div/fieldset/input"));
	}

	public void updateProfile(String fName, String lName, int address, int phone, int zip, String country, String state,
			String city) throws InterruptedException {

		this.getFNameInput().clear();
		this.getLNameInput().clear();
		this.getAddressInput().clear();
		this.getPhoneInput().clear();
		this.getZipInput().clear();

		this.getFNameInput().sendKeys(fName);
		this.getLNameInput().sendKeys(lName);
		String addressS = String.valueOf(address);
		this.getAddressInput().sendKeys(addressS);
		String phoneS = String.valueOf(phone);
		this.getPhoneInput().sendKeys(phoneS);
		String zipS = String.valueOf(zip);
		this.getZipInput().sendKeys(zipS);

		this.countrySelect(country);
		Thread.sleep(2000);
		this.stateSelect(state);
		Thread.sleep(2000);
		this.citySelect(city);

		this.getSaveButton().click();
	}
}
