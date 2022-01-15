package hello.proxy.config.v2_dynamicProxy.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;

/*
JDK 동적 프록시는 인터페이스가 필수
*/
public class LogTraceBasicHandler implements InvocationHandler{

	private final Object target;
	private final LogTrace logTrace;
	
	public LogTraceBasicHandler(Object target,LogTrace logTrace) {
		this.target = target;
		this.logTrace = logTrace;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		TraceStatus status = null;
		
		try {
			String message = method.getDeclaringClass().getSimpleName()+"."+method.getName()+"()";
			status = logTrace.begin(message);
			
			//로직 호출
			Object result = method.invoke(target, args);
			logTrace.end(status);
			
			return result;

		} catch (Exception e) {
			logTrace.exception(status, e);
			throw e;
		}
		
	}
}
