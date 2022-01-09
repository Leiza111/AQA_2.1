import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static java.time.format.DateTimeFormatter.ofPattern;

class CardOrderTest {

    public static String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    String planningDate = generateDate(3);

    @Test
    void shouldSuccessfulCardOrder() {
        open("http://localhost:9999");

        Configuration.holdBrowserOpen = true;

        $("[data-test-id='city'] input").setValue("Уфа");
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id='name'] input").setValue("Иванов Петр");
        $("[data-test-id='phone'] input").setValue("+79386542514");
        $("[data-test-id='agreement']").click();
        $$(".button__text").find(exactText("Забронировать")).click();
        $(".notification__title").shouldHave(exactText("Успешно!"), Duration.ofSeconds(20));
        $(".notification__content").shouldHave(exactText("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(20));
    

          }


}
