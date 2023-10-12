package ru.praktikum.scooter;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    public TestOfQuestions(String accordeonId, String accordeonTextExpected) {
        this.accordeonId = accordeonId;
        this.accordeonTextExpected = accordeonTextExpected;
        accordeonPanelIsVisible = false;
    }

    @Parameterized.Parameters
    public static Object[][] getQuestionsAndAnswers() {
        return new Object[][]{
                {"0", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {"1", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, " +
                        "можете просто сделать несколько заказов — один за другим."},
                {"2", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, " +
                        "когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {"3", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {"4", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку " +
                        "по красивому номеру 1010."},
                {"5", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — " +
                        "даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {"6", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. " +
                        "Все же свои."},
                {"7", "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }

    @Before
    public void setUp() {
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
    public void questionsTest() {
        MainPage mainPage = new MainPage(driver);
        QuestionsPage questionsPage = new QuestionsPage(driver, accordeonId);
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
    public void tearDoun() {
        driver.quit();
    }

}