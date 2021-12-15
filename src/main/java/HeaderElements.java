import com.codeborne.selenide.SelenideElement;
import lombok.Value;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

@Value
public class HeaderElements {
    SelenideElement logInButton = $(byText("Log In"));
    SelenideElement signUpButton = $(byText("Sign Up"));
    SelenideElement homeButton = $(By.xpath("//a[contains(text(),'Newsfeeds')]"));
    SelenideElement accountButton = $(By.xpath("//a[contains(text(),'My profile')]"));
    SelenideElement quitButton = $(byText("Sign Out"));
    SelenideElement emailField = $(By.id("formBasicEmail"));
    SelenideElement passwordField = $(By.id("formBasicPassword"));

    SelenideElement helloHeader = $(By.cssSelector("div:nth-child(2) div:nth-child(1) header.Header_header__1VCKf div:nth-child(1) > div.Header_header__greetings__3WI4_"));
    SelenideElement errorMessage = $(By.cssSelector("#root > div > header > div > div.Authorization_form__3Hdrv > div"));

}
