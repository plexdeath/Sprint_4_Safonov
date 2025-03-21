package ru.yandex.praktikum.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomePageScooter {
    private final WebDriver webDriver;

    private static final String url = "https://qa-scooter.praktikum-services.ru/"; //URL

    private final By orderStatusButtonUP = By.xpath(".//button[@class='Button_Button__ra12g']"); //Кнопка заказать верхняя

    private final By orderStatusButtonDown = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/child::button"); //Кнопка заказать нижняя

    private final By accordionButtonLocator = By.xpath(".//div[@class='accordion__button']"); //Кнопки локаторов о важном

    public final List<String> elementsImportantList = new ArrayList<String>(Arrays.asList());// Коллекция с вопросами листа о важном

    private final By waitAboutThings = By.xpath(".//div[@class='Home_FourPart__1uthg']"); //Кнопки локаторов о важном


    public HomePageScooter(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public HomePageScooter open() {
        webDriver.get(url);
        return this;
    }

    public HomePageScooter goToOrderUp() {//метод нажатия на заказать сверху
        webDriver.findElement(orderStatusButtonUP).click();
        return this;
    }


    public HomePageScooter goToOrderDown() {//метод нажатия на заказать снизу
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click()", webDriver.findElement(orderStatusButtonDown));
        return this;
    }

    public HomePageScooter waitAboutThings() {//метод ожидания вопросов о важном
        new WebDriverWait(webDriver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOfElementLocated(waitAboutThings));
        return this;
    }

    public final List<String> etalonElementsImportantList = List.of( //тестовые данные для сравнения теста о важном
            "Сколько это стоит? И как оплатить?",
            "Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
            "Хочу сразу несколько самокатов! Так можно?",
            "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",
            "Как рассчитывается время аренды?",
            "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",
            "Можно ли заказать самокат прямо на сегодня?",
            "Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
            "Можно ли продлить заказ или вернуть самокат раньше?",
            "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
            "Вы привозите зарядку вместе с самокатом?",
            "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.",
            "Можно ли отменить заказ?",
            "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
            "Я живу за МКАДом, привезёте?",
            "Да, обязательно. Всем самокатов! И Москве, и Московской области."
    );


    public void aboutImportantThingsGetDataSiteCombain() {
        List<WebElement> aboutImportantThings = webDriver.findElements(accordionButtonLocator);//Получаем в коллекцию список вопросов о важном , если вопросов будет больше или меньше мы будем знать точное их колличество
        for (int i = 0; i < aboutImportantThings.size(); i++) { //бежим по массиву
            List<WebElement> elementsImportant = webDriver.findElements(By.id("accordion__heading-" + i));
            for (WebElement elementsimportant : elementsImportant) { //Прокликиваем весь аккордион убеждаемся что аккордион полностью кликабелен и добавляем текстовые элементы в массив elementsImportantList
                ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", elementsimportant);
                elementsImportantList.add(elementsimportant.getText()); //добавляем в массив текстовые значения элементов
            }
            List<WebElement> elementDropDown = webDriver.findElements(By.id("accordion__panel-" + i)); //раскрываем список аккордиона
            for (WebElement elementdropdown : elementDropDown) {
                elementsImportantList.add(elementdropdown.getText());//добавляем текстовые элементы в массив

            }

        }

    }

}
