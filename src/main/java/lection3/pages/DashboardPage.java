package lection3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage {
    private EventFiringWebDriver driver;
    private By logoutImage = By.cssSelector("span.employee_avatar_small");
    private By ordersTab = By.id("subtab-AdminCatalog");
    private By addCategory = By.cssSelector("a#page-header-desc-category-new_category");
    private By categoryNameField = By.xpath("//input[@id='name_1']");
    private By submitButton = By.id("category_form_submit_btn");
    private By filterNameField = By.xpath("//input[@name='categoryFilter_name']");
    private By submitFilterButton = By.xpath("//button[@name='submitFilter']");

    public DashboardPage(EventFiringWebDriver driver){
        this.driver = driver;
    }

    public void selectOrdersItem(){
        waitLogo();

        WebElement orderTabElement = driver.findElement(ordersTab);
        Actions actions = new Actions(driver);
        actions.moveToElement(orderTabElement).build().perform();

        orderTabElement.findElements(By.cssSelector("li")).get(1).click();
    }

    /**
     * Adds new category in Admin Panel.
     * @param categoryName
     */
    public void addNewCategory(String categoryName){
        waitLogo();

        WebElement addCategoryElement = driver.findElement(addCategory);
        addCategoryElement.click();

        (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(submitButton));

        WebElement categoryNameFieldElement = driver.findElement(categoryNameField);
        categoryNameFieldElement.sendKeys(categoryName);

        WebElement submitButtonElement = driver.findElement(submitButton);
        submitButtonElement.click();

        // check that success message is shown
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='alert alert-success']")));

        // check that new category appears in Categories table
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//td[contains(text(),categoryName)]")));
    }

    /**
     * Use filter for new category in Admin Panel.
     * @param categoryName
     */
    public void filterCategory(String categoryName) {
        waitLogo();

        WebElement filterNameFieldElement = driver.findElement(filterNameField);
        filterNameFieldElement.sendKeys(categoryName);

        WebElement submitFilterButtonElement = driver.findElement(submitFilterButton);
        submitFilterButtonElement.click();

        // check that new category appears in Categories table after filter was applied
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//td[contains(text(),categoryName)]")));
    }

    public void waitLogo() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(logoutImage));
    }

}