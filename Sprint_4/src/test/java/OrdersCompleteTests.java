import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.praktikum.pageObject.HomePageScooter;
import ru.yandex.praktikum.pageObject.OrderPageScooter;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

@RunWith(Parameterized.class)
public class OrdersCompleteTests {

    private String name;
    private String surname;
    private String address;
    private String telephone;
    private String dateBring;
    private String courierComment;

    public OrdersCompleteTests(String name, String surname, String address, String telephone, String dateBring, String courierComment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.telephone = telephone;
        this.dateBring = dateBring;
        this.courierComment = courierComment;
    }

    WebDriver driver = new FirefoxDriver();
    HomePageScooter homePageScooter = new HomePageScooter(driver);
    OrderPageScooter orderPageScooter = new OrderPageScooter(driver);
    static Date dateNow = new Date();
    static SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy");



    @Parameterized.Parameters
    public static Collection data() {
        return Arrays.asList(new Object[][] {
                {"Владимир", "Сафонов","г.Москва Азовская 29к1 кв.30","+79057461346",formatForDateNow.format(dateNow),"Срочно привезти!"},
                {"Иван", "Дулин","г. Москва Металлургов 1","+79996666666",formatForDateNow.format(dateNow),"Захватите Михалычу вместе с самокатом красные труселя!"}
        });
    }

    @Test
    public void ordersCompleteTest() {
        homePageScooter.open();
        homePageScooter.goToOrderUp();
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
