package io.pragra.framework.utils;

import io.pragra.framework.conf.Configuration;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CoreUtils {
    private static void createDirs(Path dirPath){
        if(!Files.exists(dirPath)){
            try {
                Files.createDirectories(dirPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String captureScreenShot(WebDriver driver, String testName, CaptureTypeEnum captureType){
        createScreenShotDirs();
        String fileName = testName+"_"+getTimeStamp()+".png";
        if (captureType==CaptureTypeEnum.PASS) {
            Path path = Paths.get(Configuration.getProperty("screenshot.pass.dir"),fileName);
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                Files.copy(screenshot.toPath(),path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (captureType==CaptureTypeEnum.FAIL) {
            Path path = Paths.get(Configuration.getProperty("screenshot.fail.dir"),fileName);
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                Files.copy(screenshot.toPath(),path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileName;
    }

    public static String getTimeStamp() {
        SimpleDateFormat format = new SimpleDateFormat(Configuration.getProperty("datestamp"));
        return format.format(new Date());
    }

    private static void createScreenShotDirs() {
        String passDir = Configuration.getProperty("screenshot.pass.dir");
        String failDir = Configuration.getProperty("screenshot.fail.dir");
        Path passPath = Paths.get(passDir);
        Path failPath = Paths.get(failDir);
        createDirs(passPath);
        createDirs(failPath);
    }
}
