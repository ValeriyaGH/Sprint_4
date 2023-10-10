package ru.praktikum.scooter;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.praktikum.scooter.pom.MainPage;
import ru.praktikum.scooter.pom.MakingOrderPage;

import java.util.concurrent.TimeUnit;

@RunWith(Parameterized.class)
public class TestMakingOrder {
    private WebDriver driver;
    private final int button;
    private final String name;
    private final String surname;
    private final String subway;
    private final String address;
    private final String phone;
    private final String date;
    private final String period;
    public TestMakingOrder(int button, String name, String surname, String address, String subway, String phone, String date, String period){
        this.button = button;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.subway = subway;
        this.phone = phone;
        this.date = date;
        this.period = period;

    }
    @Parameterized.Parameters
    public static Object[][] dataForOrders() {
        return new Object[][]{
                {1, "Хью", "Джекман", "Красногорск, ул Ленина, 15","Митино", "+79774647777", "20.10.2023", "сутки"},
                {2, "Тимоти", "Шаламе", "Осенний б-р, 40", "Крылатское", "+79008889999", "23.10.2023", "трое суток"},

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
    driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
}
@Test
    public void makeOrderTest(){
    MainPage mainPage = new MainPage(driver);
    MakingOrderPage makingOrderPage = new MakingOrderPage(driver);

    mainPage.open();
    mainPage.clickAcceptCookieButton();
    mainPage.clickToMakeOrderButton(button);
    makingOrderPage.setName(name);
    makingOrderPage.setSurname(surname);
    makingOrderPage.setAddress(address);
    makingOrderPage.selectSubwayStation(subway);
    makingOrderPage.inputPhone(phone);
    makingOrderPage.clickForButtonNext();
    makingOrderPage.setDateOfDelivery(date);
    makingOrderPage.setPeriodOfRent(period);
    makingOrderPage.clickOrderButton();
    makingOrderPage.clickConfirmButton();
    makingOrderPage.checkOrderIsCreated();

}
@After
public void quit(){
        driver.quit();
}

}
