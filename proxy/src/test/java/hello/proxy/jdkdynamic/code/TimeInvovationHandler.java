package hello.proxy.jdkdynamic.code;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeInvovationHandler implements InvocationHandler{

	private final Object target;
	
	public TimeInvovationHandler(Object target) {
		this.target = target;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		log.info("TimeProxy ����");
		long startTime = System.currentTimeMillis();
		
		Object result = method.invoke(target, args);
		
		long endTime = System.currentTimeMillis();
		
		long resulttime = endTime-startTime;
		
		log.info("TimeProxy ���� resultTime={}",resulttime);
		
		return result;
	}

}
