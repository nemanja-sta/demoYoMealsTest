package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LocationPopupPage extends BasicPage {

	public LocationPopupPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor executor) {
		super(driver, wait, executor);
	}

	public WebElement closeX() {
		return this.driver.findElement(By.xpath("//*[@id=\"location-popup\"]/div/div/div/div/a"));
	}

	public void closeDialog() {
		this.closeX().click();
	}

	public WebElement selectLocation() {
		return this.driver.findElement(By.xpath("//*[@class='location-selector']/a"));
	}

	public WebElement getKeyword() {
		return this.driver.findElement(By.xpath("//*[@id='locality_keyword']"));
	}

	public WebElement getLocationItem(String locationName) {
		return this.driver.findElement(By.xpath("//li/a[contains(text(), '" + locationName + "')]/.."));
	}

	public WebElement getLocationInput() {
		return this.driver.findElement(By.xpath("//*[@id='location_id']"));
	}

	public WebElement getSubmit() {
		return this.driver.findElement(By.xpath("//*[@name='btn_submit']"));
	}

	public void openLocationDialog() {
		this.selectLocation().click();
	}

	public void setLocation(String location) throws InterruptedException {
		this.openLocationDialog();
		this.getKeyword().click();

		String argumentValue = getLocationItem(location).getAttribute("data-value");
		String enterLocation = "arguments[0].value=arguments[1]";
		executor.executeScript(enterLocation, this.getLocationInput(), argumentValue);
		executor.executeScript("arguments[0].click();", this.getSubmit());
	}

}
