package ru.garf.eclipse.impls.robot;

import ru.garf.eclipse.interfaces.Hand;
import ru.garf.eclipse.interfaces.Head;
import ru.garf.eclipse.interfaces.Leg;
import ru.garf.eclipse.interfaces.Robot;

public class ModelT1000 implements Robot {

	private Hand hand;
	private Leg leg;
	private Head head;

	public ModelT1000() {

	}

	public ModelT1000(Hand hand, Leg leg, Head head) {
		super();
		this.hand = hand;
		this.leg = leg;
		this.head = head;
	}

	public Hand getHand() {
		return hand;
	}

	public void setHand(Hand hand) {
		this.hand = hand;
	}

	public Leg getLeg() {
		return leg;
	}

	public void setLeg(Leg leg) {
		this.leg = leg;
	}

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	@Override
	public void action() {
		head.calc();
		hand.catchSomething();
		leg.go();
	}

	@Override
	public void dance() {
		System.out.println("T1000 is dancing!");

	}

}
