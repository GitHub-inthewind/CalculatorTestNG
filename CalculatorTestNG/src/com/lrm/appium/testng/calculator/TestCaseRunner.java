package com.lrm.appium.testng.calculator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;

public class TestCaseRunner {
	public static void main(String[] args)
	{
		System.out.println("main entry");
		String projectPath = System.getProperty("user.dir");
		TestNG testNg = new TestNG();
		List<String> suites = new ArrayList<String>();
		// 注：如果编译成jar并在命令行运行，需要根据jar包的位置将testng.xml和待测试apk特定目录， 
		// 否则会提示找不到文件
		suites.add(projectPath + File.separator + "testng.xml");
		testNg.setTestSuites(suites);
		testNg.run();
		System.out.println("main end");
	}

}
