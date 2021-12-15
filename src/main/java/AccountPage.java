import com.codeborne.selenide.SelenideElement;
import lombok.Value;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Value
public class AccountPage {

    SelenideElement userInfo = $(By.xpath("//div[contains(text(),'User info')]"));
    SelenideElement updateButton = $(byText("Update"));
    SelenideElement newPostButton = $(byText("New Post"));
    SelenideElement loginInfo = $(By.xpath("//body/div[@id='root']/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]"));
    SelenideElement emailInfo = $(By.xpath("//body[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]"));
    SelenideElement deleteProfileButton = $(By.xpath("//body[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/button[2]"));
    SelenideElement errorMessageChangeUser = $(By.xpath("//body[1]/div[1]/div[1]/div[1]/div[2]/div[1]"));

    // post
    SelenideElement post = $(By.cssSelector("#root > div > div > div:nth-child(2) > div:nth-child(4) > div"));
    SelenideElement postTitle = $(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[2]/div[4]/div[1]/div[1]/div[1]"));
    SelenideElement postText = $(By.tagName("p"));
    SelenideElement postTag = $(By.xpath("//body/div[@id='root']/div[1]/div[1]/div[2]/div[4]/div[1]/div[1]/div[2]"));
    SelenideElement postAuthor = $(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[2]/div[4]/div[1]/a[1]"));
    SelenideElement deletePostButton = $(By.xpath("//body[1]/div[1]/div[1]/div[1]/div[2]/div[4]/div[1]/div[2]/button[1]"));
    SelenideElement changePostButton = $$(By.xpath("//button[contains(text(),'Update')]")).get(1);
    SelenideElement errorMessage = $(By.cssSelector("div:nth-child(2) div:nth-child(1) div.Profile_profile__3dzvr div:nth-child(2) > div.fade.alert.alert-danger.show:nth-child(4)"));

    // update post form
    SelenideElement changePostWindow = $(By.xpath("//body/div[3]/div[1]/div[1]"));
    SelenideElement changePostTitleField = $(By.xpath("//body/div[3]/div[1]/div[1]/form[1]/div[1]/input[1]"));
    SelenideElement changePostTextField = $(By.xpath("//body/div[3]/div[1]/div[1]/form[1]/div[2]/textarea[1]"));
    SelenideElement changePostPictureButton = $(By.xpath("//body/div[3]/div[1]/div[1]/form[1]/div[3]/input[1]"));
    SelenideElement changePostTagsField = $(By.xpath("//body/div[3]/div[1]/div[1]/form[1]/div[4]/input[1]"));
    SelenideElement changePostCloseButton = $(byText("Close"));
    SelenideElement changePostSaveButton = $(byText("Save"));

    // update profile form
    SelenideElement updateProfileWindow = $(By.xpath("//body/div[3]/div[1]/div[1]"));
    SelenideElement newEmail = $(By.xpath("//body/div[3]/div[1]/div[1]/form[1]/div[1]/input[1]"));
    SelenideElement newName = $(By.xpath("//body/div[3]/div[1]/div[1]/form[1]/div[2]/input[1]"));
    SelenideElement newAvatarButton = $(By.xpath("//body/div[3]/div[1]/div[1]/form[1]/div[3]/input[1]"));
    SelenideElement updateProfileCloseButton = $(byText("Close"));
    SelenideElement updateProfileSaveButton = $(byText("Save"));

    // new post form
    SelenideElement addNewPostWindow = $(By.xpath("//body/div[3]/div[1]/div[1]"));
    SelenideElement newPostTitleField = $(By.xpath("//body/div[3]/div[1]/div[1]/form[1]/div[1]/input[1]"));
    SelenideElement newPostTextField = $(By.xpath("//body/div[3]/div[1]/div[1]/form[1]/div[2]/textarea[1]"));
    SelenideElement newPostPictureButton = $(By.xpath("//body/div[3]/div[1]/div[1]/form[1]/div[3]/input[1]"));
    SelenideElement newPostTagsField = $(By.xpath("//body/div[3]/div[1]/div[1]/form[1]/div[4]/input[1]"));
    SelenideElement newPostCloseButton = $(byText("Close"));
    SelenideElement newPostSaveButton = $(byText("Save"));


 }
