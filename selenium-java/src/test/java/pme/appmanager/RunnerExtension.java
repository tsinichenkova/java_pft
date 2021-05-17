package pme.appmanager;


import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class RunnerExtension extends TestWatcher {
    @Override
    protected void failed(Throwable e, Description description) {
        // Handle logging of failing tests
        System.out.println("Fail!");
    }

    @Override
    protected void succeeded(Description description) {
        // Handle logging of succeeding tests
        System.out.println("Success!");
    }
}


