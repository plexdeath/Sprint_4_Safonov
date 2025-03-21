import org.junit.After;
import ru.yandex.praktikum.pageObject.HomePageScooter;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class AboutImportantThingTest {
    WebDriver driver = new ChromeDriver();
    HomePageScooter homePageScooter = new HomePageScooter(driver);

    @Test
    public void AboutImportantThingTestElementsImportantList(){ //Тест сравнивает с эталоном верхний текст аккордиона
                homePageScooter.open();//открываем браузер
                homePageScooter.aboutImportantThingsGetDataSiteCombain();//Здесь комбайн который проверяет колличество элментов в аккордионе, кликает на все элементы и получает текст в массив
                assertThat(homePageScooter.elementsImportantList, is(homePageScooter.etalonElementsImportantList));//Здесь находит баг так как на сайте написано Жизу. Сверка данных с эталоном
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
