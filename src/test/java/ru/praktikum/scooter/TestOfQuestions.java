package ru.praktikum.scooter;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.praktikum.scooter.pom.MainPage;
import ru.praktikum.scooter.pom.QuestionsPage;


import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.core.StringContains.containsString;


@RunWith(Parameterized.class)
public class TestOfQuestions {
    WebDriver driver;
    //Id наших вопросов
    private final String accordeonId;
    //видимость ответов
    private final boolean accordeonPanelIsVisible;
    //соответствие текста ответа ожидаемому
    private final String accordeonTextExpected;

    public TestOfQuestions(String accordeonId, boolean accordeonPanelIsVisible, String accordeonTextExpected){
        this.accordeonId = accordeonId;
        this.accordeonPanelIsVisible = accordeonPanelIsVisible;
        this.accordeonTextExpected = accordeonTextExpected;

    }

    @Parameterized.Parameters
    public static Object[][] getQuestionsAndAnswers(){
        return new Object[][]{
                {"accordion__heading-0", false, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {"accordion__heading-1", false, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, " +
                        "можете просто сделать несколько заказов — один за другим."},
                {"accordion__heading-2", false, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, " +
                        "когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {"accordion__heading-3", false, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {"accordion__heading-4", false, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку " +
                        "по красивому номеру 1010."},
                {"accordion__heading-5", false, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — " +
                        "даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {"accordion__heading-6", false, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. " +
                        "Все же свои."},
                {"accordion__heading-7", false, "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };

    }



    @Before
    public void setUp(){
        //Для проверок в Хроме
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        //Для проверок в Файрфокс
        //WebDriverManager.firefoxdriver().setup();
        //driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        MainPage mainPage = new MainPage(driver);

    }


    @Test
    public void QuestionsTest(){
        MainPage mainPage = new MainPage(driver);
        QuestionsPage questionsPage = new QuestionsPage(driver,accordeonId);
        mainPage.open();
        mainPage.clickAcceptCookieButton();
        questionsPage.scrollForQuestions();
        questionsPage.clickAccordionItemHeading();
        WebElement answer = questionsPage.getAccordionItemPanel();
        boolean isVisible = Boolean.parseBoolean(answer.getAttribute("hidden"));
        assertEquals(isVisible, accordeonPanelIsVisible);
        String actualAnswer = questionsPage.accordionItemPanelText().getText();
        MatcherAssert.assertThat(actualAnswer, containsString(accordeonTextExpected));












    }
    @After
            public void tearDoun(){
        driver.quit();
    }


}