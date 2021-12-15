import com.codeborne.selenide.Condition;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import org.testng.asserts.SoftAssert;

public class CreateNewPostTest extends SetUp {
    private final Methods methods = new Methods();
    private final HeaderElements headerElements = new HeaderElements();
    private final AccountPage accountPage = new AccountPage();
    private final HomePage homePage = new HomePage();
    SoftAssert sa = new SoftAssert();

    @Epic(value = "Account page.")
    @Feature("Create post.")
    @Story("Valid values.")
    @Description(value = "Post creation check with valid values.")
    @Test
    public void createNewPostTestPositive() {
        methods.registration(6);
        methods.createPost(10,10,10);
        accountPage.getPost().shouldBe(Condition.visible);
        sa.assertEquals(accountPage.getPostTitle().getText(), methods.title, "Wrong title");
        sa.assertEquals(accountPage.getPostText().getText(), methods.postText, "Wrong content");
        sa.assertEquals(accountPage.getPostTag().getText(), "#" + methods.tag, "Wrong tag");
        sa.assertEquals(accountPage.getPostAuthor().getText(), methods.loginText, "Wrong author");
        headerElements.getHomeButton().shouldBe(Condition.visible).click();
        homePage.getPost().shouldBe(Condition.visible);
        sa.assertTrue(homePage.getPost().exists(), "Post not exists");
        sa.assertEquals(homePage.getPostText().getText(), methods.postText, "Wrong content");
        sa.assertAll();
        methods.deletePost();
        methods.deleteUser();
    }

    @Epic(value = "Account page.")
    @Feature("Create post.")
    @Story("Invalid title.")
    @Description(value = "Post creation check with invalid title.")
    @Test
    public void createNewPostTestNegativeInvalidTitle() {
        methods.registration(6);
        methods.createPost(1,10,10);
        accountPage.getErrorMessage().should(Condition.exist);
        sa.assertTrue(accountPage.getErrorMessage().exists(), "Error message not exists");
        sa.assertEquals(accountPage.getErrorMessage().getText(), "news title size not valid", "Wrong error message");
        sa.assertAll();
        methods.deleteUser();
    }

    @Epic(value = "Account page.")
    @Feature("Create post.")
    @Story("Invalid text.")
    @Description(value = "Post creation check with invalid content.")
    @Test
    public void createNewPostTestNegativeInvalidContent() {
        methods.registration(6);
        methods.createPost(10,1,10);
        accountPage.getErrorMessage().should(Condition.exist);
        sa.assertTrue(accountPage.getErrorMessage().exists(), "Error message not exists");
        sa.assertEquals(accountPage.getErrorMessage().getText(), "NEWS_DESCRIPTION_SIZE_NOT_VALID", "Wrong error message");
        sa.assertAll();
        methods.deleteUser();
    }

    @Epic(value = "Account page.")
    @Feature("Create post.")
    @Story("Invalid tag.")
    @Description(value = "Post creation check with invalid tag.")
    @Test
    public void createNewPostTestNegativeInvalidTag() {
        methods.registration(6);
        methods.createPost(10,10,0);
        accountPage.getErrorMessage().should(Condition.exist);
        sa.assertTrue(accountPage.getErrorMessage().exists(), "Error message not exists");
        sa.assertEquals(accountPage.getErrorMessage().getText(), "TAGS_NOT_VALID", "Wrong error message");
        sa.assertAll();
        methods.deleteUser();
    }
}
