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

	@Around("allMethods() && execution(java.util.Map *(..))")
	public Object watchTime(ProceedingJoinPoint joinpoint) {
		long start = System.currentTimeMillis();
		Object output = null;
		System.err.println(joinpoint.getArgs()[0]);
		try {
			output = joinpoint.proceed(new Object[] { "C:\\Windows\\system32" });
		} catch (Throwable e) {
			e.printStackTrace();
		}

		long time = System.currentTimeMillis() - start;
		System.out.println("method end: " + joinpoint.getSignature().toShortString() + ", time=" + time + "ms");

		return output;
	}

	@SuppressWarnings("rawtypes")
	@AfterReturning(pointcut = "allMethods() && execution(java.util.Map *(String)) && args(folder)", returning = "obj")
	public void printMap(Object obj, String folder) {

		System.out.println("Class - " + obj.getClass());
		System.out.println("Folder - " + folder);
		Map map = (Map) obj;
		for (Object object : map.keySet()) {
			System.out.print(object + "-" + map.get(object) + "; ");
		}
		System.out.println();
	}

	@SuppressWarnings("rawtypes")
	@AfterReturning(pointcut = "allMethods() && execution(java.util.Set *(String)) && args(folder)", returning = "obj")
	public void printSet(Object obj, String folder) {

		System.out.println("Class - " + obj.getClass());
		System.out.println("Folder - " + folder);
		Set set = (Set) obj;
		for (Object object : set) {
			System.out.print(object + "; ");
		}
		System.out.println();

	}

}
