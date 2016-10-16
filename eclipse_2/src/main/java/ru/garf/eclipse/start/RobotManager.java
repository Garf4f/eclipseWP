package ru.garf.eclipse.start;

import ru.garf.eclipse.impls.sony.SonyHand;
import ru.garf.eclipse.impls.sony.SonyLeg;
import ru.garf.eclipse.impls.toshiba.ToshibaHead;
import ru.garf.eclipse.interfaces.Hand;
import ru.garf.eclipse.interfaces.Head;
import ru.garf.eclipse.interfaces.Leg;

public class RobotManager {
	public static void main(String[] args) {

		Hand hand = new SonyHand();
		Leg leg = new SonyLeg();
		Head head = new ToshibaHead();

		Robot robot = new Robot(hand, leg, head);
		robot.action();
	}

}
