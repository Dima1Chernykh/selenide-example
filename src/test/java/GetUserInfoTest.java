import com.codeborne.selenide.Condition;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import org.testng.asserts.SoftAssert;

public class GetUserInfoTest extends SetUp {
    private final Methods methods = new Methods();
    private final HeaderElements headerElements = new HeaderElements();
    private final AccountPage accountPage = new AccountPage();
    SoftAssert sa =new SoftAssert();

    @Epic(value = "Account page.")
    @Feature("Get User Info.")
    @Story("User Info.")
    @Description(value = "Check user info.")
    @Test
    public void getInfoTest() {
        methods.registration(6);
        headerElements.getAccountButton().shouldBe(Condition.visible).click();
        accountPage.getUserInfo().shouldBe(Condition.visible);
        accountPage.getLoginInfo().shouldBe(Condition.visible).shouldHave(Condition.exactText("User login: " + methods.loginText));
        accountPage.getEmailInfo().shouldBe(Condition.visible).shouldHave(Condition.exactText("User e-mail: " + methods.emailText));
        sa.assertEquals(accountPage.getEmailInfo().getText(), ("User e-mail: " + methods.emailText), "Wrong user e-mail");
        sa.assertEquals(accountPage.getLoginInfo().getText(), ("User login: " + methods.loginText), "Wrong user login");
        sa.assertAll();
        methods.deleteUser();
    }
}
