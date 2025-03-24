import org.junit.After;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.praktikum.pageObject.HomePageScooter;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class AboutImportantThingTest {
    private int idElement;
    private String question;
    private String answer;


    public AboutImportantThingTest(int idElement,String question, String answer) {
        this.idElement = idElement;
        this.question = question;
        this.answer = answer;

    }

    @Parameterized.Parameters
    public static Collection data() {
        return Arrays.asList(new Object[][] {
                {0,"Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1,"Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2,"Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3,"Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4,"Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5,"Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6,"Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7,"Я живу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        });
    }
    WebDriver driver = new ChromeDriver();
    HomePageScooter homePageScooter = new HomePageScooter(driver);


    @Test
    public void parametrsAboutImportantThingTestElementsImportantList(){
        homePageScooter.open();
        homePageScooter.waitAboutThings();//ожидаем появление вопросов о важном
        homePageScooter.clickAccordionQuestions(idElement);//кликаем в элемент передаем в метод id Элемента
        assertThat("Ожидаемый результат вопроса",homePageScooter.getTextAccordionQuestions(idElement), is(question)); //Проверка вопросов здесь на 7 тесте упадет
        assertThat("Ожидаемый результат ответа",homePageScooter.getTextAccordionAnswers(idElement), is(answer));//Проверка ответов

    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
