import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.praktikum.pageObject.HomePageScooter;
import ru.yandex.praktikum.pageObject.OrderPageScooter;

import java.util.Arrays;
import java.util.Collection;


import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class DopTestsErrorWrongOrder {
    private String name;
    private String surname;
    private String address;
    private String telephone;

    public DopTestsErrorWrongOrder(String name, String surname, String address, String telephone) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.telephone = telephone;
    }

    @Parameterized.Parameters
    public static Collection data() {
        return Arrays.asList(new Object[][] {
                {"Dulin", "KrasnieTrysi","---------------","544534fgf"},
        });
    }


    WebDriver driver = new ChromeDriver();//Здесь подключения хром драйвер по умолчанию , можно закоментить эту строку и раскоментить строку с файрфоксом
    //WebDriver driver = new FirefoxDriver();//Здесь можно раскоментировать строку и переключится на драйвер файрфокс

    HomePageScooter homePageScooter = new HomePageScooter(driver);
    OrderPageScooter orderPageScooter = new OrderPageScooter(driver);


    @Test
    public void dopTestError() {
        homePageScooter.open();
        homePageScooter.goToOrderDown();
        orderPageScooter.inputName(name);
        assertTrue("Проверить ошибку имени",orderPageScooter.errorName().contains("Введите корректное имя"));
        orderPageScooter.inputSurname(surname);
        assertTrue("Проверить ошибку фамилии",orderPageScooter.errorSurname().contains("Введите корректную фамилию"));
        orderPageScooter.inputAddressOrder(address);
        assertTrue("Проверить ошибку адреса",orderPageScooter.errorAddress().contains("Введите корректный адрес"));
        orderPageScooter.dropDownMetro();
        orderPageScooter.inputTelephone(telephone);
        assertTrue("Проверить ошибку телефона курьера",orderPageScooter.courierTelephone().contains("Введите корректный номер"));
        orderPageScooter.buttonNext();
        assertTrue("Проверить ошибку выбора метро",orderPageScooter.errorMetro().contains("Выберите станцию"));
    }

    @Test
    public void dopTestWrongOrder() throws InterruptedException {
        homePageScooter.open();
        homePageScooter.buttonStatusOrder();
        homePageScooter.inputStatusOrder(1000);
        homePageScooter.goButton();
        Assert.assertThat("Проверка что страница не найдена",homePageScooter.picNotFound(), CoreMatchers.containsString("Not found"));//Проверили что страница не найдена
    }



    @After
    public void tearDown() {
        driver.quit();
    }

}
