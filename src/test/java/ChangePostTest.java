import com.codeborne.selenide.Condition;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import org.testng.asserts.SoftAssert;
import java.io.File;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangePostTest extends SetUp {
    private final AccountPage accountPage = new AccountPage();
    private final Methods methods = new Methods();
    private final Constants constants = new Constants();
    SoftAssert sa = new SoftAssert();

    @Epic(value = "Account page.")
    @Feature("Change Post.")
    @Story("Valid values.")
    @Description(value = "Checking post change.")
    @Test
    public void changePostTestPositive() {
        methods.registration(6);
        methods.createPost(10, 10, 10);

        accountPage.getChangePostButton().shouldBe(Condition.visible).click();
        accountPage.getChangePostWindow().shouldBe(Condition.visible);
        accountPage.getChangePostTitleField().setValue(Methods.generateRandomHexString(10));
        String title = accountPage.getChangePostTitleField().getAttribute("value");
        accountPage.getChangePostTextField().setValue(Methods.generateRandomHexString(10));
        String postText = accountPage.getChangePostTextField().getAttribute("value");
        accountPage.getChangePostTagsField().setValue(Methods.generateRandomHexString(10));
        String tag = accountPage.getChangePostTagsField().getAttribute("value");
        File file = new File(new File(constants.getPOST_PICTURE_PATH_FOR_CHANGE()).getAbsolutePath());
        accountPage.getNewAvatarButton().sendKeys(file.getAbsolutePath());

        accountPage.getChangePostSaveButton().shouldBe(Condition.visible).click();
        accountPage.getPost().should(Condition.exist);
        accountPage.getPostTitle().shouldHave(Condition.text(title));
        accountPage.getPostText().shouldHave(Condition.text(postText));
        accountPage.getPostTag().shouldHave(Condition.text(tag));

        sa.assertEquals(accountPage.getPostTitle().getText(), title, "Wrong title");
        sa.assertEquals(accountPage.getPostText().getText(), postText, "Wrong text");
        sa.assertEquals(accountPage.getPostTag().getText(), "#" + tag, "Wrong tag");
        sa.assertAll();

        methods.deletePost();
        methods.deleteUser();
    }

    @Epic(value = "Account page.")
    @Feature("Change Post.")
    @Story("Invalid title.")
    @Description(value = "Check for error message appearance.")
    @Test
    public void changePostTestNegativeInvalidTitle() {
        methods.registration(6);
        methods.createPost(10, 10, 10);
        accountPage.getChangePostButton().shouldBe(Condition.visible).click();
        accountPage.getChangePostWindow().shouldBe(Condition.visible);
        accountPage.getChangePostTitleField().val(Methods.generateRandomHexString(2));
        accountPage.getChangePostTextField().val(Methods.generateRandomHexString(10));
        accountPage.getChangePostTagsField().val(Methods.generateRandomHexString(10));
        accountPage.getChangePostSaveButton().click();
        accountPage.getErrorMessage().shouldBe(Condition.visible);
        assertEquals("news title size not valid", accountPage.getErrorMessage().getText(), "Wrong error message");
        methods.deleteUser();
    }

    @Epic(value = "Account page.")
    @Feature("Change Post.")
    @Story("Invalid text.")
    @Description(value = "Check for error message appearance.")
    @Test
    public void changePostTestNegativeInvalidText() {
        methods.registration(6);
        methods.createPost(10, 10, 10);
        accountPage.getChangePostButton().shouldBe(Condition.visible).click();
        accountPage.getChangePostWindow().shouldBe(Condition.visible);
        accountPage.getChangePostTitleField().val(Methods.generateRandomHexString(10));
        accountPage.getChangePostTextField().val(Methods.generateRandomHexString(2));
        accountPage.getChangePostTagsField().val(Methods.generateRandomHexString(10));
        accountPage.getChangePostSaveButton().click();
        accountPage.getErrorMessage().shouldBe(Condition.visible);
        assertEquals("NEWS_DESCRIPTION_SIZE_NOT_VALID", accountPage.getErrorMessage().getText(), "Wrong error message");
        methods.deleteUser();
    }

    @Epic(value = "Account page.")
    @Feature("Change Post.")
    @Story("Invalid tag.")
    @Description(value = "Check for error message appearance.")
    @Test
    public void changePostTestNegativeInvalidTag() {
        methods.registration(6);
        methods.createPost(10, 10, 10);
        accountPage.getChangePostButton().shouldBe(Condition.visible).click();
        accountPage.getChangePostWindow().shouldBe(Condition.visible);
        accountPage.getChangePostTitleField().val(Methods.generateRandomHexString(10));
        accountPage.getChangePostTextField().val(Methods.generateRandomHexString(10));
        accountPage.getChangePostTagsField().setValue(" ");
        accountPage.getChangePostSaveButton().click();
        accountPage.getErrorMessage().shouldBe(Condition.visible);
        assertEquals("TAGS_NOT_VALID", accountPage.getErrorMessage().getText(), "Wrong error message");
        methods.deleteUser();
    }
}
