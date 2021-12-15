import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import lombok.Value;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Value
public class HomePage {
    SelenideElement searchField = $(By.xpath("//body/div[@id='root']/div[1]/main[1]/div[1]/form[1]/div[1]/input[1]"));
    SelenideElement searchButton = $(byText("Search"));
    SelenideElement post = $(By.xpath("//body[1]/div[1]/div[1]/main[1]/div[2]/div[1]"));
    SelenideElement postTitle = $(By.xpath("/html[1]/body[1]/div[1]/div[1]/main[1]/div[2]/div[1]/div[1]/div[1]"));
    SelenideElement postText = $(By.xpath("/html[1]/body[1]/div[1]/div[1]/main[1]/div[2]/div[1]/div[1]/p[1]"));
    SelenideElement postTags = $(By.xpath("//body/div[@id='root']/div[1]/main[1]/div[2]/div[1]/div[1]/div[2]"));
    SelenideElement body = $(By.xpath("//body"));
    ElementsCollection posts = $$(By.cssSelector("#root > div > main > div:nth-child(2) > div"));
}
