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
import java.util.Set;

import static java.lang.Thread.sleep;

public class HomePageScooter {
    private WebDriver webDriver;

    private static final String url = "https://qa-scooter.praktikum-services.ru/"; //URL

    private final By orderStatusButtonUP = By.xpath(".//button[@class='Button_Button__ra12g']"); //Кнопка заказать верхняя

    private final By orderStatusButtonDown = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/child::button"); //Кнопка заказать нижняя

    private final By accordionButtonLocator = By.xpath(".//div[@class='accordion__button']"); //Кнопки локаторов о важном

    public final List<String> elementsImportantList = new ArrayList<String>(Arrays.asList());// Коллекция с вопросами листа о важном

    private final By waitAboutThings = By.xpath(".//div[@class='Home_FourPart__1uthg']"); //Кнопки локаторов о важном

    private final By scooterLogo = By.xpath(".//img[@alt='Scooter']"); //Логотип Самоката

    private final By yandexLogo = By.xpath(".//img[@alt='Yandex']"); //Логотип яндекса

    private final By scooterText = By.xpath(".//div[@class='Home_SubHeader__zwi_E']"); //текст самоката

    private final By buttonStatusOrder = By.xpath(".//button[@class = 'Header_Link__1TAG7' and (text() = 'Статус заказа')]"); //кнопка статус заказа

    private final By inputStatusOrder = By.className("Input_Input__1iN_Z"); //введите номер заказа

    private final By goButton = By.xpath(".//button[(text()='Go!')]"); //введите номер заказа

    private final By picNotFound = By.xpath(".//img[@src='/assets/not-found.png']");


    public String picNotFound() {
       return webDriver.findElement(picNotFound).getAttribute("alt");
    }

    public HomePageScooter goButton() {
        webDriver.findElement(goButton).click();
        return this;
    }

    public HomePageScooter inputStatusOrder(int orderNumber) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(15));
        // Ждем, пока элемент станет кликабельным
        WebElement inputField = wait.until(ExpectedConditions.elementToBeClickable(inputStatusOrder));
        // Используем JavaScript для установки значения
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].value = '" + orderNumber + "'", inputField);
        return this;
    }


    public HomePageScooter buttonStatusOrder() {
        webDriver.findElement(buttonStatusOrder).click();
        return this;
    }


    public HomePageScooter(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public HomePageScooter open() {
        webDriver.get(url);
        return this;
    }

    public HomePageScooter yandexLogo()  {
        new WebDriverWait(webDriver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOfElementLocated(yandexLogo)).click();

        return this;
    }

    public String yandexMainPage() {//получения текста с главной страницы
        Set<String> tabs = webDriver.getWindowHandles(); //Получаем все вкладки
        for (String tab : tabs) {
            webDriver.switchTo().window(tab);// Переключаемся на последнюю вкладку в браузере
        }
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        String title = (String) js.executeScript("return document.title"); //получаем title страницы
        return title;
    }



    public HomePageScooter goToOrder(String locator) {//метод нажатия на заказать сверху
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click()", webDriver.findElement(By.xpath(locator)));
        return this;
    }


    public HomePageScooter goToOrderDown() {//метод нажатия на заказать снизу
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click()", webDriver.findElement(orderStatusButtonDown));
        return this;
    }

    public String textMainScooterPage() {//получения текста с главной страницы
        return webDriver.findElement(scooterText).getText();
    }



    public HomePageScooter waitAboutThings() {//метод ожидания вопросов о важном
        new WebDriverWait(webDriver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOfElementLocated(waitAboutThings));
        return this;
    }

    public HomePageScooter scooterLogo() {//метод ожидания вопросов о важном
        new WebDriverWait(webDriver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOfElementLocated(scooterLogo)).click();
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
