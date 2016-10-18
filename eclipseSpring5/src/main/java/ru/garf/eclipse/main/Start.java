package ru.garf.eclipse.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ru.garf.eclipse.impls.robot.ModelT1000;

public class Start {
	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("all_context.xml");

		// ModelT1000 model1 = (ModelT1000) context.getBean("modelT1000");
		// model1.action();
		ModelT1000 model2 = (ModelT1000) context.getBean("model1");
		model2.action();
		ModelT1000 model3 = (ModelT1000) context.getBean("model2");
		model3.action();

	}

}
