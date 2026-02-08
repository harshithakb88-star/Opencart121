package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class Extentreportmanage implements ITestListener {
	
	
	public ExtentSparkReporter sparkReporter; // UI of the report
	public ExtentReports extent; // populate common info on the report
	public ExtentTest test; // creating testcase enteries in the report and update the status of the test methods
	
	String repName;
	
	public  void onStart(ITestContext testcontext) {
	/*	SimpleDateFormat df= new SimpleDateFormat("yyyy.MM.dd.HH.mm.SS");
		Date dt=new Date();
		String currentdatetimestamp= df.format(dt); */
		
		String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.SS").format(new Date());
		repName= "Test-Report-"+timeStamp+".html";
		
	    sparkReporter= new ExtentSparkReporter(".\\reports\\"+repName); // specify location of the report
	    sparkReporter.config().setDocumentTitle("opencart Automation report");// title of the report
	    sparkReporter.config().setReportName("opencart Functional Testing"); // name of the report
	    sparkReporter.config().setTheme(Theme.DARK); // theme of the report
	    
	    extent= new ExtentReports();
	    extent.attachReporter(sparkReporter);
	    
	    extent.setSystemInfo("Application", "opencart");
	    extent.setSystemInfo("Module", "Admin");
	    extent.setSystemInfo("Sub module", "Customers");
	    extent.setSystemInfo("username", System.getProperty("user.name"));
	    extent.setSystemInfo("Environment", "QA");
	    
	    String os=testcontext.getCurrentXmlTest().getParameter("os");
	    extent.setSystemInfo("Operating system", os);
	    
	    String browser= testcontext.getCurrentXmlTest().getParameter("Browser");
	    extent.setSystemInfo("Browser", browser);
	    
	   List<String> includedGroups= testcontext.getCurrentXmlTest().getIncludedGroups();
	   if(!includedGroups.isEmpty()) {
		   extent.setSystemInfo("Groups", includedGroups.toString());
	   }
	}
	
	public void onTestSuccess(ITestResult result) {
		test=extent.createTest(result.getTestClass().getName()); // create a new entry to the report
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName()+"got successfully executed"); // update the status
	  }
	    
	 public void onTestFailure(ITestResult result) {
		 test=extent.createTest(result.getTestClass().getName()); // create a new entry to the report
			test.assignCategory(result.getMethod().getGroups());
		 test.log(Status.FAIL, result.getName()+"got failed");
		 test.log(Status.FAIL, result.getThrowable().getMessage());
		 try {
			 String imgPath= new BaseClass().captureScreen(result.getName());
			 test.addScreenCaptureFromPath(imgPath);
		 }catch(IOException e1) {
			 e1.printStackTrace();
		 }
	  }
	    
	 public void onTestSkipped(ITestResult result) {
		 test=extent.createTest(result.getTestClass().getName()); // create a new entry to the report
			test.assignCategory(result.getMethod().getGroups());
		 test.log(Status.SKIP, result.getName()+"got skipped");
		 test.log(Status.INFO, result.getThrowable().getMessage());
		  }  
	    
	 public void onFinish(ITestContext context) {
			extent.flush();
			String pathofExtentReport= System.getProperty("user.dir")+"\\reports\\"+repName;
			File extentReport= new File(pathofExtentReport);
			try {
				Desktop.getDesktop().browse(extentReport.toURI());
			}catch(IOException e1) {
				 e1.printStackTrace();
			 }
					
		  }
	    
	    
	    
	  }

