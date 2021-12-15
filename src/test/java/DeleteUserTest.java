import com.codeborne.selenide.Condition;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import org.testng.asserts.SoftAssert;

public class DeleteUserTest extends SetUp {
    private final Methods methods = new Methods();
    private final HeaderElements headerElements = new HeaderElements();
    private final SoftAssert sa = new SoftAssert();

    @Epic(value = "Account page.")
    @Feature("Delete user.")
    @Story("Delete user.")
    @Description(value = "Checking user deletion.")
    @Test
    public void deleteUserTest() {
        methods.registration(6);
        methods.deleteUser();
        sa.assertEquals(headerElements.getHelloHeader().getText(), "Hello,");
        sa.assertFalse(headerElements.getAccountButton().isDisplayed());
        headerElements.getEmailField().sendKeys(methods.emailText);
        headerElements.getPasswordField().sendKeys(methods.passwordText);
        headerElements.getLogInButton().click();
        headerElements.getErrorMessage().shouldBe(Condition.visible);
        sa.assertEquals(headerElements.getErrorMessage().getText(), "Could not find user", "Wrong error message");
        sa.assertAll();
    }
}
