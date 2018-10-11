package com.amazon.HybridFramework.Execution;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.amazon.test.testdata.TestData;

public class Executor {

protected static Logger logger  =  Logger.getLogger(Thread.currentThread().getStackTrace()[1].getClassName() );
public static	WebDriver driver=null;
public static List<String> listofTestSripts=null;

public static Class<?> className=null;
public static String strTmpResultDir  =	"./\\logs";

public static String testBrowser;
private static int iTestStartIndex=1;
private static Properties TestProperties = null;	
private static FileInputStream fisTestConfig = null;	
private static FileInputStream fisTestSuite = null;
public static String testResultSuiteName=null;
public static String testSuiteName = null;	
public static String strCurrnetSheetName = null;

private static String[] testSheetNames = null;	
public static String testResourceDir  =	"./resources";

protected static boolean bTestScreenshot = false;
protected static String URL=null;
protected static WebDriverWait driverWait = null;
protected  static long wait=20;
protected static Actions cursor=null;



private synchronized static void setLogFileName(String path) {
    String logFileName = (path.endsWith(File.separator)) ? path : path + File.separator;
    logFileName = strTmpResultDir + "/TestLog_" + path + ".log";
    Properties logProp = new Properties();
    try {	    	  
  	  logProp.load(new FileInputStream("./\\log4j.properties"));
	      logProp.setProperty("log4j.appender.FILE.File", logFileName );
	      LogManager.resetConfiguration();
	      PropertyConfigurator.configure(logProp);
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} 
   
}


private static void getTestConfiguration(){
	try {
		
		TestProperties = new Properties();
		fisTestConfig = new FileInputStream(testResourceDir + "\\TestConfig.properties");	
    	
		TestProperties.load(fisTestConfig);    			
		testSuiteName = testResourceDir + "/" +TestProperties.getProperty("test.suitename").trim();
		
		testSheetNames= TestProperties.getProperty("test.sheetnames").split(",");  
		

		testResultSuiteName =  strTmpResultDir + "/" + TestProperties.getProperty("test.resultfilename").trim();    
		
	
		iTestStartIndex = Integer.parseInt( TestProperties.getProperty("test.startindex").trim() );
		testBrowser = TestProperties.getProperty("test.browser").trim();
		bTestScreenshot = Boolean.parseBoolean( TestProperties.getProperty("test.screenshot").trim() );
		//TestProperties=null;
		URL = String.valueOf(TestProperties.getProperty("URL").trim() );
	//	wait = Long.parseUnsignedLong(TestProperties.get("waitingTime")).trim();
		
		logger.info("Loaded Test Configuration file...");
		logger.info("Test Browser:  "  +  testBrowser);
		logger.info("TestSuite Path: " + testSuiteName);
		
		
	} catch (Exception exception) {
		iTestStartIndex=1;
		exception.printStackTrace();
    }
}

	public static void setUp() {
		getTestConfiguration();

		if (testBrowser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "./libs\\chromedriver.exe");
			driver = new ChromeDriver();
			System.out.println("Brower is opened");
			logger.info("Brower is opened");
			driverWait=new WebDriverWait(driver, wait);
			cursor = new Actions(driver);
		}

	}
	
	public static void tearDown(){
		driver.quit();
		logger.info("Brower is closed");
	}
	
/**
 * Hybrid Framework Core Code
 * 
 * @param args
 * @throws ClassNotFoundException
 * @throws IllegalAccessException
 * @throws IllegalArgumentException
 * @throws InvocationTargetException
 * @throws IOException
 */
	public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		
		listofTestSripts = new LinkedList<String>();
		
	//	String CLASSPATH= System.getProperty("java.class.path");
		
		
		
		File ClassesPath = new File("bin/com/amazon/test/scripts");
		
		File[] listOfSriptClasses= ClassesPath.listFiles();
		
		for(File       eachFile  :            listOfSriptClasses){
			
			listofTestSripts.add(FilenameUtils.removeExtension(eachFile.getName()));
			
			System.out.println(listofTestSripts.size());
		}
		
		for(String   testSriptCName:          listofTestSripts){
			
			
			System.out.println("testSriptCName....."+testSriptCName);
			
			setLogFileName(testSriptCName);
			
			className=  Class.forName("com.amazon.test.scripts."+testSriptCName);
			
			Method[] listOfCmethods=className.getMethods();
			
			setUp();
			
			for(Method eaChMethod:listOfCmethods){
				
				/**
				 * Getting TestCase IDs from the Excel Sheet
				 * 
				 */
				for(int rowNo=4; rowNo <= 13; rowNo++){
					String currentTestCase = TestData.readExcel(rowNo, 0);
					String currentTestCaseRunStatus = TestData.readExcel(rowNo, 5);
					if(eaChMethod.getName().startsWith("keyword_") &&
							eaChMethod.getName().contains(currentTestCase) && currentTestCaseRunStatus.equalsIgnoreCase("YES")){
						
						eaChMethod.invoke(null);
						
					}
				}
				
				
			}
			
			tearDown();
			
		}
		
		
	}

}
