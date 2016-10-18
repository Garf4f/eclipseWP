package ru.garf.eclipse.impls.toshiba;

import org.springframework.stereotype.Component;

import ru.garf.eclipse.interfaces.Hand;

@Component
public class ToshibaHand implements Hand {

	@Override
	public void catchSomething() {
		System.out.println("Catched from Toshiba!");

	}

}
