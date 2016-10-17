package ru.garf.eclipse.impls.robot;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import ru.garf.eclipse.abstarct.robot.BaseModel;
import ru.garf.eclipse.interfaces.Hand;
import ru.garf.eclipse.interfaces.Head;
import ru.garf.eclipse.interfaces.Leg;
import ru.garf.eclipse.interfaces.Robot;

public class ModelT1000 extends BaseModel implements Robot, InitializingBean, DisposableBean {

	private Hand hand;
	private Leg leg;
	private Head head;

	private String color;
	private int year;
	private boolean soundEnabled;

	public ModelT1000() {

	}

	public ModelT1000(Hand hand, Leg leg, Head head, String color, int year, boolean soundEnabled) {
		super();
		this.hand = hand;
		this.leg = leg;
		this.head = head;
		this.color = color;
		this.year = year;
		this.soundEnabled = soundEnabled;
	}

	public ModelT1000(String color, int year, boolean soundEnabled) {
		super();
		this.color = color;
		this.year = year;
		this.soundEnabled = soundEnabled;
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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public boolean isSoundEnabled() {
		return soundEnabled;
	}

	public void setSoundEnabled(boolean soundEnabled) {
		this.soundEnabled = soundEnabled;
	}

	@Override
	public void action() {
		head.calc();
		hand.catchSomething();
		leg.go();

		System.out.println("color: " + color);
		System.out.println("year: " + year);
		System.out.println("can play sound: " + soundEnabled);
	}

	@Override
	public void dance() {
		System.out.println("T1000 is dancing!");

	}

	@Override
	public void destroy() throws Exception {
		System.out.println(this + " DESTR");

	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println(this + " INIT");

	}

}
