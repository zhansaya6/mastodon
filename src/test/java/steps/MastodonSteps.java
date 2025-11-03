package steps;

import base.Driver;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.time.Duration;
import java.util.Arrays;

public class MastodonSteps {

    static WebElement btnPickAnotherServer = Driver.getInstance().findElement(By.xpath("//android.widget.Button[@resource-id=\"org.joinmastodon.android:id/btn_get_started\"]"));
    static By btnPickForMe = By.xpath("//android.widget.Button[@resource-id=\"org.joinmastodon.android:id/btn_random_instance\"]");
    WebElement btnJoin = Driver.getInstance().findElement(By.xpath("//android.widget.Button[@resource-id=\"org.joinmastodon.android:id/btn_join_default_server\"]"));
    By txtMastodonUno = By.xpath("//*[@text='mastodon.uno']");

    @Step("Checking if the 'Pick another server' is displayed")
    public static void pickAnotherServerBtnIsDisplayed() {
        Driver.getWebDriverWait().until(ExpectedConditions.visibilityOf(btnPickAnotherServer));
        attachScreenshot();
        Assert.assertTrue(btnPickAnotherServer.isDisplayed(), "'Pick another server' button isn't displayed");
    }

    @Step("Clicking on the 'Pick another server' button")
    public static void clickPickAnotherServerBtn() {
        btnPickAnotherServer.click();
        attachScreenshot();
    }

    @Step("Checking if the 'Pick for me' button is displayed")
    public static void pickForMeBtnIsDisplayed() {
        Driver.getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(btnPickForMe));
        attachScreenshot();
        Assert.assertTrue(Driver.getInstance().findElement(btnPickForMe).isDisplayed(), "Pick for me button isn't visible");
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] attachScreenshot() {
        return ((TakesScreenshot) Driver.getInstance()).getScreenshotAs(OutputType.BYTES);
    }

    private void performScrollDown() {
        Dimension size = Driver.getInstance().manage().window().getSize();
        int startX = size.width / 2;
        int startY = (int) (size.height * 0.8);
        int endY = (int) (size.height * 0.2);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence scroll = new Sequence(finger, 1)
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), startX, endY))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        Driver.getInstance().perform(Arrays.asList(scroll));
    }
}
