package ru.garf.eclipse.impls.pool;

import java.util.Collection;

import ru.garf.eclipse.interfaces.Robot;
import ru.garf.eclipse.interfaces.RobotPool;

public class T1000Pool implements RobotPool {

	private Collection<Robot> robotCollection;

	public T1000Pool(Collection<Robot> robotCollection) {
		super();
		this.robotCollection = robotCollection;
	}

	@Override
	public Collection<Robot> getRobotCollection() {
		return robotCollection;
	}

	public void beginShow() {
		for (Robot robot : robotCollection) {
			robot.action();
		}
	}

}
