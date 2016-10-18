package ru.garf.aop.loggins;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyLogger {

	@Pointcut("execution(* ru.garf.aop.objects.FileManager.*(..))")
	private void allMethods() {
	};

	@Around("allMethods()")
	public Object watchTime(ProceedingJoinPoint joinpoint) {
		long start = System.currentTimeMillis();
		System.out.println("method begin: " + joinpoint.getSignature().toShortString());
		Object output = null;
		try {
			output = joinpoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.out.println(joinpoint.getArgs()[0]);
		System.out.println(output);
		long time = System.currentTimeMillis() - start;
		System.out.println("method end: " + joinpoint.getSignature().toShortString() + ", time=" + time + "ms");

		return output;
	}

	@AfterReturning(pointcut = "allMethods()", returning = "obj")
	public void print(Object obj) {

		System.out.println("Print info begin >>");

		System.out.println(obj.getClass());

		System.out.println("Print info end <<");
		System.out.println();

	}

}
