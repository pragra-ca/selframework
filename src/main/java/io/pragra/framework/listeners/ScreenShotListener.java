package io.pragra.framework.listeners;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.pragra.framework.drivermanager.DriverManager;
import io.pragra.framework.report.HtmlReports;
import io.pragra.framework.utils.CaptureTypeEnum;
import io.pragra.framework.utils.CoreUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ScreenShotListener implements ITestListener {
    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTest test = HtmlReports.createTest(result.getName());
        CoreUtils.captureScreenShot(DriverManager.getDriver(), result.getName(), CaptureTypeEnum.PASS);
        test.log(Status.PASS, result.getName()+"Has passed");


    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = HtmlReports.createTest(result.getName());
        CoreUtils.captureScreenShot(DriverManager.getDriver(), result.getName(), CaptureTypeEnum.FAIL);
        test.log(Status.FAIL, result.getName()+"Has failed");

    }

    @Override
    public void onFinish(ITestContext context) {
        HtmlReports.flushReport();
    }
}
