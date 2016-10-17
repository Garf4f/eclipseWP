package ru.garf.eclipse.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ru.garf.eclipse.interfaces.Robot;
import ru.garf.eclipse.interfaces.RobotConveyor;

public class Start {
	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("all_context.xml");
		// ModelT1000 t1000 = (ModelT1000) context.getBean("t1000");

		RobotConveyor t1000Conveyor = (RobotConveyor) context.getBean("t1000Conveyor");

		Robot term1 = t1000Conveyor.creatRobot();

		term1.action();
	}

}
