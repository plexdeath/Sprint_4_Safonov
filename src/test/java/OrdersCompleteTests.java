import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.praktikum.pageObject.HomePageScooter;
import ru.yandex.praktikum.pageObject.OrderPageScooter;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import static java.lang.Thread.sleep;

@RunWith(Parameterized.class)
public class OrdersCompleteTests {

    private String name;
    private String surname;
    private String address;
    private String telephone;
    private String dateBring;
    private String courierComment;
    private String locatorOrder;
    private static final String locatorOrderUP = ".//button[@class='Button_Button__ra12g']"; //Локатор для верхней кнопки заказа
    private static final String locatorOrderDown =".//div[@class='Home_FinishButton__1_cWm']/child::button"; //Локатор для нижней кнопки заказа

    public OrdersCompleteTests(String name, String surname, String address, String telephone, String dateBring, String courierComment,String locatorOrder) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.telephone = telephone;
        this.dateBring = dateBring;
        this.courierComment = courierComment;
        this.locatorOrder= locatorOrder;
    }

    WebDriver driver = new ChromeDriver();//Здесь подключения хром драйвер по умолчанию , можно закоментить эту строку и раскоментить строку с файрфоксом
    //WebDriver driver = new FirefoxDriver();//Здесь можно раскоментировать строку и переключится  драйвер файрфокс

    HomePageScooter homePageScooter = new HomePageScooter(driver);
    OrderPageScooter orderPageScooter = new OrderPageScooter(driver);
    static Date dateNow = new Date();
    static SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy");



    @Parameterized.Parameters
    public static Collection data() {
        return Arrays.asList(new Object[][] {
                {"Владимир", "Сафонов","г.Москва Азовская 29к1 кв.30","+79057461346",formatForDateNow.format(dateNow),"Срочно привезти!",locatorOrderUP},
                {"Иван", "Дулин","г. Москва Металлургов 1","+79996666666",formatForDateNow.format(dateNow),"Захватите Михалычу вместе с самокатом , красные труселя!",locatorOrderDown}
        });
    }

    @Test
    public void ordersCompleteTestUp() { //Проверка теста с первой точкой входа верхняя кнопка заказать
        homePageScooter.open();
        homePageScooter.goToOrder(locatorOrder);// выполняется тест в первом он делает заказ с верхней кнопки во втором случае с нижней кнопки заказать
        orderPageScooter.inputName(name);
        orderPageScooter.inputSurname(surname);
        orderPageScooter.inputAddressOrder(address);
        orderPageScooter.dropDownMetro();
        orderPageScooter.clickMetroValue();
        orderPageScooter.inputTelephone(telephone);
        orderPageScooter.buttonNext();
        orderPageScooter.orderWaitPage();
        orderPageScooter.whenBringItScooter(dateBring);
        orderPageScooter.datePickerKiller();
        orderPageScooter.dropDownRent();
        orderPageScooter.selectDropDownRent();
        orderPageScooter.selectBlackColorScooter();
        orderPageScooter.orderWaitPage();
        orderPageScooter.inputCourierComment(courierComment);
        orderPageScooter.buttonToOrder();
        orderPageScooter.modalWindowAcceptOrder();
        orderPageScooter.buttonYes();
        Assert.assertThat("Проверка оформления заказа",orderPageScooter.labelOrderCompleteGetText(), CoreMatchers.containsString("Заказ оформлен"));
    }


    @After
    public void tearDown() {
        driver.quit();
    }
}
