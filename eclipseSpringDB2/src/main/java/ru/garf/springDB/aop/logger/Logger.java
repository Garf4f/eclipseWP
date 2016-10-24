package ru.garf.springDB.aop.logger;

import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Component
public class Logger {

	public void checkTransaction() {
		System.out.println(TransactionSynchronizationManager.isActualTransactionActive());
	}
}
