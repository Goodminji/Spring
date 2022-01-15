package hello.proxy.cglib.code;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeMethodInterceptor implements MethodInterceptor{

	private final Object target;
	
	public TimeMethodInterceptor(Object target) {
		this.target = target;
	}
	
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
		log.info("TimeProxy ����");
		long startTime = System.currentTimeMillis();
		
		Object result = methodProxy.invoke(target, args);
		
		long endTime = System.currentTimeMillis();
		
		long resulttime = endTime-startTime;
		
		log.info("TimeProxy ���� resultTime={}",resulttime);
		
		return result;
	}

}
