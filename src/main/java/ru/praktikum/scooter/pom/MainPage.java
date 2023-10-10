package ru.praktikum.scooter.pom;

import com.google.common.annotations.VisibleForTesting;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;


public class MainPage {
    public MainPage(WebDriver webDriver){
        this.webDriver = webDriver;
    }
private final WebDriver webDriver;
    //Кнопка принять куки
    private By acceptCookieButton = By.className("App_CookieButton__3cvqF");

    //Кнопка заказа верхняя
    private By makeOrderButtonTop = By.className("Button_Button__ra12g");
    //Кнопка заказа нижняя
    private By makeOrderButtonBelow = By.xpath(".//div[contains(@class,'Home_FinishButton__1_cWm')]/button[@class='Button_Button__ra12g Button_Middle__1CSJM']");



    public void clickAcceptCookieButton(){
        webDriver.findElement(acceptCookieButton).click();

}
public void clickToMakeOrderButton(int button) {

    if (button == 1) {
        webDriver.findElement(makeOrderButtonTop).click();
    } else if (button ==2) {
        WebElement element = webDriver.findElement(By.className("Home_Status__YkfmH"));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", element);
        webDriver.findElement(makeOrderButtonBelow).click();
}
}

    public void open(){
        webDriver.get("https://qa-scooter.praktikum-services.ru/");

    }
}
