package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotificationSystemPage extends BasicPage {

	public NotificationSystemPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor executor) {
		super(driver, wait, executor);
	}

	public WebElement getNotification() {
		return this.driver.findElement(By.xpath(
				"//*[contains(@class, 'alert--success') or contains(@class, 'alert--danger')][contains(@style,'display: block')]"));
	}

	public WebElement getNotificationDanger() {
		return this.driver.findElement(By.xpath("//*[contains(@class, 'alert--danger')]"));
	}

	public String notificationMessage() {
		return getNotification().getText();
	}

	public void toBeInvisible() {
		this.wait.until(ExpectedConditions.attributeContains(By.xpath("//*[contains(@class, 'system_message')]"),
				"style", "display: none;"));
	}
}
