import com.codeborne.selenide.Condition;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DeletePostTest extends SetUp {
    private final Methods methods = new Methods();
    private final AccountPage accountPage = new AccountPage();

    @Epic(value = "Account page.")
    @Feature("Delete Post.")
    @Story("Delete Post.")
    @Description(value = "Checking user deletion.")
    @Test
    public void deletePostTest() {
        methods.registration(6);
        methods.createPost(10, 10, 10);
        methods.deletePost();
        accountPage.getPost().shouldNotBe(Condition.visible);
        assertFalse(accountPage.getPost().isDisplayed(), "Post was not deleted");
        methods.deleteUser();
    }
}
