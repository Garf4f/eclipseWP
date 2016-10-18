package ru.garf.eclipse.impls.toshiba;

import org.springframework.stereotype.Component;

import ru.garf.eclipse.interfaces.Head;

@Component
public class ToshibaHead implements Head {

	@Override
	public void calc() {
		System.out.println("Thinking from Toshiba...");

	}

}
