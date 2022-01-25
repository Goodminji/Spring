package hello.proxy.common.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeAdvice implements MethodInterceptor{

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		log.info("TimeProxy ����");
		long startTime = System.currentTimeMillis();
		
		Object result = invocation.proceed();//target�� ã�Ƽ� ���� ȣ���� ����
		
		long endTime = System.currentTimeMillis();
		
		long resulttime = endTime-startTime;
		
		log.info("TimeProxy ���� resultTime={}",resulttime);
		
		return result;
	}

}
