import base.Driver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import steps.MastodonSteps;

public class MastodonAndroidTest {

    @Test
    public void appInstalledAndRunning() {
        MastodonSteps.pickAnotherServerBtnIsDisplayed();
        MastodonSteps.clickPickAnotherServerBtn();
        MastodonSteps.pickForMeBtnIsDisplayed();
        System.out.println();
    }

    @AfterMethod
    public void tearDown() {
        Driver.quit();
    }
}
