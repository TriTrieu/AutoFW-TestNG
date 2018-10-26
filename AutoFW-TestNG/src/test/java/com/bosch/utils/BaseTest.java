package com.bosch.utils;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
	public static WebDriver driver;
	
	@BeforeSuite
	public void before_suite(){
		
	}
	
	@AfterSuite
	public void after_suite(){
		
	}
	
	@BeforeClass
	public void before_class(){
		
	}
	
	@AfterClass
	public void after_class(){
		
	}
	
	@BeforeMethod(alwaysRun = true)
	public void before_method(){
		
	}
	
    @AfterMethod(alwaysRun = true)
	public void after_method(){
		
	}
	
	
}
