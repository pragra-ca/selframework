package io.pragra.framework.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.pragra.framework.conf.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HtmlReports {
    private ExtentReports reports;
    private static HtmlReports instance;

    private HtmlReports() {
        reports = new ExtentReports();
        String reportDir = Configuration.getProperty("report.dir");
        if (!Files.exists(Paths.get(reportDir))) {
            try {
                Files.createDirectories(Paths.get(reportDir));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ExtentSparkReporter reporter = new ExtentSparkReporter(Configuration.getProperty("report.dir")+"/"+Configuration.getProperty("report.filename"));
        reports.attachReporter(reporter);
    }


    public static synchronized ExtentTest createTest(String testName){
        if(instance==null) {
            instance = new HtmlReports();
        }
        return instance.reports.createTest(testName);
    }

    public static void flushReport() {
        if (instance != null) {
            instance.reports.flush();
        }
    }

}
