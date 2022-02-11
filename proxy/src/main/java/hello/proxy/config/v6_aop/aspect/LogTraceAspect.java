package hello.proxy.config.v6_aop.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect//���Ͻ� ���� @Aspect�� ���� ���������� ��ȯ�ؼ� ����,
public class LogTraceAspect {

	private final LogTrace logTrace;
	
	public LogTraceAspect(LogTrace logTrace) {
		this.logTrace = logTrace;
	}
	
	//@Around ����Ʈ�� ǥ������ �ִ´� AspectJ ǥ���� ���
	// @Around �޼ҵ�� �����̽��� �ȴ�.
	@Around("execution(* hello.proxy.app..*(..))")
	public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
		TraceStatus status = null;
		
		try {
			String message = joinPoint.getSignature().toShortString();
			status = logTrace.begin(message);
			
			//���� ȣ��
			Object result = joinPoint.proceed();
			logTrace.end(status);
			
			return result;

		} catch (Exception e) {
			logTrace.exception(status, e);
			throw e;
		}
	}
}
