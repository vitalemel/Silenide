import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DeliveryCard {


    private String generate(int addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test

    public void DeliveryCardTest() {
        open("http://localhost:9999/");
        $("[data-test-id ='city'] input").setValue("Челябинск");
        String currentDate = generate(3, "dd.MM.yyyy");
        $("[data-test-id ='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id ='date'] input").sendKeys(currentDate);
        $("[data-test-id ='name'] input").setValue("Петр Алексеевич Перов");
        $("[data-test-id ='phone'] input").setValue("+79881111111");
        $("[data-test-id ='agreement']").click();
        $("button.button").click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + currentDate));

    }

}