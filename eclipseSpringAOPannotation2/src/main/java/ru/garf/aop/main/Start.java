package ru.garf.aop.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ru.garf.aop.objects.FileManager;

public class Start {

	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		FileManager fileman = (FileManager) context.getBean("fileManager");
		System.out.println();
		System.out.println();
		fileman.getExtensionList("C:\\Windows");
		fileman.getExtensionCounter("C:\\Windows");

	}
}
