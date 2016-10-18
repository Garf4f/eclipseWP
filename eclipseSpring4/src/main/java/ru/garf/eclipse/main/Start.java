package ru.garf.eclipse.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ru.garf.eclipse.impls.robot.ModelT1000;

public class Start {
	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("all_context.xml");
		ModelT1000 t1000 = (ModelT1000) context.getBean("t1000");
		t1000.action();

		// RobotConveyor t1000Conveyor = (RobotConveyor)
		// context.getBean("t1000Pool");
		// Robot term1 = t1000Conveyor.creatRobot();
		// term1.action();

		// T1000Pool t1000Pool = (T1000Pool) context.getBean("t1000Pool");
		// t1000Pool.beginShow();

		// T1000Pool t1000GoldenPool = (T1000Pool)
		// context.getBean("t1000GoldenPool");
		// t1000GoldenPool.beginShow();

	}

}
