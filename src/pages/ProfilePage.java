package pages;

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
		return this.driver.findElement(By.xpath("//*[@id=\"profileInfoFrm\"]/div[1]/div[1]/fieldset/input"));
	}

	public WebElement getLNameInput() {
		return this.driver.findElement(By.xpath("//*[@id=\"profileInfoFrm\"]/div[1]/div[2]/fieldset/input"));
	}

	public WebElement getAddressInput() {
		return this.driver.findElement(By.xpath("//*[@id=\"profileInfoFrm\"]/div[2]/div[2]/fieldset/input"));
	}

	public WebElement getPhoneInput() {
		return this.driver.findElement(By.xpath("//*[@id=\"profileInfoFrm\"]/div[3]/div[1]/fieldset/input"));
	}

	public WebElement getZipInput() {
		return this.driver.findElement(By.xpath("//*[@id=\"profileInfoFrm\"]/div[3]/div[2]/fieldset/input"));
	}

	public void countrySelect(String country) {
		WebElement gcs = this.driver.findElement(
				By.xpath("/html/body/div[6]/div/div/div/div[2]/div/div/div[4]/form/div[4]/div[1]/fieldset/select"));
		Select select = new Select(gcs);
		select.selectByVisibleText(country);
	}

	public void stateSelect(String state) {
		WebElement gss = this.driver.findElement(
				By.xpath("/html/body/div[6]/div/div/div/div[2]/div/div/div[4]/form/div[4]/div[2]/fieldset/select"));
		Select select = new Select(gss);
		select.selectByVisibleText(state);
	}

	public void citySelect(String city) {
		WebElement gcitys = this.driver.findElement(
				By.xpath("/html/body/div[6]/div/div/div/div[2]/div/div/div[4]/form/div[4]/div[3]/fieldset/select"));
		Select select = new Select(gcitys);
		select.selectByVisibleText(city);
	}
	
	public WebElement getPhotoInput() {
		return this.driver.findElement(By.xpath("//*[@id=\"profileInfo\"]/div/div[1]/img"));
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

//WebElement uploadElement = driver.findElement(By.xpath("//*[@id=\"inputImage\"]"));
//
//uploadElement.sendKeys("D:\\Slike\\slika.jpg");

//●	get metode za sve potrebne elemente
//○	sve elemente za izmenu osnovnih informacija kao i 
//○	sve elemente potrebne za rad sa profilnom slikom
//○	da bi se na stranici pojavio element input type="file" potrebno je da se prvo izvrši JavaScript kod koji vrši akciju klik na Upload dugme 
//○	Skripta: arguments[0].click();
//●	metodu koja otprema profilnu sliku - kao parametar se prosleđuje putanja do fajla, tj. u ovom slučaju do slike
//●	metodu koja briše profilnu sliku
//○	klikom na Remove dugme 
//○	Preporuka: izvršite JavaScript kod arguments[0].click(); nad tim dugmetom
//●	metodu koja menja sve osnovne informacije korisnika - kao parametri se prosleđuju sve potrebne informacije
