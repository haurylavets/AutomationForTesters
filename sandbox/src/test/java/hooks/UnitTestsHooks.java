package hooks;

import org.testng.annotations.BeforeMethod;

public abstract class UnitTestsHooks {

    @BeforeMethod(onlyForGroups = {"unit tests"})
    public void startBrowser() {
        System.out.println("This method called before every unit test");
    }
}
