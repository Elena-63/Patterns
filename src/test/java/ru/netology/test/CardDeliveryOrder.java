package ru.netology.test;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.date.DataGenerator;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class CardDeliveryOrder {
    String name = DataGenerator.generateName();
    String phone = DataGenerator.generatePhone();
    String city = DataGenerator.generateCity();
    String cityEng = DataGenerator.generateCityENG();
    String nameEng = DataGenerator.generateNameEng();

    @BeforeEach
            void seTup(){
        open("http://localhost:9999");
    }

    @Test
    void shouldSubmitRequest() {
        SelenideElement form = $(".form");
        $("[data-test-id=city] input").setValue(city);
        $("[data-test-id=date] input").doubleClick().sendKeys(DataGenerator.generateDate(3));
        $("[data-test-id=name] input").setValue(name);
        $("[data-test-id=phone] input").setValue(phone);
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(".notification_status_ok").shouldBe(visible);
        $("[data-test-id='success-notification'] .notification__content").shouldHave(exactText("Встреча успешно запланирована на " + DataGenerator.generateDate(3)));
        $("[data-test-id=date] input").doubleClick().sendKeys(DataGenerator.generateDate(7));
        $(".button").click();
        $("[data-test-id=replan-notification]").shouldBe(visible, Duration.ofSeconds(12));
        $(withText("Перепланировать")).click();
        $(".notification_status_ok").shouldBe(visible);
        $(".notification__content").shouldHave(exactText("Встреча успешно запланирована на " + DataGenerator.generateDate(7)));
    }

    @Test
    void shouldEnterAnIncorrectFirstAndLastName() {
        SelenideElement form = $(".form");
        $("[data-test-id='city'] input").setValue(city);
        $("[data-test-id=date] input").doubleClick().sendKeys(DataGenerator.generateDate(3));
        $("[data-test-id=name] input").setValue(nameEng);
        $("[data-test-id=phone] input").setValue(phone);
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(byText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldEnterAnIncorrectCity() {
        SelenideElement form = $(".form");
        $("[data-test-id='city'] input").setValue(cityEng);
        $("[data-test-id=date] input").doubleClick().sendKeys(DataGenerator.generateDate(3));
        $("[data-test-id=name] input").setValue(name);
        $("[data-test-id=phone] input").setValue(phone);
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=city].input_invalid").shouldHave(exactText("Доставка в выбранный город недоступна"));
    }

    @Test
    void shouldInterAnIncorrectDate() {
        SelenideElement form = $(".form");
        $("[data-test-id=city] input").setValue(city);
        $("[data-test-id=date] input").doubleClick().sendKeys(DataGenerator.generateDate(-3));
        $("[data-test-id=name] input").setValue(name);
        $("[data-test-id=phone] input").setValue(phone);
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=date]").shouldHave(exactText("Заказ на выбранную дату невозможен"));
    }

    @Test
    void shouldLeaveAllFieldsEmpty() {
        SelenideElement form = $(".form");
        $(".button").click();
        $("[data-test-id=city]").shouldHave(exactText("Поле обязательно для заполнения"));
    }
}