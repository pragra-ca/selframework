package io.pragra.framework.listeners;

import io.pragra.framework.drivermanager.DriverManager;
import io.pragra.framework.utils.CaptureTypeEnum;
import io.pragra.framework.utils.CoreUtils;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ScreenShotListener implements ITestListener {
    @Override
    public void onTestSuccess(ITestResult result) {
        CoreUtils.captureScreenShot(DriverManager.getDriver(), result.getName(), CaptureTypeEnum.PASS);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        CoreUtils.captureScreenShot(DriverManager.getDriver(), result.getName(), CaptureTypeEnum.FAIL);

    }
}
