import com.codeborne.selenide.Condition;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.testng.asserts.SoftAssert;
import static com.codeborne.selenide.Selenide.*;

public class GetNewsWithPaginationTest extends SetUp {
    private final SoftAssert sa = new SoftAssert();
    private final Methods methods = new Methods();
    private final HeaderElements headerElements = new HeaderElements();
    private final AccountPage accountPage = new AccountPage();
    private final HomePage homePage = new HomePage();
    private final Constants constants = new Constants();

    @SneakyThrows
    @Epic(value = "Home page.")
    @Feature("Pagination.")
    @Story("Scroll down.")
    @Description(value = "Check pagination.")
    @Test
    public void getNewsWithPaginationTest() {
        methods.registration(10);
        for (int i = 0; i < constants.getPOSTS_ON_PAGE() * 2; i++) {
            methods.createPost(10,10,10);
        }
        open(constants.getHOME_PAGE_URL());
        Thread.sleep(2000);
        int currentPostsOnPage = homePage.getPosts().size();
//        System.out.println(currentPostsOnPage);
        sa.assertTrue(constants.getPOSTS_ON_PAGE() == currentPostsOnPage);
        homePage.getBody().scrollIntoView(false);
        Thread.sleep(2000);
        int currentPostsOnPageAfterScroll = homePage.getPosts().size();
//        System.out.println(currentPostsOnPageAfterScroll);
        sa.assertTrue(currentPostsOnPageAfterScroll == currentPostsOnPage * 2);
        headerElements.getAccountButton().shouldBe(Condition.visible).click();
        methods.deleteUser();
        sa.assertAll();
    }
}
