package listeners;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Test.ArkFramWork.BaseClass;
import utilities.ExtentReporter;

public class Listeners implements ITestListener {
	ExtentReports extentReport = ExtentReporter.getExtentReport();
	ExtentTest extentTest;
	ThreadLocal<ExtentTest> extentTestThread = new ThreadLocal<ExtentTest>();
	//String testName;
	@Override
	public void onTestStart(ITestResult result) {
		//extentReport = ExtentReporter.getExtentReport();
		
		String testName = result.getName();
		extentTest = extentReport.createTest(testName+"Execution Start");
		extentTestThread.set(extentTest);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testName = result.getName();
		//extentTest.log(Status.PASS, testName + "got passed");
		extentTestThread.get().log(Status.PASS,testName+"Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		//extentTest.fail(result.getThrowable());
		extentTestThread.get().fail(result.getThrowable());
		BaseClass base = new BaseClass();
		WebDriver driver=null;
		//below method will give Test function name
		String testName = result.getName();
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try{
			String screenshotFilePath = base.takeScreenShot(testName, driver);
			extentTestThread.get().addScreenCaptureFromPath(screenshotFilePath, testName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {

	}

	@Override
	public void onFinish(ITestContext context) {
		extentReport.flush();
	}

}
