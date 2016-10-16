package ru.garf.eclipse.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ru.garf.eclipse.interfaces.Robot;

public class Start {
	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		Robot t1000 = (Robot) context.getBean("t1000");
		t1000.dance();

	}

}
