package ru.praktikum.scooter.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.junit.Assert;

import static org.hamcrest.core.StringStartsWith.startsWith;

public class MakingOrderPage {

    public MakingOrderPage(WebDriver webDriver){
        this.webDriver = webDriver;
    }
private final WebDriver webDriver;
    //Поле Имя
    private By placegolderName = By.xpath(".//input[@placeholder='* Имя']");
    //Поле Фамилия
    private By placegolderSurname = By.xpath(".//input[@placeholder='* Фамилия']");
    //Поле Адрес
    private By placeholderAddress = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    //Поле Станция Метро
    private By placeholderSubway = By.xpath(".//input[@placeholder='* Станция метро']");
    //Поле телефон
    private By placeholderTelephoneNumber = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    //Кнопка Далее
    private By nextForOrder = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    //Когда привезти самокат
    private By placeholderDate = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    //Срок аренды
    private By placeholderPeriod = By.className("Dropdown-placeholder");

    //Кнопка заказать
    private By makingOrder = By.xpath(".//div[contains(@class,'Order_Buttons__1xGrp')]/button[text()='Заказать']");
    //Кнопка Да
    private By confirmButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and contains(text(),'Да')]");
    //Поле  Заказ оформлен
    private final By orderIsCreated = By.xpath(".//div[contains(text(),'Заказ оформлен')]");



    public void setName(String name){
        webDriver.findElement(placegolderName).sendKeys(name);
    }
    public void setSurname(String surname){
        webDriver.findElement(placegolderSurname).sendKeys(surname);
    }
    public void setAddress(String address){
        webDriver.findElement(placeholderAddress).sendKeys(address);
    }
    public void selectSubwayStation(String subway){
        webDriver.findElement(placeholderSubway).click();
        webDriver.findElement(placeholderSubway).sendKeys(subway);
        webDriver.findElement(placeholderSubway).sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
    }
    public void inputPhone(String phone){
        webDriver.findElement(placeholderTelephoneNumber).sendKeys(phone);
    }
    public void clickForButtonNext() {
        webDriver.findElement(nextForOrder).click();
    }
    public void setDateOfDelivery(String date){
        webDriver.findElement(placeholderDate).sendKeys(date, Keys.ENTER);
    }
    public void setPeriodOfRent(String period){
        webDriver.findElement(placeholderPeriod).click();
        webDriver.findElement(By.xpath(".//div[@class='Dropdown-menu']/div[text()='" +period+"']")).click();
    }
    public void clickOrderButton(){
        webDriver.findElement(makingOrder).click();
    }
    public void clickConfirmButton(){
        webDriver.findElement(confirmButton).click();
    }
    public void checkOrderIsCreated(){
        String actual = webDriver.findElement(orderIsCreated).getText();
        Assert.assertThat("Ошибка!", actual, startsWith("Заказ оформлен"));
    }
}
