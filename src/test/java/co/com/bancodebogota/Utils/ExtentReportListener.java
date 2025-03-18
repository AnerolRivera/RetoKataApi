package co.com.bancodebogota.Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportListener implements ITestListener {

    private ExtentSparkReporter sparkReporter;
    private ExtentReports extent;
    private ExtentTest test;

    @Override
    public void onStart(ITestContext context) {
        // Generar un reporte con timestamp para evitar sobrescribir archivos
        String timestamp = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
        String reportPath = "test-output/ExtentReport-" + timestamp + ".html";

        sparkReporter = new ExtentSparkReporter(reportPath);
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        System.out.println("Generando reporte en: " + reportPath);
    }

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        System.out.println("Iniciando prueba: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.pass("Prueba exitosa");
        System.out.println("Test PASSED: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.fail("Prueba fallida: " + result.getThrowable());
        System.out.println("Test FAILED: " + result.getMethod().getMethodName() + " - " + result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
        System.out.println("Reporte generado correctamente.");
    }
}
