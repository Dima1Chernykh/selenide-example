import com.codeborne.selenide.SelenideElement;
import lombok.Value;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

@Value
public class LoginPage {
    SelenideElement regWindow = $(By.className("modal-content"));
    SelenideElement emailReg = $(By.cssSelector("body > div.fade.modal.show > div > div > form > div:nth-child(1) > input"));
    SelenideElement loginReg = $(By.cssSelector("body > div.fade.modal.show > div > div > form > div:nth-child(2) > input"));
    SelenideElement passwordReg = $(By.cssSelector("body > div.fade.modal.show > div > div > form > div:nth-child(3) > input"));
    SelenideElement avatarReg = $(By.xpath("//body[1]/div[3]/div[1]/div[1]/form[1]/div[4]/input[1]"));
    SelenideElement closeButton = $(byText("Close"));
    SelenideElement saveButton = $(byText("Save"));
}
