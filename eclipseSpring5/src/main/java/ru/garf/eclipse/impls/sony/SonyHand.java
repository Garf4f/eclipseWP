package ru.garf.eclipse.impls.sony;

import ru.garf.eclipse.interfaces.Hand;

public class SonyHand implements Hand {

	@Override
	public void catchSomething() {
		System.out.println("Catched from Sony!");

	}

}
