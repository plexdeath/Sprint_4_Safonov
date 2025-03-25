import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.praktikum.pageObject.HomePageScooter;

public class DopTestsLogo {
    WebDriver driver = new ChromeDriver();
    HomePageScooter homePageScooter = new HomePageScooter(driver);

    @Test
    public void verifyLogoScooterHomePage() {
        homePageScooter.open();
        homePageScooter.goToOrderDown(); //перейти в заказ
        homePageScooter.scooterLogo(); // нажать на логотип самоката
        Assert.assertThat("Проверка что мы на главной странице после нажатия логотипа",homePageScooter.textMainScooterPage(), CoreMatchers.containsString("Привезём его прямо к вашей двери"));
    }

    @Test
    public void verifyLogoYandexHomePage(){
        homePageScooter.open();
        homePageScooter.yandexLogo(); // нажать на логотип самоката
        Assert.assertThat("Проверка что мы на главной странице Яндекса после нажатия на логотип",homePageScooter.yandexMainPage(), CoreMatchers.containsString("Дзен — платформа для просмотра и создания контента."));
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
