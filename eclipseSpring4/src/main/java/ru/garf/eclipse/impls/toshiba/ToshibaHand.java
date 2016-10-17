package ru.garf.eclipse.impls.toshiba;

import ru.garf.eclipse.interfaces.Hand;

public class ToshibaHand implements Hand {

	@Override
	public void catchSomething() {
		System.out.println("Catched from Toshiba!");

	}

}
