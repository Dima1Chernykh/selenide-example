import com.codeborne.selenide.Condition;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.asserts.SoftAssert;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrationTest extends SetUp {
    private final Methods methods = new Methods();
    private final HeaderElements headerElements = new HeaderElements();
    private final SoftAssert sa = new SoftAssert();
    private final LoginPage loginPage = new LoginPage();

    @Epic(value = "Login page.")
    @Feature("Registration.")
    @Story("Valid Registration.")
    @Description(value = "Check new user registration.")
    @Test
    public void registrationTestPositive() {
        methods.registration(6);
        headerElements.getHelloHeader().shouldHave(Condition.text("Hello, " + methods.loginText));
        assertEquals(headerElements.getHelloHeader().getText(), "Hello, " + methods.loginText, "Greeting not matching");
        methods.deleteUser();
    }

    @Epic(value = "Login page.")
    @Feature("Registration.")
    @Story("Invalid Values.")
    @Description(value = "Check error message exists.")
    @Test
    public void registrationTestNegative() {
        methods.registration(1);
        headerElements.getErrorMessage().shouldBe(Condition.visible);
        sa.assertTrue(headerElements.getErrorMessage().exists(), "Error message not exists");
        sa.assertEquals(headerElements.getErrorMessage().getText(), "USERNAME_SIZE_NOT_VALID", "Wrong error message");
        sa.assertAll();
    }

    @Epic(value = "Login page.")
    @Feature("Registration.")
    @Story("Existing email.")
    @Description(value = "Check error message exists.")
    @Test
    public void registrationTestNegativeExistingEmail() {
        methods.registration(10);
        headerElements.getQuitButton().click();
        headerElements.getSignUpButton().shouldBe(Condition.visible).click();
        loginPage.getEmailReg().shouldBe(Condition.visible).click();
        loginPage.getEmailReg().sendKeys(methods.emailText);
        loginPage.getLoginReg().sendKeys(Methods.generateRandomHexString(10));
        loginPage.getSaveButton().shouldBe(Condition.visible).click();
        headerElements.getErrorMessage().shouldBe(Condition.visible);
        assertEquals("User already exists", headerElements.getErrorMessage().getText(), "Wrong error message");
    }
}
