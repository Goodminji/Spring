package hello.proxy.config.v6_aop.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect//프록시 적용 @Aspect를 보고 어드바이저로 변환해서 저장,
public class LogTraceAspect {

	private final LogTrace logTrace;
	
	public LogTraceAspect(LogTrace logTrace) {
		this.logTrace = logTrace;
	}
	
	//@Around 포인트컷 표현식을 넣는다 AspectJ 표현식 사용
	// @Around 메소드는 어드바이스가 된다.
	@Around("execution(* hello.proxy.app..*(..))")
	public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
		TraceStatus status = null;
		
		try {
			String message = joinPoint.getSignature().toShortString();
			status = logTrace.begin(message);
			
			//로직 호출
			Object result = joinPoint.proceed();
			logTrace.end(status);
			
			return result;

		} catch (Exception e) {
			logTrace.exception(status, e);
			throw e;
		}
	}
}
