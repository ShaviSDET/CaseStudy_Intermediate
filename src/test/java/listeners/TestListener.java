package listeners;

import base.DriverFactory;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static utils.ScreenshotUtil.take;

public class TestListener extends DriverFactory implements ITestListener {
    private static final ThreadLocal<ExtentTest> testNode = new ThreadLocal<>();
    private static final ExtentReports extent = ExtentManager.getInstance();

    @Override public void onTestStart(ITestResult result) {
        testNode.set(extent.createTest(result.getMethod().getMethodName()));
    }

    @Override public void onTestSuccess(ITestResult result) {
        String path = take(getDriver(), result.getMethod().getMethodName());
        if (path != null) testNode.get().pass("Passed",
                MediaEntityBuilder.createScreenCaptureFromPath(path).build());
    }

    @Override public void onTestFailure(ITestResult result) {
        String path = take(getDriver(), result.getMethod().getMethodName());
        if (path != null) testNode.get().fail(result.getThrowable(),
                MediaEntityBuilder.createScreenCaptureFromPath(path).build());
        else testNode.get().fail(result.getThrowable());
    }

    @Override public void onFinish(ITestContext context) { extent.flush(); }
}
