package ru.yandex.praktikum.pageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static java.lang.Thread.sleep;

public class OrderPageScooter {

    private final WebDriver webDriver;

    public OrderPageScooter(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    private final By inputName = By.xpath(".//input[@placeholder='* Имя']"); //инпут имя

    private final By inputSurname = By.xpath(".//input[@placeholder='* Фамилия']");//инпут фамилия

    private final By inputAddressOrder = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");//инпут адрес куда привезти заказ

    private final By dropDownMetro = By.xpath(".//input[@placeholder='* Станция метро']");//дроп даун выбора метро

    private final By clickMetroValue = By.xpath(".//*[@value='170']");//значение станции метро Севастопольская

    private final By inputTelephone = By.xpath(".//*[@placeholder='* Телефон: на него позвонит курьер']");//дроп даун выбора метро

    private final By buttonNext = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");//Кнопка далее

    private final By orderWaitPage = By.xpath(".//*[@class='Order_Content__bmtHS']");//ожидание страницы

    private final By whenBringItScooter = By.xpath(".//*[@placeholder='* Когда привезти самокат']");//поле выбора даты

    private final By datePickerKiller = By.xpath(".//div[@class='react-datepicker']");//пикер выбора даты для удаления яваскриптом

    private final By dropDownRent = By.xpath(".//div[@class='Dropdown-placeholder']");//поле выбора срока аренды

    private final By selectDropDownRent = By.xpath(".//*[@class='Dropdown-option' and (text() = 'двое суток')]");//выбрать срок аренды

    private final By selectBlackColorScooter = By.xpath(".//input[@id='black']");//выбрать черный цвет самоката

    private final By inputCourierComment = By.xpath(".//*[@placeholder='Комментарий для курьера']");//Коментарий для курьера

    private final By buttonToOrder = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");//заказать кнопка

    private final By modalWindowAcceptOrder = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']"); //Модальное окно с подтверждением заказа

    private final By buttonYes = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and (text() = 'Да')]"); //Кнопка Да на модальном окне

    private final By labelOrderComplete = By.xpath(".//div[text()='Заказ оформлен']"); //лэйбл Заказ оформлен

    private final By errorName = By.xpath("//input[@placeholder='* Имя']/following-sibling::div"); //ошибка если введено имя неправильно

    private final By errorSurname = By.xpath("//input[@placeholder='* Фамилия']/following-sibling::div"); //ошибка если введена фамилия неправильно

    private final By errorAddress = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']/following-sibling::div"); //ошибка если введен адрес неправильно

    private final By errorMetro = By.xpath("//input[@placeholder='* Станция метро']/following::div[text()='Выберите станцию']"); //ошибка если станция выбрана не правильно

    private final By courierTelephone = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']/following-sibling::div"); //ошибка если введен некоректный номер телефона

    public String courierTelephone() {
        return webDriver.findElement(courierTelephone).getAttribute("textContent");
    }

    public String errorMetro() {
        return webDriver.findElement(errorMetro).getAttribute("textContent");
    }

    public String errorName() {
       return webDriver.findElement(errorName).getAttribute("textContent");
    }

    public String errorSurname() {
        return webDriver.findElement(errorSurname).getAttribute("textContent");
    }

    public String errorAddress() {
        return webDriver.findElement(errorAddress).getAttribute("textContent");
    }


    public OrderPageScooter inputName(String name) {
        webDriver.findElement(inputName).sendKeys(name);
        return this;
    }

    public OrderPageScooter inputSurname(String surname) {
        webDriver.findElement(inputSurname).sendKeys(surname);
        return this;
    }

    public OrderPageScooter inputAddressOrder(String address) {
        webDriver.findElement(inputAddressOrder).sendKeys(address);
        return this;
    }

    public OrderPageScooter dropDownMetro() {
        new WebDriverWait(webDriver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOfElementLocated(dropDownMetro)).click();
        return this;
    }

    public OrderPageScooter clickMetroValue()  {
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView()", webDriver.findElement(clickMetroValue));
        webDriver.findElement(clickMetroValue).click();
        return this;

    }

    public OrderPageScooter inputTelephone(String telephone) {
        webDriver.findElement(inputTelephone).sendKeys(telephone);
        return this;
    }
    public OrderPageScooter buttonNext() {
        webDriver.findElement(buttonNext).click();
        return this;
    }

    public OrderPageScooter orderWaitPage() {
       new WebDriverWait(webDriver, Duration.ofSeconds(30))
                   .until(ExpectedConditions.visibilityOfElementLocated(orderWaitPage));
       return this;
    }
    public OrderPageScooter whenBringItScooter(String dateBring) {
        webDriver.findElement(whenBringItScooter).sendKeys(dateBring);
        return this;
    }

    public OrderPageScooter datePickerKiller() {
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].remove()", webDriver.findElement(datePickerKiller));
        return this;
    }

    public OrderPageScooter dropDownRent() {
        webDriver.findElement(dropDownRent).click();
        return this;
    }

    public OrderPageScooter selectDropDownRent() {
        webDriver.findElement(selectDropDownRent).click();
        return this;
    }

    public OrderPageScooter selectBlackColorScooter() {
        webDriver.findElement(selectBlackColorScooter).click();
        return this;
    }

    public OrderPageScooter inputCourierComment(String couriercomment) {
        webDriver.findElement(inputCourierComment).sendKeys(couriercomment);
        return this;
    }

    public OrderPageScooter buttonToOrder() {
        webDriver.findElement(buttonToOrder).click();
        return this;
    }

    public OrderPageScooter modalWindowAcceptOrder() {
       new WebDriverWait(webDriver, Duration.ofSeconds(30))
                    .until(ExpectedConditions.visibilityOfElementLocated(modalWindowAcceptOrder));
        return this;
    }

    public OrderPageScooter buttonYes() {
        webDriver.findElement(buttonYes).click();
        return this;
    }

    public String labelOrderCompleteGetText() {
        return webDriver.findElement(labelOrderComplete).getText();
    }

}