package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasicPage {

	public LoginPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor executor) {
		super(driver, wait, executor);
	}

	public WebElement getEmailInput() {
		return this.driver.findElement(By.xpath("//*[@id=\"frm_fat_id_frmLogin\"]/fieldset[1]/input"));
	}

	public WebElement getPasswordInput() {
		return this.driver.findElement(By.xpath("//*[@id=\"frm_fat_id_frmLogin\"]/fieldset[2]/input"));
	}

	public WebElement getLoginButton() {
		return this.driver.findElement(By.xpath("//*[@id=\"frm_fat_id_frmLogin\"]/fieldset[4]/input"));
	}

	public void login(String email, String password) {
		this.getEmailInput().sendKeys(email);
		this.getPasswordInput().sendKeys(password);
		this.getLoginButton().click();
	}
}
