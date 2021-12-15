import com.codeborne.selenide.Condition;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import org.testng.asserts.SoftAssert;

public class GetDistinctNewsTest extends SetUp {
    private final LoginPage loginPage = new LoginPage();
    private final Methods methods = new Methods();
    private final HeaderElements headerElements = new HeaderElements();
    private final HomePage homePage = new HomePage();
    SoftAssert sa = new SoftAssert();

    @Epic(value = "Home page.")
    @Feature("Filter.")
    @Story("By Title.")
    @Description(value = "Check the search functionality.")
    @Test
    public void getDistinctNewsTest() {
        methods.registration(6);
        methods.createPost(10,10,10);
        headerElements.getHomeButton().shouldBe(Condition.visible).click();
        homePage.getSearchField().val(methods.title);
        homePage.getSearchButton().shouldBe(Condition.visible).click();
        homePage.getPost().shouldBe(Condition.visible);
        sa.assertTrue(homePage.getPost().exists(), "Post not found");
        sa.assertEquals(homePage.getPostTitle().getText(), methods.title, "Wrong title");
        sa.assertEquals(homePage.getPostText().getText(), methods.postText, "Wrong content");
        sa.assertEquals(homePage.getPostTags().getText(), "#" + methods.tag, "Wrong tag");
        sa.assertAll();
        methods.deleteUser();
    }
}
