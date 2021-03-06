package ru.garf.aop.loggins;

import java.util.Map;
import java.util.Set;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyLogger {

	@Pointcut("execution(* ru.garf.aop.objects.Manager.*(..))")
	private void allMethods() {
	};

	@Around("allMethods()) && execution(java.util.Map *(..))")
	public Object watchTime(ProceedingJoinPoint joinpoint) {
		long start = System.currentTimeMillis();
		Object output = null;
		System.out.println(joinpoint.getArgs()[0]);
		try {
			output = joinpoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}

		long time = System.currentTimeMillis() - start;
		System.out.println("method end: " + joinpoint.getSignature().toShortString() + ", time=" + time + "ms");

		return output;
	}

	@SuppressWarnings("rawtypes")
	@AfterReturning(pointcut = "allMethods() && execution(java.util.Map *(..))", returning = "obj")
	public void printMap(Object obj) {

		System.out.println("Class - " + obj.getClass());
		Map map = (Map) obj;
		for (Object object : map.keySet()) {
			System.out.print(object + "-" + map.get(object) + "; ");
		}
		System.out.println();
	}

	@SuppressWarnings("rawtypes")
	@AfterReturning(pointcut = "allMethods() && execution(java.util.Set *(..))", returning = "obj")
	public void printSet(Object obj) {

		System.out.println("Class - " + obj.getClass());
		Set set = (Set) obj;
		for (Object object : set) {
			System.out.print(object + "; ");
		}
		System.out.println();

	}

}
