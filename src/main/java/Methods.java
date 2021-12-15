import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.SneakyThrows;
import org.openqa.selenium.Alert;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.switchTo;
import java.io.File;
import java.util.Random;

public class Methods {

    private final LoginPage loginPage = new LoginPage();
    private final HeaderElements headerElements = new HeaderElements();
    private final AccountPage accountPage = new AccountPage();
    private final Constants constants = new Constants();

    String loginText;
    String emailText;
    String passwordText;
    String title;
    String postText;
    String tag;

    // random string generation
    public static String generateRandomHexString(int length) {
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        while (sb.length() < length) {
            sb.append(Integer.toHexString(r.nextInt()));
        }
        return sb.toString().substring(0, length);
    }

    @Step("registration")
    public void registration(int symbolCount) {
        open(constants.getHOME_PAGE_URL());
        headerElements.getSignUpButton().shouldBe(Condition.visible).click();
        loginPage.getRegWindow().shouldBe(Condition.visible);
        loginPage.getEmailReg().shouldBe(Condition.visible).click();
        loginPage.getEmailReg().sendKeys(generateRandomHexString(symbolCount) + "@gmail.com");
        emailText = loginPage.getEmailReg().getAttribute("value");
        loginPage.getLoginReg().shouldBe(Condition.visible).click();
        loginPage.getLoginReg().sendKeys(generateRandomHexString(symbolCount));
        loginText = loginPage.getLoginReg().getAttribute("value");
        loginPage.getPasswordReg().shouldBe(Condition.visible).click();
        loginPage.getPasswordReg().sendKeys(generateRandomHexString(symbolCount));
        passwordText = loginPage.getPasswordReg().getAttribute("value");
        File file = new File(new File(constants.getAVATAR_PATH_FOR_REG()).getAbsolutePath());
        loginPage.getAvatarReg().sendKeys(file.getAbsolutePath());
        loginPage.getSaveButton().click();
        headerElements.getHelloHeader().shouldBe(Condition.visible);
    }

    @Step("create post")
    public void createPost(int titleSymbolCount, int postTextSymbolCount, int tagSymbolCount) {
        headerElements.getAccountButton().shouldBe(Condition.visible).click();
        accountPage.getUserInfo().shouldBe(Condition.visible);
        accountPage.getNewPostButton().shouldBe(Condition.visible).click();
        accountPage.getAddNewPostWindow().shouldBe(Condition.visible);
        accountPage.getNewPostTitleField().sendKeys(Methods.generateRandomHexString(titleSymbolCount));
        title = accountPage.getNewPostTitleField().getAttribute("value");
        accountPage.getNewPostTextField().sendKeys(Methods.generateRandomHexString(postTextSymbolCount));
        postText = accountPage.getNewPostTextField().getAttribute("value");
        accountPage.getNewPostTagsField().sendKeys(Methods.generateRandomHexString(tagSymbolCount));
        tag = accountPage.getNewPostTagsField().getAttribute("value");
        File file = new File(new File(constants.getPOST_PICTURE_PATH()).getAbsolutePath());
        accountPage.getNewPostPictureButton().sendKeys(file.getAbsolutePath());
        accountPage.getNewPostSaveButton().click();
    }

    public void deletePost() {
        headerElements.getAccountButton().shouldBe(Condition.visible).click();
        accountPage.getPost().shouldBe(Condition.visible);
        accountPage.getDeletePostButton().shouldBe(Condition.visible).click();
    }

    @SneakyThrows
    public void deleteUser() {
        headerElements.getAccountButton().shouldBe(Condition.visible).click();
        accountPage.getDeleteProfileButton().shouldBe(Condition.visible).click();
        Alert alert = switchTo().alert();
        alert.accept();
        Thread.sleep(2000);
    }
}
