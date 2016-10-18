package ru.garf.eclipse.impls.toshiba;

import org.springframework.stereotype.Component;

import ru.garf.eclipse.interfaces.Leg;

@Component
public class ToshibaLeg implements Leg {

	@Override
	public void go() {
		System.out.println("Go from Toshiba!");

	}

}
