package api.extentreports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentTestNGListener implements ITestListener {
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public void onStart(ITestContext context) {
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String reportPath = "C:\\Users\\igalk\\IdeaProjects\\spotifyapiautomation\\src\\main\\resources\\reports\\ExtentReport_" + timestamp + ".html";

        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(reportPath);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    public void onTestStart(ITestResult result) {
        String packageName = result.getMethod().getTestClass().getRealClass().getPackage().getName();
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(), result.getMethod().getDescription());
        extentTest.assignCategory(packageName);
        test.set(extentTest);
    }

    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test passed");
        test.get().info("Test passed With Status 200 and asserted fields in JSON file");

        // Log the response message
        Object[] parameters = result.getParameters();
        if (parameters.length > 0) {
            test.get().info("Response: " + parameters[0]);
        }
    }

    public void onTestFailure(ITestResult result) {
        test.get().fail(result.getThrowable());

        // Log the response message
        Object[] parameters = result.getParameters();
        if (parameters.length > 0) {
            test.get().info("Response: " + parameters[0]);
        }
    }
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}