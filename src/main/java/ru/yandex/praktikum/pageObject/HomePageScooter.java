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

    private final By orderStatusButtonDown = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/child::button"); //Кнопка заказать нижняя

    private final By waitAboutThings = By.xpath(".//div[@class='Home_FourPart__1uthg']"); //Кнопки локаторов о важном

    private final By scooterLogo = By.xpath(".//img[@alt='Scooter']"); //Логотип Самоката

    private final By yandexLogo = By.xpath(".//img[@alt='Yandex']"); //Логотип яндекса

    private final By scooterText = By.xpath(".//div[@class='Home_SubHeader__zwi_E']"); //текст самоката

    private final By buttonStatusOrder = By.xpath(".//button[@class = 'Header_Link__1TAG7' and (text() = 'Статус заказа')]"); //кнопка статус заказа

    private final By inputStatusOrder = By.className("Input_Input__1iN_Z"); //введите номер заказа

    private final By goButton = By.xpath(".//button[(text()='Go!')]"); //введите номер заказа

    private final By picNotFound = By.xpath(".//img[@src='/assets/not-found.png']");

    private static final String accordionQuestions = "accordion__heading-";

    private static final String accordionAnswers = "accordion__panel-";

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

    public HomePageScooter clickAccordionQuestions(int idElement) {
       ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", webDriver.findElement(By.id(accordionQuestions + idElement)));
        return this;
    }

    public String getTextAccordionQuestions(int idElement) {

       return webDriver.findElement(By.id(accordionQuestions + idElement)).getText();
    }

    public String getTextAccordionAnswers(int idElement) {
        return webDriver.findElement(By.id(accordionAnswers + idElement)).getText();
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

}
