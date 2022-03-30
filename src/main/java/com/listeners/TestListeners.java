package com.listeners;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;

import sfdc.test.BaseTest;
import sfdc.utilities.ReusableUtils;

public class TestListeners implements ITestListener {
	ReusableUtils ru = new ReusableUtils();

	@Override
	public void onTestStart(ITestResult result) {
		BaseTest.test = BaseTest.extent.createTest(result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("Test script passed ");
		
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("Test script Failed ");
		try {
			BaseTest.test.addScreenCaptureFromPath(ru.takeScreenshot());
		} catch (IOException e) {
			e.printStackTrace();
		}
		BaseTest.test.fail(result.getName());
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	
}
