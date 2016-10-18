package ru.garf.aop.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ru.garf.aop.objects.FileManager;

public class Start {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		FileManager fileUtil = (FileManager) context.getBean("fileManager");
		// fileUtil.getExtensionCounter("C:\\Windows\\System32");
		fileUtil.getExtensionCounter("C:\\Windows");
		// fileUtil.getExtensionList("C:\\Windows\\System32");
		fileUtil.getExtensionList("C:\\Windows");

	}
}
