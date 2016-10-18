package ru.garf.eclipse.impls.robot;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.garf.eclipse.abstarct.robot.BaseModel;
import ru.garf.eclipse.enums.ColorStyle;
import ru.garf.eclipse.interfaces.Hand;
import ru.garf.eclipse.interfaces.Head;
import ru.garf.eclipse.interfaces.Leg;
import ru.garf.eclipse.interfaces.Robot;

@Component
// @Qualifier(value = "model1")
public class ModelT1000 extends BaseModel implements Robot, InitializingBean, DisposableBean {

	private ColorStyle color;
	private int year;
	private boolean soundEnabled;

	public ModelT1000() {

	}

	@Bean
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public ModelT1000 model1() {
		return new ModelT1000();
	}

	@Bean
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public ModelT1000 model2() {
		return new ModelT1000(ColorStyle.BLACK, 2005, true);
	}

	public ModelT1000(Hand hand, Leg leg, Head head, ColorStyle color, int year, boolean soundEnabled) {
		this.color = color;
		this.year = year;
		this.soundEnabled = soundEnabled;
	}

	public ModelT1000(ColorStyle color, int year, boolean soundEnabled) {
		super();
		this.color = color;
		this.year = year;
		this.soundEnabled = soundEnabled;
	}

	// public ModelT1000(Hand hand, Leg leg, Head head) {
	// super(hand, leg, head);
	// }

	// public Hand getHand() {
	// return hand;
	// }
	//
	// public void setHand(Hand hand) {
	// this.hand = hand;
	// }
	//
	// public Leg getLeg() {
	// return leg;
	// }
	//
	// public void setLeg(Leg leg) {
	// this.leg = leg;
	// }
	//
	// public Head getHead() {
	// return head;
	// }
	//
	// public void setHead(Head head) {
	// this.head = head;
	// }

	public ColorStyle getColor() {
		return color;
	}

	public void setColor(ColorStyle color) {
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
		getHead().calc();
		getHand().catchSomething();
		getLeg().go();

		System.out.println("color: " + color);
		System.out.println("year: " + year);
		System.out.println("can play sound: " + soundEnabled);
		System.out.println();
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
