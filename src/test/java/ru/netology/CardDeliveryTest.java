package ru.netology;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {
    private DataGeneratorForm dataGeneratorForm = DataGenerator.generateData();

    private SelenideElement planBtnElement;
    private SelenideElement replainElement;
    private SelenideElement planSuccessElement;

    @BeforeEach
    void setUp() {

        Configuration.browser = "chrome";
        open("http://localhost:9999");
    }

    @Test
    @DisplayName("Should successful plan and replan meeting")
    void shouldSuccessfulPlanAndReplanMeeting() {


        $("[data-test-id='city'] input").setValue(dataGeneratorForm.getCity());

        SelenideElement dateElement = $("[data-test-id=date] input[class=input__control]");
        dateElement.sendKeys(Keys.LEFT_CONTROL + "a" + Keys.BACK_SPACE);
        String date = DataGenerator.getDate(4);
        dateElement.setValue(date);

        $("[data-test-id=name] input").setValue(dataGeneratorForm.getName());
        $("[data-test-id=phone] input").setValue(dataGeneratorForm.getPhone());
        $("[data-test-id=agreement]").click();

        planBtnElement = $$("button").find(exactText("Запланировать"));
        planBtnElement.click();

        planSuccessElement = $("[data-test-id=success-notification]");
        planSuccessElement.shouldHave(text("Встреча успешно запланирована на")).shouldHave(text(date));

        dateElement.sendKeys(Keys.LEFT_CONTROL + "a" + Keys.BACK_SPACE);
        date = DataGenerator.getDate(7);
        dateElement.setValue(date);
        planBtnElement.click();

        replainElement = $("[data-test-id=replan-notification]");
        replainElement.shouldHave(text("Необходимо подтверждение"));
        replainElement.$$("button").find(exactText("Перепланировать")).click();

        planSuccessElement.shouldHave(text("Встреча успешно запланирована на")).shouldHave(text(date));

        // TODO: добавить логику теста в рамках которого будет выполнено планирование и перепланирование встречи.
        // Для заполнения полей формы можно использовать пользователя validUser и строки с датами в переменных
        // firstMeetingDate и secondMeetingDate. Можно также вызывать методы generateCity(locale),
        // generateName(locale), generatePhone(locale) для генерации и получения в тесте соответственно города,
        // имени и номера телефона без создания пользователя в методе generateUser(String locale) в датагенераторе
    }
}
