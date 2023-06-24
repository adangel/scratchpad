package sample;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Condition.exist; // unnecessary import
import static com.codeborne.selenide.Selenide.$;

public class CallRateLayer {
    public void checkPage() {
        $("ROOT_ELEMENT").shouldBe(visible.because("msg")); // its import use
    }
}
