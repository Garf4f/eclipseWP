package ru.garf.exlipse_1.start;

import ru.garf.exlipse_1.objects.SonyHand;
import ru.garf.exlipse_1.objects.SonyHead;
import ru.garf.exlipse_1.objects.SonyLeg;

public class Robot {
	private SonyHand hand = new SonyHand();
	private SonyLeg leg = new SonyLeg();
	private SonyHead head = new SonyHead();
	
	public void action(){
		head.calc();
		hand.catchSomthing();
		leg.go();
	}
}
