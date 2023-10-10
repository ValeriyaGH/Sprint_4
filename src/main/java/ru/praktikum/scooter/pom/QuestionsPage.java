package ru.praktikum.scooter.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class QuestionsPage {
    private final WebDriver webDriver;

    //Вопросы
    private By accordionItemHeading;
    //Поля с ответами
    private By accordionItemPanel;
    //Текст внутри полей с ответами
    private By accordionItemPanelText;

//Конструктор класса, позволяющий с помощью смены id искать вопросы и соотвествующие им ответы
    public QuestionsPage(WebDriver webDriver, String accordionId){
        this.webDriver = webDriver;
        this.accordionItemHeading = By.xpath(".//div[@id='"+ accordionId +"']");
        this.accordionItemPanel = By.xpath(".//div[@aria-labelledby='"+ accordionId +"']");
        this.accordionItemPanelText = By.xpath(".//div[@aria-labelledby='"+ accordionId +"']/p");

    }

    public void clickAccordionItemHeading() {
        WebElement element = webDriver.findElement(accordionItemHeading);
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", element);
        new Actions(webDriver).moveToElement(element).click().perform();
    }
    public WebElement getAccordionItemPanel() {
        return webDriver.findElement(accordionItemPanel);
    }

    public WebElement accordionItemPanelText() {

        return webDriver.findElement(accordionItemPanelText);
    }
    public void scrollForQuestions(){
        WebElement element = webDriver.findElement(By.className("accordion"));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", element);

    }


}
