import com.codeborne.selenide.Condition;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import org.testng.asserts.SoftAssert;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest extends SetUp {

    private final Methods methods = new Methods();
    private final HeaderElements headerElements = new HeaderElements();
    SoftAssert sa = new SoftAssert();

    @Epic(value = "Login page.")
    @Feature("Login.")
    @Story("Valid Login.")
    @Description(value = "Check sign in with email and password.")
    @Test
    public void loginTestPositive() {
        // registration
        methods.registration(6);
        // login
        headerElements.getQuitButton().click();
        headerElements.getEmailField().sendKeys(methods.emailText);
        headerElements.getPasswordField().sendKeys(methods.passwordText);
        headerElements.getLogInButton().click();
        headerElements.getHelloHeader().shouldHave(Condition.text("Hello, " + methods.loginText));
        assertEquals(("Hello, " + methods.loginText), headerElements.getHelloHeader().getText(), "Greeting not matching");
        // delete user
        methods.deleteUser();
    }

    @Epic(value = "Login page.")
    @Feature("Login.")
    @Story("Invalid Password.")
    @Description(value = "Check sign in with invalid password.")
    @Test
    public void loginTestNegativeInvalidPassword() {
        methods.registration(6);
        headerElements.getQuitButton().click();
        headerElements.getEmailField().sendKeys(methods.emailText);
        headerElements.getPasswordField().sendKeys(Methods.generateRandomHexString(10));
        headerElements.getLogInButton().click();
        headerElements.getErrorMessage().shouldHave(Condition.visible);
        sa.assertTrue(headerElements.getErrorMessage().exists(), "Error message not exists");
        sa.assertEquals(headerElements.getErrorMessage().getText(), "password not valid");
        sa.assertAll();
    }

    @Epic(value = "Login page.")
    @Feature("Login.")
    @Story("Invalid email.")
    @Description(value = "Check sign in with invalid email.")
    @Test
    public void loginTestNegativeInvalidEmail() {
        methods.registration(6);
        headerElements.getQuitButton().click();
        headerElements.getEmailField().sendKeys(Methods.generateRandomHexString(10) + "@gmail.com");
        headerElements.getPasswordField().sendKeys(methods.passwordText);
        headerElements.getLogInButton().click();
        headerElements.getErrorMessage().shouldBe(Condition.visible);
        sa.assertTrue(headerElements.getErrorMessage().exists(), "Error message not exists");
        sa.assertEquals(headerElements.getErrorMessage().getText(), "Could not find user", "Wrong error message");
        sa.assertAll();
    }
}
