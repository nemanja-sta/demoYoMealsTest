package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MealPage extends BasicPage {

	public MealPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor executor) {
		super(driver, wait, executor);
	}

	public WebElement qtyInput() {
		return this.driver
				.findElement(By.xpath("//*[@id=\"body\"]/section[1]/div/div/div[2]/div/div[3]/div[1]/ul/li[3]/input"));
	}

	public WebElement addCartButton() {
		return this.driver.findElement(By.xpath("//*[@id=\"body\"]/section[1]/div/div/div[2]/div/div[3]/div[2]/a"));
	}

	public void addMeal(int quantity) {
		String s = String.valueOf(quantity);
		// this.qtyInput().clear();
		this.qtyInput().sendKeys(s);
		this.addCartButton().click();
	}

	public void favouriteMeal() {
		WebElement btn = this.driver.findElement(By.xpath("//*[@id=\"item_119\"]"));
		btn.click();
	}
}
